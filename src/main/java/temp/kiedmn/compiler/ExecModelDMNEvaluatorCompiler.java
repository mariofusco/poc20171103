/*
 * Copyright 2005 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package temp.kiedmn.compiler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.drools.compiler.commons.jci.compilers.CompilationResult;
import org.drools.compiler.commons.jci.problems.CompilationProblem;
import org.drools.compiler.compiler.io.memory.MemoryFileSystem;
import org.drools.core.common.ProjectClassLoader;
import org.kie.api.runtime.rule.DataSource;
import org.kie.dmn.core.api.DMNExpressionEvaluator;
import org.kie.dmn.core.ast.DMNBaseNode;
import org.kie.dmn.core.compiler.DMNCompilerContext;
import org.kie.dmn.core.compiler.DMNCompilerImpl;
import org.kie.dmn.core.compiler.DMNEvaluatorCompilerImpl;
import org.kie.dmn.core.compiler.DMNFEELHelper;
import org.kie.dmn.core.impl.DMNModelImpl;
import org.kie.dmn.model.v1_1.DecisionTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import temp.kiedmn.DMNUnit;

import static java.util.stream.Collectors.joining;

import static org.drools.modelcompiler.builder.JavaParserCompiler.getCompiler;

public class ExecModelDMNEvaluatorCompiler extends DMNEvaluatorCompilerImpl {

    private static final Logger logger = LoggerFactory.getLogger(ExecModelDMNEvaluatorCompiler.class);

    enum GeneratorsEnum {
        EVALUATOR("Evaluator", new EvaluatorSourceGenerator()),
        UNIT("DTUnit", new UnitSourceGenerator()),
        EXEC_MODEL("ExecModel", new ExecModelSourceGenerator()),
        UNARY_TESTS("UnaryTests", new UnaryTestsSourceGenerator());

        String type;
        SourceGenerator sourceGenerator;

        GeneratorsEnum( String type, SourceGenerator sourceGenerator) {
            this.type = type;
            this.sourceGenerator = sourceGenerator;
        }
    }

    private ProjectClassLoader projectClassLoader = ProjectClassLoader.createProjectClassLoader();

    public ExecModelDMNEvaluatorCompiler( DMNCompilerImpl compiler, DMNFEELHelper feel ) {
        super( compiler, feel );
    }

    @Override
    protected DMNExpressionEvaluator compileDecisionTable( DMNCompilerContext ctx, DMNModelImpl model, DMNBaseNode node, String dtName, DecisionTable dt ) {
        DTableModel dTableModel = new DTableModel( model.getNamespace(), dtName, dt );
        AbstractModelEvaluator evaluator = generateEvaluator(dTableModel);
        evaluator.initParameters(model, node, dt, dtName, dTableModel.getFeelExpressionsForInputs( ctx ));
        return evaluator;
    }

    public AbstractModelEvaluator generateEvaluator(DTableModel dTableModel) {
        String pkgName = dTableModel.getNamespace();
        String clasName = dTableModel.getTableName();

        MemoryFileSystem srcMfs = new MemoryFileSystem();
        MemoryFileSystem trgMfs = new MemoryFileSystem();
        String[] sources = new String[GeneratorsEnum.values().length];

        for (int i = 0; i < sources.length; i++) {
            GeneratorsEnum generator = GeneratorsEnum.values()[i];
            String className = pkgName + "." + clasName + generator.type;
            sources[i] = "src/main/java" + className.replace( '.', '/' ) + ".java";
            String javaSource = generator.sourceGenerator.generate( dTableModel );
            srcMfs.write( sources[i], javaSource.getBytes() );
        }

        CompilationResult res = getCompiler().compile(sources, srcMfs, trgMfs, projectClassLoader);

        CompilationProblem[] errors = res.getErrors();
        if (errors != null && errors.length > 0) {
            Stream.of(errors).forEach( System.err::println );
            throw new RuntimeException();
        }

        trgMfs.getFileNames().stream().forEach( f -> projectClassLoader.defineClass( f.replace( '/', '.' ).substring( 0, f.length()-".class".length() ), trgMfs.getBytes( f ) ) );

        try {
            Class<?> evalClass = projectClassLoader.loadClass( pkgName + "." + clasName + "Evaluator" );
            return (AbstractModelEvaluator) evalClass.newInstance();
        } catch (Exception e) {
            throw new UnsupportedOperationException( "Unknown decision table: " + clasName, e );
        }
    }

    interface SourceGenerator {
        String generate(DTableModel dTableModel);
    }

    public static class EvaluatorSourceGenerator implements SourceGenerator {
        public String generate(DTableModel dTableModel) {
            String pkgName = dTableModel.getNamespace();
            String clasName = dTableModel.getTableName();

            StringBuilder sb = new StringBuilder();
            sb.append( "package " ).append( pkgName ).append( ";\n" );
            sb.append( "\n" );
            sb.append( "import java.util.List;\n" );
            sb.append( "import org.drools.model.Rule;\n" );
            sb.append( "import " ).append( DMNUnit.class.getCanonicalName() ).append( ";\n" );
            sb.append( "\n" );
            sb.append( "public class " ).append( clasName ).append( "Evaluator extends " + AbstractModelEvaluator.class.getCanonicalName() + "{\n" );
            sb.append( "\n" );
            sb.append( "    @Override\n" );
            sb.append( "    protected List<Rule> getRules() {\n" );
            sb.append( "        return " ).append( clasName ).append( "ExecModel.getRules();\n" );
            sb.append( "    }\n" );
            sb.append( "\n" );
            sb.append( "    @Override\n" );
            sb.append( "    protected DMNUnit getDMNUnit() {\n" );
            sb.append( "        return new " ).append( clasName ).append( "DTUnit();\n" );
            sb.append( "    }\n" );
            sb.append( "}\n" );

            String source = sb.toString();
            logger.debug(clasName + ":\n" + source);
            return source;
        }
    }

    public static class ExecModelSourceGenerator implements SourceGenerator {
        public String generate( DTableModel dTableModel ) {
            String pkgName = dTableModel.getNamespace();
            String clasName = dTableModel.getTableName();

            StringBuilder sb = new StringBuilder();
            sb.append( "package " ).append( pkgName ).append( ";\n" );
            sb.append( "\n" );
            sb.append( "import java.util.List;\n" );
            sb.append( "import org.kie.api.runtime.rule.DataSource;\n" );
            sb.append( "import org.drools.model.*;\n" );
            sb.append( "import org.drools.modelcompiler.dsl.pattern.D;\n" );
            sb.append( "import static " ).append( pkgName ).append( "." ).append( clasName ).append( "UnaryTests.TEST_ARRAY;\n" );
            sb.append( "import static " ).append( UnaryTestsUtil.class.getCanonicalName() ).append( ".unaryTestsOr;\n" );
            sb.append( "\n" );
            sb.append( "public class " ).append( clasName ).append( "ExecModel {\n" );
            sb.append( "\n" );
            sb.append( "    public static List<Rule> getRules() {\n" );
            sb.append( "        return java.util.Arrays.asList( " );
            sb.append( IntStream.range( 0, dTableModel.getRows().size() ).mapToObj( i -> "rule_" + clasName + "_" + i + "()" ).collect( joining(", ") ) );
            sb.append( " );\n" );
            sb.append( "    }\n" );

            int exprCounter = 0;

            for (int i = 0; i < dTableModel.getRows().size(); i++) {
                DTableModel.DRowModel row = dTableModel.getRows().get(i);

                sb.append( "\n" );
                sb.append( "    private static Rule rule_" + clasName + "_" + i + "() {\n" );
                for (int j = 0; j < dTableModel.getOutputsNr(); j++) {
                    sb.append( "        UnitData<DataSource> var_output" + j + " = D.unitData(DataSource.class, \"output" + j + "\");\n" );
                }
                for (int j = 0; j < dTableModel.getInputs().size(); j++) {
                    sb.append( "        UnitData<DataSource> var_input" + j + " = D.unitData(DataSource.class, \"input" + j + "\");\n" );
                    sb.append( "        Variable<Object> var_$pattern$" + j + "$ = D.declarationOf(Object.class, \"$pattern$" + j + "$\", var_input" + j + ");\n" );
                }

                sb.append( "\n" );
                sb.append( "        return D.rule(\"" + pkgName + "\", \"" + clasName + "_" + i + "\")\n" );
                sb.append( "                .unit(" + pkgName + "." + clasName + "DTUnit.class)\n" );
                sb.append( "                .build( \n" );

                for (int j = 0; j < dTableModel.getInputs().size(); j++) {
                    sb.append( "                       D.pattern(var_$pattern$" + j + "$).expr(TEST_ARRAY[" + i + "][" + j + "].getClass().getSimpleName(),\n" );
                    sb.append( "                           (_this) -> unaryTestsOr( TEST_ARRAY[" + i + "][" + j + "].getUnaryTests() ).apply( null, _this )),\n" );
                }

                sb.append( "                       D.on( " );
                sb.append( IntStream.range( 0, dTableModel.getOutputsNr() ).mapToObj( j -> "var_output" + j ).collect( joining(", ") ) );
                sb.append( " ).execute((" );
                sb.append( IntStream.range( 0, dTableModel.getOutputsNr() ).mapToObj( j -> "output" + j ).collect( joining(", ") ) );
                sb.append( " ) -> {\n" );
                for (int j = 0; j < dTableModel.getOutputsNr(); j++) {
                    sb.append( "                            output" + j + ".insert(" + row.getOutputs().get(j) + ");\n" );
                }

                sb.append( "                       }\n" );
                sb.append( "        ));\n" );
                sb.append( "    }\n" );
            }

            sb.append( "}\n" );

            String source = sb.toString();
            logger.debug(clasName + ":\n" + source);
            return source;
        }
    }

    public static class UnitSourceGenerator implements SourceGenerator {
        public String generate( DTableModel dTableModel ) {
            String pkgName = dTableModel.getNamespace();
            String clasName = dTableModel.getTableName();

            StringBuilder sb = new StringBuilder();
            sb.append( "package " ).append( pkgName ).append( ";\n" );
            sb.append( "\n" );
            sb.append( "import " ).append( DataSource.class.getCanonicalName() ).append( ";\n" );
            sb.append( "\n" );
            sb.append( "public class " ).append( clasName ).append( "DTUnit extends " + DMNUnit.class.getCanonicalName() + " {\n" );

            for (int i = 0; i < dTableModel.getInputs().size(); i++) {
                sb.append( "\n" );
                sb.append( "    private DataSource<Object> input" ).append( i ).append( ";\n" );
                sb.append( "    public DataSource<Object> getInput" ).append( i ).append( "() {\n" );
                sb.append( "        return input" ).append( i ).append( ";\n" );
                sb.append( "    }\n" );
            }

            sb.append( "\n" );
            sb.append( "    private DataSource<Object> output0 = DataSource.create();\n" );
            sb.append( "    public DataSource<Object> getOutput0() {\n" );
            sb.append( "        return output0;\n" );
            sb.append( "    }\n" );

            sb.append( "\n" );
            sb.append( "    @Override\n" );
            sb.append( "    public void onStart() {\n" );
            for (int i = 0; i < dTableModel.getInputs().size(); i++) {
                sb.append( "        input" ).append( i ).append( " = DataSource.create( getValue(" ).append( i ).append( ") );\n" );
            }
            sb.append( "    }\n" );

            sb.append( "\n" );
            sb.append( "    @Override\n" );
            sb.append( "    public void onEnd() {\n" );
            sb.append( "        result = output0.iterator().next();\n" );
            sb.append( "    }\n" );
            sb.append( "}\n" );

            String source = sb.toString();
            logger.debug(clasName + ":\n" + source);
            return source;
        }
    }

    public static class UnaryTestsSourceGenerator implements SourceGenerator {
        public String generate( DTableModel dTableModel ) {
            String pkgName = dTableModel.getNamespace();
            String clasName = dTableModel.getTableName();

            StringBuilder sb = new StringBuilder();
            sb.append( "package " ).append( pkgName ).append( ";\n" );
            sb.append( "\n" );
            sb.append( "import java.util.List;\n" );
            sb.append( "import org.kie.dmn.feel.codegen.feel11.CompiledFEELUnaryTests;\n" );
            sb.append( "import org.kie.dmn.feel.runtime.UnaryTest;\n" );
            sb.append( "import static org.kie.dmn.feel.codegen.feel11.CompiledFEELSemanticMappings.*;\n" );
            sb.append( "\n" );
            sb.append( "public class " ).append( clasName ).append( "UnaryTests {\n" );
            sb.append( getUnaryTestsSource(dTableModel, pkgName, clasName) );
            sb.append( "}\n" );

            String source = sb.toString();
            logger.debug(clasName + ":\n" + source);
            return source;
        }

        public String getUnaryTestsSource( DTableModel dTableModel, String pkgName, String className ) {
            Map<String, String> testClassesByInput = new HashMap<>();
            StringBuilder sb = new StringBuilder();
            sb.append( "    public static final CompiledFEELUnaryTests[][] TEST_ARRAY = new CompiledFEELUnaryTests[][] {\n" );

            StringBuilder testsBuilder = new StringBuilder();
            for (int i = 0; i < dTableModel.getRows().size(); i++) {
                sb.append( "            { " );
                DTableModel.DRowModel row = dTableModel.getRows().get(i);
                for (int j = 0; j < row.getInputs().size(); j++) {
                    String input = row.getInputs().get(j);
                    String testClass = testClassesByInput.get(input);
                    if (testClass == null) {
                        testClass = className + "r" + i + "c" + j;
                        testClassesByInput.put(input, testClass);
                        testsBuilder.append( "\n" );
                        testsBuilder.append( FeelUtil.getSourceForUnaryTest( pkgName, testClass, input ) );
                        testsBuilder.append( "\n" );
                    }
                    sb.append( "new " ).append( testClass ).append( "()" );
                    if (j < row.getInputs().size()-1) {
                        sb.append( ", " );
                    }
                }
                if (i < dTableModel.getRows().size()-1) {
                    sb.append( " },\n" );
                } else {
                    sb.append( " }\n" );
                }
            }

            sb.append( "    };\n" );
            sb.append( testsBuilder );

            return sb.toString();
        }
    }
}

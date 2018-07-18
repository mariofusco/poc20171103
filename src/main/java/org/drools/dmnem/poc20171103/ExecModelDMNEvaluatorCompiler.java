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

package org.drools.dmnem.poc20171103;

import java.lang.reflect.Field;
import java.util.List;
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
import org.kie.dmn.feel.codegen.feel11.CompiledFEELExpression;
import org.kie.dmn.model.v1_1.DecisionTable;
import temp.kiedmn.DMNUnit;

import static org.drools.modelcompiler.builder.JavaParserCompiler.getCompiler;

public class ExecModelDMNEvaluatorCompiler extends DMNEvaluatorCompilerImpl {

    private static final String[] CLASSES = new String[] { "Evaluator", "DTUnit", "ExecModel", "UnaryTests" };
    private static final SourceGenerator[] SOURCE_GENERATORS = new SourceGenerator[] {
            new EvaluatorSourceGenerator(), new UnitSourceGenerator(), new ExecModelSourceGenerator(), new UnaryTestsSourceGenerator() };

    public ExecModelDMNEvaluatorCompiler( DMNCompilerImpl compiler, DMNFEELHelper feel ) {
        super( compiler, feel );
    }

    @Override
    protected DMNExpressionEvaluator compileDecisionTable( DMNCompilerContext ctx, DMNModelImpl model, DMNBaseNode node, String dtName, DecisionTable dt ) {
//        if (true) return super.compileDecisionTable( ctx, model, node, dtName, dt );
        DTableModel dTableModel = new DTableModel( model.getNamespace(), dtName, dt );
        List<CompiledFEELExpression> compiledExprs = dTableModel.getFeelExpressionsForInputs( ctx );
        String camelName = toCamelCase(dtName);
        String pkgName = "org.drools.dmnem.poc20171103";
        AbstractModelEvaluator evaluator = generateEvaluator(dTableModel, pkgName, camelName);
        evaluator.initParameters(model, node, dt, dtName, compiledExprs);
        return evaluator;
    }

    public AbstractModelEvaluator generateEvaluator(DTableModel dTableModel, String pkgName, String camelName) {
        ProjectClassLoader projectClassLoader = ProjectClassLoader.createProjectClassLoader();

        MemoryFileSystem srcMfs = new MemoryFileSystem();
        MemoryFileSystem trgMfs = new MemoryFileSystem();
        String[] sources = new String[4];

        for (int i = 0; i < 4; i++) {
            String className = pkgName + "." + camelName + CLASSES[i];
            sources[i] = "src/main/java" + className.replace( '.', '/' ) + ".java";
            String javaSource = SOURCE_GENERATORS[i].generate( dTableModel, pkgName, camelName );
            srcMfs.write( sources[i], javaSource.getBytes() );
        }

        CompilationResult res = getCompiler().compile(sources, srcMfs, trgMfs, projectClassLoader);

        CompilationProblem[] errors = res.getErrors();
        if (errors != null && errors.length > 0) {
            Stream.of(errors).forEach( System.err::println );
            throw new RuntimeException();
        }

        try {
            Class<?> evalClass = projectClassLoader.loadClass( pkgName + "." + camelName + CLASSES[0] );
            return (AbstractModelEvaluator) evalClass.newInstance();
        } catch (Exception e) {
            throw new UnsupportedOperationException( "Unknown decision table: " + camelName, e );
        }
    }

    interface SourceGenerator {
        String generate(DTableModel dTableModel, String pkgName, String camelName);
    }

    public static class EvaluatorSourceGenerator implements SourceGenerator {
        public String generate(DTableModel dTableModel, String pkgName, String camelName) {
            StringBuilder sb = new StringBuilder();
            sb.append( "package " ).append( pkgName ).append( ";\n" );
            sb.append( "import java.util.List;\n" );
            sb.append( "import org.drools.model.Rule;\n" );
            sb.append( "import " ).append( DMNUnit.class.getCanonicalName() ).append( ";\n" );
            sb.append( "public class " ).append( camelName ).append( "Evaluator extends AbstractModelEvaluator {\n" );

            sb.append( "@Override\n" );
            sb.append( "protected List<Rule> getRules() {\n" );
            sb.append( "return " ).append( camelName ).append( "ExecModel.getRules();\n" );
            sb.append( "}\n" );

            sb.append( "@Override\n" );
            sb.append( "protected DMNUnit getDMNUnit() {\n" );
            sb.append( "return new " ).append( camelName ).append( "DTUnit();\n" );
            sb.append( "}\n" );
            sb.append( "}\n" );

            return sb.toString();
        }
    }

    public static class ExecModelSourceGenerator implements SourceGenerator {
        public String generate( DTableModel dTableModel, String pkgName, String camelName ) {
            return getField( pkgName, camelName, "EXEC_MODEL" );
        }
    }

    public static class UnitSourceGenerator implements SourceGenerator {
        public String generate( DTableModel dTableModel, String pkgName, String camelName ) {
            StringBuilder sb = new StringBuilder();
            sb.append( "package " ).append( pkgName ).append( ";\n" );
            sb.append( "import " ).append( DataSource.class.getCanonicalName() ).append( ";\n" );
            sb.append( "import " ).append( DMNUnit.class.getCanonicalName() ).append( ";\n" );
            sb.append( "public class " ).append( camelName ).append( "DTUnit extends DMNUnit {\n" );

            for (int i = 0; i < dTableModel.getInputs().size(); i++) {
                sb.append( "private DataSource<Object> input" ).append( i ).append( ";\n" );
                sb.append( "public DataSource<Object> getInput" ).append( i ).append( "() {\n" );
                sb.append( "return input" ).append( i ).append( ";\n" );
                sb.append( "}\n" );
            }

            sb.append( "private DataSource<Object> output0 = DataSource.create();\n" );
            sb.append( "public DataSource<Object> getOutput0() {\n" );
            sb.append( "return output0;\n" );
            sb.append( "}\n" );

            sb.append( "@Override\n" );
            sb.append( "public void onStart() {\n" );
            for (int i = 0; i < dTableModel.getInputs().size(); i++) {
                sb.append( "input" ).append( i ).append( " = DataSource.create( getValue(" ).append( i ).append( ") );\n" );
            }
            sb.append( "}\n" );

            sb.append( "@Override\n" );
            sb.append( "public void onEnd() {\n" );
            sb.append( "result = output0.iterator().next();\n" );
            sb.append( "}\n" );
            sb.append( "}\n" );

            return sb.toString();
        }
    }

    public static class UnaryTestsSourceGenerator implements SourceGenerator {
        public String generate( DTableModel dTableModel, String pkgName, String camelName ) {
            StringBuilder sb = new StringBuilder();
            sb.append( "package " ).append( pkgName ).append( ";\n" );
            sb.append( "import java.util.List;\n" );
            sb.append( "import org.kie.dmn.feel.codegen.feel11.CompiledFEELUnaryTests;\n" );
            sb.append( "import org.kie.dmn.feel.runtime.UnaryTest;\n" );
            sb.append( "import static org.kie.dmn.feel.codegen.feel11.CompiledFEELSemanticMappings.*;\n" );
            sb.append( "public class " ).append( camelName ).append( "UnaryTests {\n" );
            sb.append( dTableModel.getUnaryTestsSource() );
            sb.append( "}\n" );
            return sb.toString();
        }
    }

    private static String getField( String pkgName, String camelName, String fieldName ) {
        String className = camelName + "Sources";
        try {
            Class<?> evalClass = Class.forName( pkgName + "." + className );
            Field field = evalClass.getField( fieldName );
            return (String) field.get( null );
        } catch (Exception e) {
            throw new RuntimeException( e );
        }
    }

    private static String toCamelCase(String name) {
        StringBuilder sb = new StringBuilder();
        for (String word : name.split( "\\s" )) {
            if (word.isEmpty()) {
                continue;
            }
            sb.append( Character.toUpperCase( word.charAt( 0 ) ) );
            if (word.length() > 1) {
                sb.append( word.substring( 1 ) );
            }
        }
        return sb.toString();
    }

}

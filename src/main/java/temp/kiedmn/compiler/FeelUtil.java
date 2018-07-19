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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTree;
import org.drools.javaparser.ast.expr.Expression;
import org.kie.dmn.core.compiler.profiles.ExtendedDMNProfile;
import org.kie.dmn.feel.FEEL;
import org.kie.dmn.feel.codegen.feel11.CompiledFEELExpression;
import org.kie.dmn.feel.codegen.feel11.CompilerBytecodeLoader;
import org.kie.dmn.feel.codegen.feel11.DirectCompilerResult;
import org.kie.dmn.feel.codegen.feel11.DirectCompilerVisitor;
import org.kie.dmn.feel.lang.CompilerContext;
import org.kie.dmn.feel.lang.EvaluationContext;
import org.kie.dmn.feel.lang.Type;
import org.kie.dmn.feel.parser.feel11.FEELParser;
import org.kie.dmn.feel.parser.feel11.FEEL_1_1Parser;
import org.kie.dmn.feel.parser.feel11.profiles.DoCompileFEELProfile;
import org.kie.dmn.feel.runtime.UnaryTest;

import static java.util.stream.Collectors.toList;

public class FeelUtil {

    public static final FEEL feel = FEEL.newInstance( Arrays.asList(new DoCompileFEELProfile(), new ExtendedDMNProfile()) );

    public static CompiledFEELExpression asFeelExpression(String text) {
        return asFeelExpression( text, feel.newCompilerContext() );
    }

    public static CompiledFEELExpression asFeelExpression( String text, CompilerContext context ) {
        return (CompiledFEELExpression) feel.compile( text, context );
    }

    public List<Object> getOutputValues( List<CompiledFEELExpression> feelExpressions, EvaluationContext ctx ) {
        return feelExpressions.stream().map( expr -> expr.apply( ctx ) ).collect( toList() );
    }

    public static UnaryTest or( List<UnaryTest> fs ) {
        return (a,b) -> fs.stream().anyMatch( f -> f.apply( a,b ) );
    }

    public static String getSourceForUnaryTest(String packageName, String className, String input) {
        Map<String, Type> inputTypes = Collections.emptyMap();
        FEEL_1_1Parser parser = FEELParser.parse(null, input, inputTypes, Collections.emptyMap(), Collections.emptyList(), Collections.emptyList());

        ParseTree tree = parser.expressionList();

        DirectCompilerVisitor v = new DirectCompilerVisitor(inputTypes, true);
        DirectCompilerResult directResult = v.visit(tree);

        Expression expr = directResult.getExpression();
        return new CompilerBytecodeLoader().getSourceForUnaryTest(packageName, className, input, expr, directResult.getFieldDeclarations());
    }
}

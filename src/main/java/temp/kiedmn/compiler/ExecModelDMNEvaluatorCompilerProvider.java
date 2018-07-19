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

import org.kie.dmn.core.compiler.DMNCompilerImpl;
import org.kie.dmn.core.compiler.DMNEvaluatorCompiler;
import org.kie.dmn.core.compiler.DMNEvaluatorCompilerProvider;
import org.kie.dmn.core.compiler.DMNFEELHelper;

public class ExecModelDMNEvaluatorCompilerProvider implements DMNEvaluatorCompilerProvider {
    @Override
    public DMNEvaluatorCompiler create( DMNCompilerImpl compiler, DMNFEELHelper feel ) {
        return new ExecModelDMNEvaluatorCompiler( compiler, feel );
    }
}

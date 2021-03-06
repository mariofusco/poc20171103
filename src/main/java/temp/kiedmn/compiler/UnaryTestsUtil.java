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

import java.util.List;

import org.kie.dmn.feel.runtime.UnaryTest;

public class UnaryTestsUtil {
    public static UnaryTest unaryTestsOr( List<UnaryTest> fs ) {
        return fs.size() == 1 ? fs.get(0) : (a,b) -> fs.stream().anyMatch( f -> f.apply( a,b ) );
    }
}

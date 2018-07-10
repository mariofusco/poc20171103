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

import java.util.UUID;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.ReleaseId;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.runtime.KieContainer;

public class ModelUtil {

    public static KieContainer getKieContainerForDrls( Class<? extends KieBuilder.ProjectType> projectType, String... stringRules ) {
        return getKieContainerForDrls( projectType, null, stringRules );
    }

    public static KieContainer getKieContainerForDrls( Class<? extends KieBuilder.ProjectType> projectType, KieModuleModel model, String... stringRules ) {
        KieServices ks = KieServices.get();
        ReleaseId releaseId = ks.newReleaseId( "org.kie", "kjar-test-" + UUID.randomUUID(), "1.0" );

        KieBuilder kieBuilder = createKieBuilder( ks, model, releaseId, projectType, stringRules );
        return ks.newKieContainer( releaseId );
    }

    public static KieBuilder createKieBuilder( KieServices ks, KieModuleModel model, ReleaseId releaseId, Class<? extends KieBuilder.ProjectType> projectType, String... stringRules ) {
        ks.getRepository().removeKieModule( releaseId );

        KieFileSystem kfs = ks.newKieFileSystem();
        if ( model != null ) {
            kfs.writeKModuleXML( model.toXML() );
        }
        kfs.writePomXML( getPom( releaseId ) );
        for (int i = 0; i < stringRules.length; i++) {
            kfs.write( ks.getResources().newClassPathResource( stringRules[i] ) );
        }

        return ks.newKieBuilder(kfs).buildAll(projectType);
    }

    public static String getPom(ReleaseId releaseId) {
        String pom =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd\">\n" +
                        "  <modelVersion>4.0.0</modelVersion>\n" +
                        "\n" +
                        "  <groupId>" + releaseId.getGroupId() + "</groupId>\n" +
                        "  <artifactId>" + releaseId.getArtifactId() + "</artifactId>\n" +
                        "  <version>" + releaseId.getVersion() + "</version>\n" +
                        "</project>";
        return pom;
    }
}

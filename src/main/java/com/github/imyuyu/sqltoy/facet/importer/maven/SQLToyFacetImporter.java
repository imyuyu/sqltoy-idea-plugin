package com.github.imyuyu.sqltoy.facet.importer.maven;

import com.github.imyuyu.sqltoy.facet.SQLToyFacet;
import com.github.imyuyu.sqltoy.facet.SQLToyFacetConfiguration;
import com.github.imyuyu.sqltoy.facet.SQLToyFacetType;
import com.intellij.facet.FacetType;
import com.intellij.openapi.externalSystem.service.project.IdeModifiableModelsProvider;
import com.intellij.openapi.module.Module;
import java.util.List;
import java.util.Map;
import org.jetbrains.idea.maven.importing.FacetImporter;
import org.jetbrains.idea.maven.importing.MavenRootModelAdapter;
import org.jetbrains.idea.maven.model.MavenArtifact;
import org.jetbrains.idea.maven.project.MavenProject;
import org.jetbrains.idea.maven.project.MavenProjectChanges;
import org.jetbrains.idea.maven.project.MavenProjectsProcessorTask;
import org.jetbrains.idea.maven.project.MavenProjectsTree;

/**
 * sqltoy maven facet importer
 */
public class SQLToyFacetImporter extends FacetImporter<SQLToyFacet, SQLToyFacetConfiguration, SQLToyFacetType> {
    public SQLToyFacetImporter() {
        super("dummy", "dummy", FacetType.findInstance(SQLToyFacetType.class));
    }

    public boolean isApplicable(MavenProject mavenProject) {
        if (mavenProject.isAggregator()) {
            return false;
        } else {
            List<MavenArtifact> dependencies = mavenProject.findDependencies("com.sagframe", "sagacity-sqltoy");
            return !dependencies.isEmpty();
        }
    }

    protected boolean isDisableFacetAutodetection(Module module) {
        return false;
    }

    @Override
    protected void setupFacet(SQLToyFacet f, MavenProject mavenProject) {

    }

    @Override
    protected void reimportFacet(IdeModifiableModelsProvider modelsProvider, Module module, MavenRootModelAdapter rootModel, SQLToyFacet facet, MavenProjectsTree mavenTree, MavenProject mavenProject, MavenProjectChanges changes, Map<MavenProject, String> mavenProjectToModuleName, List<MavenProjectsProcessorTask> postTasks) {

    }
}

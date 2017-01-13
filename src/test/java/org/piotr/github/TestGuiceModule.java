package org.piotr.github;

import com.google.inject.AbstractModule;
import org.piotr.github.model.service.GitHubRepositoryService;
import org.piotr.github.model.service.RepositoryService;
import org.piotr.github.resource.RepositoryResource;
import org.piotr.github.utils.PropertiesReader;


public class TestGuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(RepositoryResource.class);
        bind(RepositoryService.class).to(GitHubRepositoryService.class);
        PropertiesReader propertiesReader = new PropertiesReader(getClass().getResourceAsStream("/config.properties"));
        bind(PropertiesReader.class).toInstance(propertiesReader);
    }
}

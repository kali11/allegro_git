package org.piotr.github;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import org.piotr.github.model.github.GitHubConnector;
import org.piotr.github.model.github.VCSConnector;
import org.piotr.github.model.service.GitHubRepositoryService;
import org.piotr.github.model.service.RepositoryService;

public class GuiceServletConfig extends GuiceServletContextListener {
    public static Injector injector;

    @Override
    protected Injector getInjector() {
        injector = Guice.createInjector(new ServletModule() {
            @Override
            public void configureServlets() {
                bind(RepositoryService.class).to(GitHubRepositoryService.class);
                bind(VCSConnector.class).to(GitHubConnector.class);
            }
        });
        return injector;
    }
}

package org.piotr.github;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import org.piotr.github.model.github.GitHubConnector;
import org.piotr.github.model.github.VCSConnector;
import org.piotr.github.model.service.GitHubRepositoryService;
import org.piotr.github.model.service.RepositoryService;

import javax.servlet.ServletContextEvent;
import java.io.InputStream;
import java.util.Properties;

public class GuiceServletConfig extends GuiceServletContextListener {
    public static Injector injector;
    private Properties properties;

    @Override
    public void contextInitialized(ServletContextEvent context) {
        System.out.println("qweasdzcx");
        InputStream in = context.getServletContext().getResourceAsStream("/WEB-INF/config.properties");
        properties = new Properties();
        try {
            properties.load(in);
        } catch (Exception e) {
            System.out.println(e);
        }

        super.contextInitialized(context);
    }

    @Override
    protected Injector getInjector() {
        injector = Guice.createInjector(new ServletModule() {
            @Override
            public void configureServlets() {
                bind(RepositoryService.class).to(GitHubRepositoryService.class);
                bind(GitHubConnector.class).toInstance(supplyGitHubConnector());
            }
        });
        return injector;
    }

    private GitHubConnector supplyGitHubConnector() {
        GitHubConnector gitHubConnector = new GitHubConnector(properties.getProperty("api.url"));
        return gitHubConnector;
    }
}

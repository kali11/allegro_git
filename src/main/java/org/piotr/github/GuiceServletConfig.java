package org.piotr.github;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import org.piotr.github.model.service.GitHubRepositoryService;
import org.piotr.github.model.service.RepositoryService;
import org.piotr.github.utils.PropertiesReader;

import javax.servlet.ServletContextEvent;
import java.io.InputStream;

public class GuiceServletConfig extends GuiceServletContextListener {
    public static Injector injector;
    private PropertiesReader propertiesReader;

    @Override
    public void contextInitialized(ServletContextEvent context) {
        InputStream in = context.getServletContext().getResourceAsStream("/WEB-INF/config.properties");
        propertiesReader = new PropertiesReader(in);
        super.contextInitialized(context);
    }

    @Override
    protected Injector getInjector() {
        injector = Guice.createInjector(new ServletModule() {
            @Override
            public void configureServlets() {
                bind(RepositoryService.class).to(GitHubRepositoryService.class);
                //bind(GitHubConnector.class).toInstance(supplyGitHubConnector());
                bind(PropertiesReader.class).toInstance(propertiesReader);
            }
        });
        return injector;
    }

//    private GitHubConnector supplyGitHubConnector() {
//        GitHubConnector gitHubConnector = new GitHubConnector(properties.getProperty("api.url"));
//        return gitHubConnector;
//    }
}

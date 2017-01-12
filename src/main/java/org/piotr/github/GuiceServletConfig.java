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

/**
 * Google Guice configuration class
 */
public class GuiceServletConfig extends GuiceServletContextListener {
    private static final String PROPERTIES_FILE = "/WEB-INF/config.properties";
    private PropertiesReader propertiesReader;
    public static Injector injector;

    @Override
    public void contextInitialized(ServletContextEvent context) {
        InputStream in = context.getServletContext().getResourceAsStream(PROPERTIES_FILE);
        propertiesReader = new PropertiesReader(in);
        super.contextInitialized(context);
    }

    @Override
    protected Injector getInjector() {
        injector = Guice.createInjector(new ServletModule() {
            @Override
            public void configureServlets() {
                // here we can define guice bindings
                bind(RepositoryService.class).to(GitHubRepositoryService.class);
                bind(PropertiesReader.class).toInstance(propertiesReader);
            }
        });
        return injector;
    }
}

package org.piotr.github.resource;

import org.apache.log4j.Logger;
import org.piotr.github.model.pojo.RepoDetails;
import org.piotr.github.model.service.GitHubRepositoryService;
import org.piotr.github.model.service.RepositoryService;
import org.piotr.github.utils.PropertiesReader;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.util.Properties;

/**
 * Root VCS repositories resource.
 */
@Path("repositories")
public class RepositoryResource {

    @Inject
    private RepositoryService repositoryService;

    @Inject
    private PropertiesReader propertiesReader;

    private Logger logger = Logger.getLogger(getClass().getName());

    @GET
    @Path("/{owner}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public RepoDetails getIt(@PathParam("owner") String owner,
                             @PathParam("repository-name") String repositoryName) {
        return repositoryService.getRepositoryDetails(owner, repositoryName);
    }
}

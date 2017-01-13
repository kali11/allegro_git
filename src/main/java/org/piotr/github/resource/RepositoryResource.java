package org.piotr.github.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.piotr.github.model.mapper.GitHubRepositoryNotFoundException;
import org.piotr.github.model.pojo.RepoDetails;
import org.piotr.github.model.service.RepositoryService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Root VCS repositories resource.
 */
@Path("repositories")
public class RepositoryResource {

    private final Logger logger = LogManager.getLogger(getClass().getName());

    @Inject
    private RepositoryService repositoryService;

    /**
     * Returns detailed information about specific VCS repository
     * @param owner - repository's owner
     * @param repositoryName - repository's name
     * @return RepoDetails
     */
    @GET
    @Path("/{owner}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt(@PathParam("owner") String owner,
                             @PathParam("repository-name") String repositoryName) {
        Response response;
        try {
            RepoDetails repoDetails = repositoryService.getRepositoryDetails(owner, repositoryName);
            response = Response.ok(repoDetails, MediaType.APPLICATION_JSON).build();
        } catch (GitHubRepositoryNotFoundException e) {
            logger.warn("cannot find repository: {} for owner: {}. Response from API: {}",
                    repositoryName,  owner, e.getMessage());
            response = Response.noContent().build();
        }
        return response;
    }
}

package org.piotr.github.model.connector;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.piotr.github.model.mapper.GitHubJacksonObjectMapperProvider;
import org.piotr.github.model.pojo.RepoDetails;

import javax.inject.Singleton;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * Class that handles connection to GitHubAPI
 *
 * @see <a href="https://api.github.com/v3">GirHubAPI</a>
 */
@Singleton
public class GitHubConnector implements VCSConnector {
    private final Logger logger = LogManager.getLogger(getClass().getName());
    private final GitHubJacksonObjectMapperProvider provider = new GitHubJacksonObjectMapperProvider();

    /**
     * Sends a http request to GitHubAPI. For Example: https://api.github.com/v3/repos/{owner}/{repoName}
     *
     * @param apiUrl   - url to GitHubAPI
     * @param repoPath - path for requesting repositories
     * @param owner    - repository's owner
     * @param repoName - repository's name
     * @return RepoDetails
     */
    @Override
    public RepoDetails getRepositoryDetails(String apiUrl, String repoPath, String owner, String repoName) {
        Client client = ClientBuilder.newClient()
                .register(provider);
        WebTarget target = client.target(apiUrl)
                .path(repoPath)
                .path(owner)
                .path(repoName);
        logger.info("sending request to: {}", target.getUri().toString());
        Response response = target.request().get();
//        RepoDetails result;
//        try {
//            result = response.readEntity(RepoDetails.class);
//        } catch (NullPointerException e) {
//            throw new DeserializationException(response.readEntity(String.class), e);
//        }
        return response.readEntity(RepoDetails.class);
    }
}

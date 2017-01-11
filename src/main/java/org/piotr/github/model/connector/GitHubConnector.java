package org.piotr.github.model.connector;


import org.piotr.github.model.mapper.GitHubJacksonObjectMapperProvider;
import org.piotr.github.model.pojo.RepoDetails;

import javax.inject.Singleton;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

@Singleton
public class GitHubConnector implements VCSConnector {
    private final GitHubJacksonObjectMapperProvider provider = new GitHubJacksonObjectMapperProvider();

    @Override
    public RepoDetails getRepositoryDetails(String apiUrl, String repoPath, String owner, String repoName) {
        Client client = ClientBuilder.newClient()
                .register(provider);
        Response response = client.target(apiUrl)
                .path(repoPath)
                .path(owner)
                .path(repoName)
                .request()
                .get();
        return response.readEntity(RepoDetails.class);
    }
}

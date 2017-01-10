package org.piotr.github.model.github;


import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.piotr.github.model.mapper.GitHubJacksonObjectMapperProvider;

import javax.inject.Singleton;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

@Singleton
public class GitHubConnector extends VCSConnector {
    private static final String API_URL = "https://api.github.com";
    private static final String LOGIN = "kali11";
    private static final String TOKEN = "c6811793e6f9a122aed3e60a8c3b966762e60b46";
    private static final String REPO_PATH = "repos";

    public GitHubConnector(String url) {
        super(url);
    }

    @Override
    public Response getRepositoryDetails(String owner, String repoName) {
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder()
                .nonPreemptive()
                .credentials(LOGIN, TOKEN)
                .build();
        return ClientBuilder.newClient()
                .register(feature)
                //.register(new GitHubJacksonObjectMapperProvider())
                .target(this.getApiUrl())
                .path(REPO_PATH)
                .path(owner)
                .path(repoName)
                .request()
                .get();
    }
}

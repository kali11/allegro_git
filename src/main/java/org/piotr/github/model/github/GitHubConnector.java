package org.piotr.github.model.github;


import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.inject.Singleton;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

@Singleton
public class GitHubConnector implements VCSConnector {
    private static final String API_URL = "https://api.github.com";
    private static final String LOGIN = "kali11";
    private static final String TOKEN = "24d5966ec75581c9b53dda29094362e7f897008a";

    @Override
    public Response getRepositoryDetails(String owner, String repoName) {
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder()
                .nonPreemptive()
                .credentials(LOGIN, TOKEN)
                .build();
        return ClientBuilder.newClient()
                .register(feature)
                .target(API_URL)
                .path("repos")
                .path(owner)
                .path(repoName)
                .request()
                .get();
    }
}

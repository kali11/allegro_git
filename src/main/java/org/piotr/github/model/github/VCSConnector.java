package org.piotr.github.model.github;


import javax.ws.rs.core.Response;

public abstract class VCSConnector {
    private final String apiUrl;
    protected VCSConnector(String apiUrl) {
        this.apiUrl = apiUrl;
    }
    public abstract Response getRepositoryDetails(String owner, String repoName);

    public String getApiUrl() {
        return apiUrl;
    }
}

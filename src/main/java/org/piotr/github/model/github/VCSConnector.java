package org.piotr.github.model.github;


import javax.ws.rs.core.Response;

public interface VCSConnector {
    Response getRepositoryDetails(String owner, String repoName);
}

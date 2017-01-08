package org.piotr.github.model.service;

import org.piotr.github.model.github.VCSConnector;
import org.piotr.github.model.pojo.RepoDetails;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.Response;

@Singleton
public class GitHubRepositoryService implements RepositoryService {

    @Inject
    private VCSConnector connector;

    @Override
    public RepoDetails getRepositoryDetails(String owner, String repoName) {
        Response response = connector.getRepositoryDetails(owner, repoName);
        return response.readEntity(RepoDetails.class);
    }
}

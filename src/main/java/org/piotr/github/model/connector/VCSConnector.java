package org.piotr.github.model.connector;


import org.piotr.github.model.pojo.RepoDetails;

public interface VCSConnector {
    RepoDetails getRepositoryDetails(String apiUrl, String repoPath, String owner, String repoName);
}

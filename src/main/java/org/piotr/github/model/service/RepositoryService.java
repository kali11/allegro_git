package org.piotr.github.model.service;


import org.piotr.github.model.pojo.RepoDetails;

public interface RepositoryService {
    RepoDetails getRepositoryDetails(String owner, String repoName);
}

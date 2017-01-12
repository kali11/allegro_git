package org.piotr.github.model.service;

import org.piotr.github.model.connector.GitHubConnector;
import org.piotr.github.model.pojo.RepoDetails;
import org.piotr.github.utils.PropertiesReader;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * A class that is a service for acquiring data about GitHub respositories
 */
@Singleton
public class GitHubRepositoryService implements RepositoryService {

    @Inject
    private GitHubConnector connector;

    private final String apiUrl;
    private final String repoPath;

    @Inject
    public GitHubRepositoryService(PropertiesReader propertiesReader) {
        apiUrl = propertiesReader.getProperty("api.url");
        repoPath = propertiesReader.getProperty("api.repoPath");
    }

    /**
     * Returns GitHub repository details
     * @param owner - repository's owner
     * @param repoName - repository's name
     * @return RepoDetails
     */
    @Override
    public RepoDetails getRepositoryDetails(String owner, String repoName) {
        return connector.getRepositoryDetails(apiUrl, repoPath, owner, repoName);
    }
}

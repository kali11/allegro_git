package org.piotr.github.model.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.piotr.github.model.github.GitHubConnector;
import org.piotr.github.model.github.VCSConnector;
import org.piotr.github.model.pojo.RepoDetails;
import org.piotr.github.model.pojo.RepoDetailsDeserializer;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.Response;

@Singleton
public class GitHubRepositoryService implements RepositoryService {

    @Inject
    private GitHubConnector connector;

    @Override
    public RepoDetails getRepositoryDetails(String owner, String repoName) {
        Response response = connector.getRepositoryDetails(owner, repoName);
        //ObjectMapper objectMapper = new ObjectMapper();
        //SimpleModule module = new SimpleModule();
        //module.addDeserializer(RepoDetails.class, new RepoDetailsDeserializer());
        //objectMapper.registerModule(module);
        //objectMapper.readValue(response.re, RepoDetails.class);
        return response.readEntity(RepoDetails.class);
    }
}

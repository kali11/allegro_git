package org.piotr.github.model.service;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.piotr.github.model.connector.GitHubConnector;
import org.piotr.github.model.pojo.RepoDetails;
import org.piotr.github.utils.PropertiesReader;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GitHubRepositoryServiceTest {

    @Mock
    private GitHubConnector connector;

    @Mock
    private PropertiesReader propertiesReader;

    @InjectMocks
    private GitHubRepositoryService gitHubRepositoryService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnProperRepoDetails() {
        // given
        String owner = "Owner";
        String repoName = "name";
        String cloneUrl = "http://cloneUrl.com";
        Long stars = 3L;
        String description = "description";
        String creationDate = "2015-01-01T10:00:00Z";
        RepoDetails expected = new RepoDetails();
        expected.setFullName(repoName);
        expected.setCloneUrl(cloneUrl);
        expected.setStars(stars);
        expected.setDescription(description);
        expected.setCreatedAt(creationDate);
        when(connector.getRepositoryDetails(anyString(), anyString(), anyString(), anyString())).thenReturn(expected);

        // when
        RepoDetails actual = gitHubRepositoryService.getRepositoryDetails(owner, repoName);

        // then
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }
}

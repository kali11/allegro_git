package org.piotr.github.resource;


import com.carlosbecker.guice.GuiceModules;
import com.carlosbecker.guice.GuiceTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.piotr.github.TestGuiceModule;
import org.piotr.github.model.pojo.RepoDetails;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 * IMPORTANT:
 * This is an integration test class. It tests whole resource without any mocks.
 * As a consequence, this test depends on GitHub API availability.
 */
@RunWith(GuiceTestRunner.class)
@GuiceModules(TestGuiceModule.class)
public class RepositoryResourceTest {

    @Inject
    private RepositoryResource repositoryResource;

    @Test
    public void shouldReturnProperRepoDetails() {
        // given
        String repoName = "ipython";
        String owner = "ipython";
        String fullName = "ipython/ipython";
        String cloneUrl = "https://github.com/ipython/ipython.git";
        String createdDate = "2010-05-10";

        // when
        Response response = repositoryResource.getIt(owner, repoName);

        // then
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
        RepoDetails repoDetails = (RepoDetails) response.getEntity();
        Assert.assertNotNull(repoDetails);
        Assert.assertEquals(createdDate, repoDetails.getCreatedAt());
        Assert.assertEquals(fullName, repoDetails.getFullName());
        Assert.assertEquals(cloneUrl, repoDetails.getCloneUrl());
    }

    @Test
    public void shouldReturn204() {
        // given
        String owner = "notExistingOwner";
        String repoName = "notExistingRepo";

        // when
        Response response = repositoryResource.getIt(owner, repoName);

        // then
        Assert.assertNotNull(response);
        Assert.assertEquals(204, response.getStatus());
    }
}

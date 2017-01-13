package org.piotr.github.model.mapper;

/**
 * This exception is thrown when given repository doesn't exist on GitHub
 */
public class GitHubRepositoryNotFoundException extends RuntimeException {

    public GitHubRepositoryNotFoundException(String message) {
        super(message);
    }

    public GitHubRepositoryNotFoundException(Throwable cause) {
        super(cause);
    }

    public GitHubRepositoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}

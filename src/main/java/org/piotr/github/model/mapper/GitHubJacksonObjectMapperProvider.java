package org.piotr.github.model.mapper;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.piotr.github.model.pojo.RepoDetails;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * This is a provider for GitHubRepoDetailsDeserializer
 */
@Provider
public class GitHubJacksonObjectMapperProvider implements ContextResolver<ObjectMapper> {
    final ObjectMapper defaultObjectMapper;

    public GitHubJacksonObjectMapperProvider() {
        defaultObjectMapper = createDefaultMapper();
    }

    public static ObjectMapper createDefaultMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(RepoDetails.class, new GitHubRepoDetailsDeserializer());
        objectMapper.registerModule(module);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return objectMapper;
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return defaultObjectMapper;
    }
}

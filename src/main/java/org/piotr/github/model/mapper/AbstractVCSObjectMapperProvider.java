package org.piotr.github.model.mapper;


import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.ext.ContextResolver;

public abstract class AbstractVCSObjectMapperProvider implements ContextResolver<ObjectMapper> {
    ObjectMapper defaultObjectMapper;

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return defaultObjectMapper;
    }
}

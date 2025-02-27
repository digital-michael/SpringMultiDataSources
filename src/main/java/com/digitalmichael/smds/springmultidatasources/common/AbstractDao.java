package com.digitalmichael.smds.springmultidatasources.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.Map;


/**
 * Provides a common base class for DAO, mostly requiring equals to be implemented
 * and providing a default toString() as json.
 *
 * STATUS: tested and appears fine
 */
public abstract class AbstractDao {

    public String toString() {
        return toJsonString();
    }

    abstract public boolean equals(Object o);

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String toJsonString() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // Enable pretty-print
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert to JSON", e);
        }
    }

    public Map<String, Object> toMap() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // Enable pretty-print
        return mapper.convertValue(this, Map.class);
    }
}

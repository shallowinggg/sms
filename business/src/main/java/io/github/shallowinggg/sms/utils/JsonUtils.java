package io.github.shallowinggg.sms.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;

/**
 * @author ding shimin
 */
public final class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();

        // configure
    }

    private JsonUtils() {
    }

    @Nullable
    public static <T> T readSilent(String json, Class<T> clazz) {
        if (json == null) {
            return null;
        }

        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            LOGGER.error("failed when deserialize json '{}', cause: {}", json, e.getMessage());
            return null;
        }
    }

    @Nullable
    public static <T> String writeSilent(T data) {
        if (data == null) {
            return null;
        }

        try {
            return mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            LOGGER.error("failed when serialize data '{}', cause: {}", data, e.getMessage());
            return null;
        }
    }
}

package com.xiaotu.jackson.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.xiaotu.jackson.pojo.User;

import java.io.IOException;

public class UserDeserializer extends JsonDeserializer<User> {

    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String userName = node.get("user-name").asText();
        User user = new User();
        user.setUserName(userName);
        return user;
    }
}

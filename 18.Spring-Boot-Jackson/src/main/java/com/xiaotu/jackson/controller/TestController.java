package com.xiaotu.jackson.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.xiaotu.jackson.pojo.User;

@Controller
public class TestController {
    @Autowired
    ObjectMapper mapper;

    @JsonView(User.AllUserFieldView.class)
    @RequestMapping("getuser")
    @ResponseBody
    public User getUser() {
        User user = new User();
        user.setUserName("xiaotu");
        user.setAge(18);
        user.setPassword("123456");
        user.setBirthday(new Date());
        return user;
    }

    @RequestMapping("serialization")
    @ResponseBody
    public String serialization() {
        try {
            User user = new User();
            user.setUserName("xiaotu");
            user.setBirthday(new Date());
            String str = mapper.writeValueAsString(user);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("readjsonstring")
    @ResponseBody
    public String readJsonString() {
        try {
            String json = "{\"name\":\"xiaotu\",\"age\":17,\"birthday\":\"2022-09-07 16:31:52\"}";
            JsonNode node = mapper.readTree(json);
            String name = node.get("name").asText();
            int age = node.get("age").asInt();
            String birthday = node.get("birthday").asText();
            return name + " " + age + " " + birthday;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("readjsonasobject")
    @ResponseBody
    public String readJsonAsObject() {
        try {
            String json = "{\"userName\":\"xiaotu\",\"age\":17,\"birthday\":\"2022-09-07 16:31:52\"}";
            User user = mapper.readValue(json, User.class);
            String name = user.getUserName();
            int age = user.getAge();
            Date birthday = user.getBirthday();
            return name + " " + age + " " + birthday.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("formatobjecttojsonstring")
    @ResponseBody
    public String formatObjectToJsonString() {
        try {
            User user = new User();
            user.setUserName("xiaotu");
            user.setAge(17);
            user.setPassword("123456");
            user.setBirthday(new Date());
            String jsonStr = mapper.writeValueAsString(user);
            return jsonStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("updateuser")
    @ResponseBody
    public int updateUser(@RequestBody List<User> list) {
        return list.size();
    }

    @RequestMapping("customize")
    @ResponseBody
    public String customize() throws JsonParseException, JsonMappingException, IOException {
        String jsonStr = "[{\"userName\":\"xiaotu\",\"age\":17},{\"userName\":\"theodore\",\"age\":18}]";
        JavaType type = mapper.getTypeFactory().constructParametricType(List.class, User.class);
        List<User> list = mapper.readValue(jsonStr, type);
        String msg = "";
        for (User user : list) {
            msg += user.getUserName();
        }
        return msg;
    }
}

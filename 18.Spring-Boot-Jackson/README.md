# 18.Spring-Boot-Jackson

Fastjson反序列化漏洞后，面对序列化的时候，更多考虑的用jsckson或gson。

## 自定义ObjectMapper

我们都知道，在Spring中使用`@ResponseBody`注解可以将方法返回的对象序列化成JSON，比如：

```java
@RequestMapping("getuser")
@ResponseBody
public User getUser() {
    User user = new User();
    user.setUserName("xiaotu");
    user.setBirthday(new Date());
    return user;
}
```



User类：

```java
public class User implements Serializable {
    private static final long serialVersionUID = 6222176558369919436L;
    
    private String userName;
    private int age;
    private String password;
    private Date birthday;
    ...
}
```



访问`getuser`页面输出：

```json
{"userName":"mrbird","age":0,"password":null,"birthday":1522634892365}
```



可看到时间默认以时间戳的形式输出，如果想要改变这个默认行为，我们可以自定义一个ObjectMapper来替代：

```json
package com.xiaotu.jackson.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper getObjectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return mapper;
    }
}
```



## 序列化

Jackson通过使用mapper的`writeValueAsString`方法将Java对象序列化为JSON格式字符串：

```java
@Autowired
ObjectMapper mapper;

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
```



## 反序列化

使用`@ResponseBody`注解可以使对象序列化为JSON格式字符串，除此之外，Jackson也提供了反序列化方法。

### 树遍历

当采用树遍历的方式时，JSON被读入到JsonNode对象中，可以像操作XML DOM那样读取JSON。比如：

```java
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
```

`readTree`方法可以接受一个字符串或者字节数组、文件、InputStream等， 返回JsonNode作为根节点，你可以像操作XML DOM那样操作遍历JsonNode以获取数据。

解析多级JSON例子：

```java
String json = "{\"name\":\"mrbird\",\"hobby\":{\"first\":\"sleep\",\"second\":\"eat\"}}";;
JsonNode node = mapper.readTree(json);
JsonNode hobby = node.get("hobby");
String first = hobby.get("first").asText();
```



### 绑定对象

我们也可以将Java对象和JSON数据进行绑定，如下所示：

```java
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
```



## Jackson注解

Jackson包含了一些实用的注解：

### @JsonProperty

`@JsonProperty`，作用在属性上，用来为JSON Key指定一个别名。

### @Jsonlgnore

`@Jsonlgnore`，作用在属性上，用来忽略此属性。

### @JsonIgnoreProperties

`@JsonIgnoreProperties`，忽略一组属性，作用于类上，比如`JsonIgnoreProperties({ "password", "age" })`。

### @JsonFormat

`@JsonFormat`，用于日期格式化

### @JsonNaming

`@JsonNaming`，用于指定一个命名策略，作用于类或者属性上。Jackson自带了多种命名策略，你可以实现自己的命名策略，比如输出的key 由Java命名方式转为下面线命名方法 —— userName转化为user-name。

### @JsonSerialize

`@JsonSerialize`，指定一个实现类来自定义序列化。类必须实现`JsonSerializer`接口，代码如下：

```java
@JsonSerialize(using = UserSerializer.class)
public class User implements Serializable {
    ...
}
```

```java
public class UserSerializer extends JsonSerializer<User> {

    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("user-name",user.getUserName());
        jsonGenerator.writeEndObject();
    }
}
```



### @JsonDeserialize

`@JsonDeserialize`，用户自定义反序列化，同`@JsonSerialize` ，类需要实现`JsonDeserializer`接口。

```java
@JsonDeserialize (using = UserDeserializer.class)
public class User implements Serializable {
    ...
}
```

```java
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
```

### @JsonView

`@JsonView`，作用在类或者属性上，用来定义一个序列化组。 比如对于User对象，某些情况下只返回userName属性就行，而某些情况下需要返回全部属性。 因此User对象可以定义成如下

```java
public class User implements Serializable {
    private static final long serialVersionUID = 6222176558369919436L;
    
    public interface UserNameView {};
    public interface AllUserFieldView extends UserNameView {};
    
    @JsonView(UserNameView.class)
    private String userName;
    
    @JsonView(AllUserFieldView.class)
    private int age;
    
    @JsonView(AllUserFieldView.class)
    private String password;
    
    @JsonView(AllUserFieldView.class)
    private Date birthday;
    ...	
}
```

User定义了两个接口类，一个为`userNameView`，另外一个为`AllUserFieldView`继承了`userNameView`接口。这两个接口代表了两个序列化组的名称。属性userName使用了`@JsonView(UserNameView.class)`，而剩下属性使用了`@JsonView(AllUserFieldView.class)`。

Spring中Controller方法允许使用`@JsonView`指定一个组名，被序列化的对象只有在这个组的属性才会被序列化，代码如下：

```java
@JsonView(User.UserNameView.class)
@RequestMapping("getuser")
@ResponseBody
public User getUser() {
    User user = new User();
    user.setUserName("xiaotu");
    user.setAge(17);
    user.setPassword("123456");
    user.setBirthday(new Date());
    return user;
}
```





## 集合的反序列化

在Controller方法中，可以使用`＠RequestBody`将提交的JSON自动映射到方法参数上，比如

```java
@RequestMapping("updateuser")
@ResponseBody
public int updateUser(@RequestBody List<User> list){
    return list.size();
}
```



某些情况下，不能使用直接List<User> 直接接受mapper.readValue(jsonStr,List.class)

这是因为在运行时刻，泛型己经被擦除了（不同于方法参数定义的泛型，不会被擦除）。为了提供泛型信息，Jackson提供了JavaType ，用来指明集合类型，将上述方法改为：

```java
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
```


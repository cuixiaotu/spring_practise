# Spring Boot中编写单元测试

编写单元测试可以帮助开发人员编写高质量的代码，提升代码质量，减少Bug，便于重构。Spring Boot提供了一些实用程序和注解，用来帮助我们测试应用程序，在Spring Boot中开启单元测试只需引入spring-boot-starter-test即可，其包含了一些主流的测试库。本文主要介绍基于 Service和Controller的单元测试。

`mvnw dependency:tree`

```
[INFO] --- maven-dependency-plugin:3.3.0:tree (default-cli) @ test ---
[INFO] com.xiaotu:test:jar:0.0.1-SNAPSHOT
[INFO] +- org.springframework.boot:spring-boot-starter:jar:2.7.3:compile
[INFO] |  +- org.springframework.boot:spring-boot:jar:2.7.3:compile
[INFO] |  |  \- org.springframework:spring-context:jar:5.3.22:compile
[INFO] |  |     +- org.springframework:spring-aop:jar:5.3.22:compile
[INFO] |  |     +- org.springframework:spring-beans:jar:5.3.22:compile
[INFO] |  |     \- org.springframework:spring-expression:jar:5.3.22:compile
[INFO] |  +- org.springframework.boot:spring-boot-autoconfigure:jar:2.7.3:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-logging:jar:2.7.3:compile
[INFO] |  |  +- ch.qos.logback:logback-classic:jar:1.2.11:compile
[INFO] |  |  |  \- ch.qos.logback:logback-core:jar:1.2.11:compile
[INFO] |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.17.2:compile
[INFO] |  |  |  \- org.apache.logging.log4j:log4j-api:jar:2.17.2:compile
[INFO] |  |  \- org.slf4j:jul-to-slf4j:jar:1.7.36:compile
[INFO] |  +- jakarta.annotation:jakarta.annotation-api:jar:1.3.5:compile
[INFO] |  +- org.springframework:spring-core:jar:5.3.22:compile
[INFO] |  |  \- org.springframework:spring-jcl:jar:5.3.22:compile
[INFO] |  \- org.yaml:snakeyaml:jar:1.30:compile
[INFO] \- org.springframework.boot:spring-boot-starter-test:jar:2.7.3:test
[INFO]    +- org.springframework.boot:spring-boot-test:jar:2.7.3:test
[INFO]    +- org.springframework.boot:spring-boot-test-autoconfigure:jar:2.7.3:test
[INFO]    +- com.jayway.jsonpath:json-path:jar:2.7.0:test
[INFO]    |  +- net.minidev:json-smart:jar:2.4.8:test
[INFO]    |  |  \- net.minidev:accessors-smart:jar:2.4.8:test
[INFO]    |  |     \- org.ow2.asm:asm:jar:9.1:test
[INFO]    |  \- org.slf4j:slf4j-api:jar:1.7.36:compile
[INFO]    +- jakarta.xml.bind:jakarta.xml.bind-api:jar:2.3.3:test
[INFO]    |  \- jakarta.activation:jakarta.activation-api:jar:1.2.2:test
[INFO]    +- org.assertj:assertj-core:jar:3.22.0:test
[INFO]    +- org.hamcrest:hamcrest:jar:2.2:test
[INFO]    +- org.junit.jupiter:junit-jupiter:jar:5.8.2:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-api:jar:5.8.2:test
[INFO]    |  |  +- org.opentest4j:opentest4j:jar:1.2.0:test
[INFO]    |  |  +- org.junit.platform:junit-platform-commons:jar:1.8.2:test
[INFO]    |  |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-params:jar:5.8.2:test
[INFO]    |  \- org.junit.jupiter:junit-jupiter-engine:jar:5.8.2:test
[INFO]    |     \- org.junit.platform:junit-platform-engine:jar:1.8.2:test
[INFO]    +- org.mockito:mockito-core:jar:4.5.1:test
[INFO]    |  +- net.bytebuddy:byte-buddy:jar:1.12.13:test
[INFO]    |  +- net.bytebuddy:byte-buddy-agent:jar:1.12.13:test
[INFO]    |  \- org.objenesis:objenesis:jar:3.2:test
[INFO]    +- org.mockito:mockito-junit-jupiter:jar:4.5.1:test
[INFO]    +- org.skyscreamer:jsonassert:jar:1.5.1:test
[INFO]    |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO]    +- org.springframework:spring-test:jar:5.3.22:test
[INFO]    \- org.xmlunit:xmlunit-core:jar:2.9.0:test
```

- JUnit，标准的单元测试Java应用程序；
- Spring Test & Spring Boot Test，对Spring Boot应用程序的单元测试提供支持；
- Mockito, Java mocking框架，用于模拟任何Spring管理的Bean，比如在单元测试中模拟一个第三方系统Service接口返回的数据，而不会去真正调用第三方系统；
- AssertJ，一个流畅的assertion库，同时也提供了更多的期望值与测试返回值的比较方式；
- Hamcrest，库的匹配对象（也称为约束或谓词）；
- JsonPath，提供类似XPath那样的符号来获取JSON数据片段；
- JSONassert，对JSON对象或者JSON字符串断言的库。



标准的Spring Boot测试单元应有如下的代码结构：

```java
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class TestApplicationTests {

}
```

## 知识准备

### JUnit4注解

JUnit4中包含了几个比较重要的注解：`@BeforeClass`、`@AfterClass`、`@Before`、`@After`和`@Test`。其中， `@BeforeClass`和`@AfterClass`在每个类加载的开始和结束时运行，必须为静态方法；而`@Before`和`@After`则在每个测试方法开始之前和结束之后运行。见如下例

```java
@RunWith(SpringRunner.class)
@SpringBootTest
class TestApplicationTests {

	@BeforeClass
	public static void beforeClassTest() {
		System.out.println("before class test");
	}

	@Before
	public void beforeTest() {
		System.out.println("before test");
	}

	@Test
	public void Test1() {
		System.out.println("test 1+1=2");
		Assert.assertEquals(2, 1 + 1);
	}

	@Test
	public void Test2() {
		System.out.println("test 2+2=4");
		Assert.assertEquals(4, 2 + 2);
	}

	@After
	public void afterTest() {
		System.out.println("after test");
	}

	@AfterClass
	public static void afterClassTest() {
		System.out.println("after class test");
	}

}
```

输出如下

```
...
before class test
before test
test 1+1=2
after test
before test
test 2+2=4
after test
after class test
...
```



### Assert

上面代码中，我们使用了Assert类提供的assert口方法，下面列出了一些常用的assert方法：

- `assertEquals("message",A,B)`，判断A对象和B对象是否相等，这个判断在比较两个对象时调用了`equals()`方法。
- `assertSame("message",A,B)`，判断A对象与B对象是否相同，使用的是`==`操作符。
- `assertTrue("message",A)`，判断A条件是否为真。
- `assertFalse("message",A)`，判断A条件是否不为真。
- `assertNotNull("message",A)`，判断A对象是否不为`null`。
- `assertArrayEquals("message",A,B)`，判断A数组与B数组是否相等。



### MockMvc

下文中，对Controller的测试需要用到MockMvc技术。MockMvc，从字面上来看指的是模拟的MVC，即其可以模拟一个MVC环境，向Controller发送请求然后得到响应。

在单元测试中，使用MockMvc前需要进行初始化，如下所示

```java
private MockMvc mockMvc;

@Autowired
private WebApplicationContext wac;

@Before
public void setupMockMvc(){
    mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
}
```


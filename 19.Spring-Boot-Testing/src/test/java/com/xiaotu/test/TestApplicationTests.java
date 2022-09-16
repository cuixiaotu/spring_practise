package com.xiaotu.test;

import org.junit.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class TestApplicationTests {

	@BeforeAll
	public static void beforeClassTest() {
		System.out.println("before class test");
	}

	@BeforeEach
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

	@AfterEach
	public void afterTest() {
		System.out.println("after test");
	}

	@AfterAll
	public static void afterClassTest() {
		System.out.println("after class test");
	}

}

package com.xiaotu.ehcache;

import com.xiaotu.ehcache.bean.Student;
import com.xiaotu.ehcache.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EhcacheApplicationTests {

	@Autowired
	private StudentService studentService;

	@Test
	public void test01() throws Exception {
		Student student1 = this.studentService.queryStudentBySno("001");
		System.out.println("学号" + student1.getSno() + "的学生姓名为：" + student1.getName());

		Student student2 = this.studentService.queryStudentBySno("001");
		System.out.println("学号" + student2.getSno() + "的学生姓名为：" + student2.getName());
	}

	@Test
	void contextLoads() {
	}

}

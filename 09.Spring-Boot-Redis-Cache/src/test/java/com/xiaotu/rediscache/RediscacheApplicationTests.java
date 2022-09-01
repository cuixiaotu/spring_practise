package com.xiaotu.rediscache;

import com.xiaotu.rediscache.bean.Student;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.xiaotu.rediscache.service.StudentService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class RediscacheApplicationTests {

	@Autowired
	private StudentService studentService;

	@Test
	public void test1() throws Exception {
		Student student1 = studentService.queryStudentBySno("001");
		System.out.println("学号" + student1.getSno() + "的学生姓名为：" + student1.getName());

		Student student2 = studentService.queryStudentBySno("001");
		System.out.println("学号" + student2.getSno() + "的学生姓名为：" + student2.getName());
	}

	@Test
	public void test2() throws Exception {
		Student student1 = this.studentService.queryStudentBySno("001");
		System.out.println("学号" + student1.getSno() + "的学生姓名为：" + student1.getName());

		student1.setName("康康");
		studentService.update(student1);

		Student student2 = studentService.queryStudentBySno("001");
		System.out.println("学号" + student2.getSno() + "的学生姓名为：" + student2.getName());
	}

	@Test
	public void test3() {

		System.out.println("学号" + "的学生姓名为：" );
	}

}


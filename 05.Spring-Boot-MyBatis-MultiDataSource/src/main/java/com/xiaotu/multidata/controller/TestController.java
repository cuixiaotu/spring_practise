package com.xiaotu.multidata.controller;

import com.xiaotu.multidata.bean.Student;
import com.xiaotu.multidata.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TestController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String index(){
        return "hello";
    }

    @GetMapping("/getallstudent")
    public List<Map<String, Object>> index1(){
        return studentService.getAllStudents();
    }

    @GetMapping("/getallstudentmaster")
    public List<Map<String, Object>> index3(){
        return studentService.getAllStudentsFromMaster();
    }
}

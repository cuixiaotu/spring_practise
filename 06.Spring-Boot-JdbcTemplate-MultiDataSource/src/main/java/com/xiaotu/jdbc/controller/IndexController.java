package com.xiaotu.jdbc.controller;

import com.xiaotu.jdbc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class IndexController {

    @Autowired
    private StudentService studentService;

    @GetMapping("getallstudentsfrommaster")
    public List<Map<String, Object>> index1(){
        return studentService.getAllStudentsFromMaster();
    }

    @GetMapping("getallstudentsfromslave")
    public List<Map<String, Object>> index2(){
        return studentService.getAllStudentsFromSlave();
    }
}

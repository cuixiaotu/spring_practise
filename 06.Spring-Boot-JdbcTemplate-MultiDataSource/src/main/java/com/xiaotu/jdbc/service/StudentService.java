package com.xiaotu.jdbc.service;

import java.util.List;
import java.util.Map;

public interface StudentService {
    //写mysql1
    List<Map<String, Object>> getAllStudentsFromMaster();
    List<Map<String, Object>> getAllStudentsFromSlave();
}

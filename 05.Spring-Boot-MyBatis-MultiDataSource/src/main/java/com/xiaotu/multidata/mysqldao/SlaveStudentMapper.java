package com.xiaotu.multidata.mysqldao;

import com.xiaotu.mybatis.bean.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

//@Mapper
//public interface SlaveStudentMapper {
//
//    @Insert("insert into student(sno,sname,ssex) values(#{sno},#{name},#{sex})")
//    int add(Student student);
//
//    @Update("update student set sname=#{name},ssex=#{sex} where sno=#{sno}")
//    int update(Student student);
//
//    @Delete("delete from student where sno=#{sno}")
//    int deleteBySno(String sno);
//
//    @Select("select * from student where sno=#{sno}")
//    @Results(id = "student",value= {
//            @Result(property = "sno", column = "sno", javaType = String.class),
//            @Result(property = "name", column = "sname", javaType = String.class),
//            @Result(property = "sex", column = "ssex", javaType = String.class)
//    })
//    Student queryStudentBySno(String sno);
//
//    List<Map<String, Object>> getAllStudents();
//}

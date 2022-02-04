package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.ExamService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("stu")
public class Studentcontroller {

        @Autowired
        StudentService studentService;
        @Autowired
        ExamService examService;


        // 对所有学生信息的查询
        // Search the information of all the students
        @GetMapping
        public List<Student> search() {
            List<Student> studentList = studentService.search();
            return studentList;
        }

        // 以查询学号的方式查询具体学生
        // Search student infos by id
        @GetMapping("/studentsingle/{id}")
        public Student searchById(@PathVariable Integer id) {
            Student stu = studentService.searchById(id);
            return stu;
        }

        // 以查询用户名的方式查询具体学生
        // Search student infos by Username
        @GetMapping("/student/{username}")
        public Student searchByUsername(@PathVariable String username) {
        Student stu = studentService.searchByUsername(username);
        System.out.println(stu);
        return stu;
    }

        // 增加新生信息
        // Add new student User info
        @PostMapping
        public String add(@RequestBody Student student) {
            boolean flag = studentService.add(student);
            if (flag) {
                return "redirect:search";
            }
            return "";
        }

        // 修改新生信息
        // Update student infos
        @PutMapping
        public String update(@RequestBody Student stu) {
            if (studentService.update(stu)) {
                return "redirect:search";
            } else {
                return "update";
            }
        }

        // 删除某个新生信息
        // Delete certain student's info
        @DeleteMapping("{id}")
        public String delete(@PathVariable Integer id) {
            if (studentService.delete(id)) {
                return "redirect:search";
            } else {
                return "";
            }
        }

    }


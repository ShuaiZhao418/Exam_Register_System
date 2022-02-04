package com.example.demo.controller;

import com.example.demo.entity.Student_exam;
import com.example.demo.service.Student_examService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student_exam")
public class Student_examcontroller {

        @Autowired
        Student_examService student_examService;

        //对所有学生信息的查询
        // Search all the student who has attended exams
        @GetMapping
        public List<Student_exam> search() {
            List<Student_exam> studentexamList = student_examService.search();
            return studentexamList;
        }

        //以查询学号的方式查询具体学生
        // Search students by id who has attended exams
        @GetMapping("{id}")
        public Student_exam searchById(@PathVariable Integer id) {
            Student_exam studentexam = student_examService.searchById(id);
            return studentexam;
        }

        //以查询学号和考试号的方式查询具体学生
        // Search student info who has attended certain exams
        @GetMapping("/single/{studentnum}/{examnum}")
        public Student_exam searchByStudentnumAndExamnum(@PathVariable Integer studentnum, @PathVariable Integer examnum) {
            Student_exam studentexam = student_examService.searchByStudentnumAndExamnum(studentnum, examnum);
            return studentexam;
        }


        //增加新生信息
        // Add new info that a student has registered an exam
        @PostMapping
        public String add(@RequestBody Student_exam se) {
            boolean flag = student_examService.add(se);
            if (flag) {
                return "redirect:search";
            }
            return "";
        }

        //修改新生信息
        // Update the corresponding info between student and exam
        @PutMapping
        public String update(@RequestBody Student_exam se) {
            if (student_examService.update(se)) {
                return "redirect:search";
            } else {
                return "update";
            }
        }

        //以查询学生id的方式查询具体学生
        @GetMapping("/studentexam/{studentnum}")
        public List<Student_exam> searchByStudentnum(@PathVariable Integer studentnum) {
            List<Student_exam> studentexamList = student_examService.searchByStudentnum(studentnum);
            return studentexamList;
        }

        //删除某个新生信息
        @DeleteMapping("{id}")
        public String delete(@PathVariable Integer id) {
            if (student_examService.delete(id)) {
                return "redirect:search";
            } else {
                return "";
            }
        }

        //删除某个新生信息
        @DeleteMapping("/deleteexam/{studentnum}/{examnum}")
        public String deleteByStudentnumAndExamnum(@PathVariable Integer studentnum, @PathVariable Integer examnum) {
            student_examService.deleteByStudentnumAndExamnum(studentnum, examnum);
            return "";
        }
    }


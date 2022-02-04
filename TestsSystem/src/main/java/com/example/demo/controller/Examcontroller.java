package com.example.demo.controller;

import com.example.demo.entity.Exam;
import com.example.demo.entity.Student;
import com.example.demo.service.ExamService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping("exam")
public class Examcontroller {

    @Autowired
    StudentService studentService;
    @Autowired
    ExamService examService;

    // Search all the exam informations
    @GetMapping
    public List<Exam> search(){
        List<Exam> examList=examService.search();

        return examList;
    }

    // Search exams in certain date and city
    @GetMapping("/allexam/{examcity}/{examtime}")
    public List<Exam> searchByExamcityAndExamtime(@PathVariable String examcity, @PathVariable String examtime) throws ParseException, UnsupportedEncodingException {
        List<Exam> examlist=examService.searchByExamcityAndExamtime(examcity,examtime);
        return examlist;
    }

    // Search exams in certain date and city after current date
    @GetMapping("/allexamafter/{examcity}/{examtime}")
    public List<Exam> searchByExamcityAndExamtimeAfter(@PathVariable String examcity, @PathVariable String examtime) throws ParseException, UnsupportedEncodingException {
        List<Exam> examlist=examService.searchByExamcityAndExamtimeafter(examcity,examtime);
        return examlist;
    }


    // Search exams after current date
    @GetMapping("/after/{id}")
    public Exam searchByIdAfter(@PathVariable Integer id) {
        Exam ex = examService.searchByIdafter(id);
        return ex;
    }

    // Search exams before current date
    @GetMapping("/before/{id}")
    public Exam searchByIdBefore(@PathVariable Integer id) {
        Exam ex = examService.searchByIdbefore(id);
        return ex;
    }

    // Search exams by id
    @GetMapping("{id}")
    public Exam searchById(@PathVariable Integer id) {
        Exam ex = examService.searchById(id);
        return ex;
    }

    // Add new Exams
    @PostMapping
    public String add(@RequestBody Exam ex){
        boolean flag = examService.add(ex);
        if(flag){
            return "redirect:search";
        }
        return "";
    }

    // Update exam informations
    @PutMapping
    public String update(@RequestBody Exam ex){
        if(examService.update(ex)){
            return "redirect:search";
        }else{
            return "";
        }
    }

    // Delete certain exam info
    @DeleteMapping("{id}")
    public String delete(@PathVariable Integer id) {
        if (examService.delete(id)) {
            return "redirect:search";
        } else {
            return "";
        }
    }

}


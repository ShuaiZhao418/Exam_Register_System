package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.entity.Student_exam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Student_examService {
    List<Student_exam> search();
    boolean add(Student_exam se);
    // Search all the students in a certain exam
    Student_exam searchById(Integer id);
    boolean update(Student_exam se);
    boolean delete(Integer id);
    // Search if a student attend a exam
    List<Student_exam>  searchByStudentnum(Integer Id);
    // Search all the exams a student attend
    Student_exam searchByStudentnumAndExamnum(Integer studentnum, Integer examnum);
    // A student delete certain exam
    Integer deleteByStudentnumAndExamnum(Integer studentunm, Integer examnum);
}

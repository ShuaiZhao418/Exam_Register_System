package com.example.demo.service;

import com.example.demo.entity.Exam;
import com.example.demo.entity.Student;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface ExamService {
    // Search for all the exams
    List<Exam> search();
    // Add one exam
    boolean add(Exam student);
    // Search all the exams
    Exam searchById(Integer id);
    // Update exam informations
    boolean update(Exam student);
    // Delete certain exam by ID
    boolean delete(Integer id);
    // Search for the exams by certain city and time
    List<Exam> searchByExamcityAndExamtime(String examcity, String examtime);
    // Search for the exams by certain city and time after current date
    List<Exam>  searchByExamcityAndExamtimeafter(String examcity, String examtime);
    // Search for all the exams before current date
    Exam searchByIdbefore(Integer id);
    // Search for all the exams after current date
    Exam searchByIdafter(Integer id);

}

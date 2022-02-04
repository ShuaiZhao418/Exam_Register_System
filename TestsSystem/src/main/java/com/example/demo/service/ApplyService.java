package com.example.demo.service;

import com.example.demo.entity.Apply;
import com.example.demo.entity.Exam;
import com.example.demo.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApplyService {
    // Search for all the application information
    List<Apply> search();
    // Add one application information
    boolean add(Apply apply);
    // Update the information of application
    boolean update(Apply apply);
    // Delete the information of application
    boolean delete(Integer id);
    // search for the application by ID
    Apply searchById(Integer id);
    // search for the application by attribute isRead or not
    List<Apply> searchByMessageread(Integer judge);
}

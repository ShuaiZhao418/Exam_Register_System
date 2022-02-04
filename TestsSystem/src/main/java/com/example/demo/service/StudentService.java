package com.example.demo.service;

import com.example.demo.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    List<Student> search();
    boolean add(Student student);
    Student searchById(Integer id);
    boolean update(Student student);
    boolean delete(Integer id);
    Student searchByUsername(String username);
}

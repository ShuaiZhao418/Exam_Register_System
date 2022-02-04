package com.example.demo.dao;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao extends JpaRepository<Student, Integer> {

    // Find students by student's username
    Student findByUsername(String username);
}

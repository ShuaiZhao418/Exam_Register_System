package com.example.demo.dao;

import com.example.demo.entity.Apply;
import com.example.demo.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplyDao extends JpaRepository<Apply, Integer> {

    // Find the application information from students
    List<Apply> findByMessageread(Integer judge);
}

package com.example.demo.service.ServiceImpl;

import com.example.demo.dao.StudentDao;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDao stuDao;

    //Search all the students information
    @Override
    public List<Student> search() {
        return stuDao.findAll();
    }

    // Add a student / register a new user
    @Override
    public boolean add(Student student) {
        Student newStu=stuDao.save(student);
        return newStu!=null;
    }

    // Search a student info by ID
    @Override
    public Student searchById(Integer id) {
        return stuDao.findById(id).get();
    }

    // Search a student info by Username
    @Override
    public Student searchByUsername(String username) {
        return stuDao.findByUsername(username);
    }

    // Student updates the info
    @Override
    public boolean update(Student student) {
        Student newStu=stuDao.save(student);
        return newStu!=null;
    }

    // Delete certain User info
    @Override
    public boolean delete(Integer id) {
        stuDao.deleteById(id);
        return true;
    }
}

package com.example.demo.service.ServiceImpl;

import com.example.demo.dao.ExamDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.entity.Exam;
import com.example.demo.entity.Student;
import com.example.demo.service.ExamService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    ExamDao examDao;

    @Autowired
    StudentDao stuDao;

    // Search for all the exams
    @Override
    public List<Exam> search() {
        return examDao.findAll();
    }

    // Search for the exams by certain city and time after current date
    @Override
    public List<Exam>  searchByExamcityAndExamtimeafter(String examcity, String examtime){
        return examDao.findByExamcityAndExamtimeafter(examcity, examtime);
    }

    // Search for all the exams before current date
    @Override
    public Exam searchByIdbefore(Integer id){
        return examDao.findByIdbefore(id);
    }

    // Search for all the exams after current date
    @Override
    public Exam  searchByIdafter(Integer id){
        return examDao.findByIdafter(id);
    }

    // Add one exam
    @Override
    public boolean add(Exam ex) {
        Exam newExam =examDao.save(ex);
        return newExam!=null;
    }


    // Search all the exams
    @Override
    public Exam searchById(Integer id) {
        return examDao.findById(id).get();
    }

    // Update exam informations
    @Override
    public boolean update(Exam bj) {
        Exam exam =examDao.save(bj);
        return exam!=null;
    }

    // Delete certain exam by ID
    @Override
    public boolean delete(Integer id) {
        examDao.deleteById(id);
        return true;
    }

    // Search for the exams by certain city and time
    @Override
    public List<Exam> searchByExamcityAndExamtime(String examcity, String examtime) {
        List<Exam> examlist=examDao.findByExamcityAndExamtime(examcity,examtime);
        return examlist;
    }
}

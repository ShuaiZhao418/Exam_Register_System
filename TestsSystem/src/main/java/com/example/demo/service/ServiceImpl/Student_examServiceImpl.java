package com.example.demo.service.ServiceImpl;

import com.example.demo.dao.StudentDao;
import com.example.demo.dao.Student_examDao;
import com.example.demo.entity.Student;
import com.example.demo.entity.Student_exam;
import com.example.demo.service.StudentService;
import com.example.demo.service.Student_examService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Student_examServiceImpl implements Student_examService {

    @Autowired
    Student_examDao student_examDao;

    @Override
    public List<Student_exam> search() {
        return student_examDao.findAll();
    }

    @Override
    public boolean add(Student_exam se) {
        Student_exam studentexam=student_examDao.save(se);
        return studentexam!=null;
    }


    @Override
    public boolean update(Student_exam se) {
        Student_exam studentexam=student_examDao.save(se);
        return studentexam!=null;
    }

    @Override
    public boolean delete(Integer id) {
        student_examDao.deleteById(id);
        return true;
    }

    // A student delete certain exam
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteByStudentnumAndExamnum(Integer studentnum, Integer examnum) {
        student_examDao.deleteByStudentnumAndExamnum(studentnum, examnum);
        return 1;
    }

    // Search all the exams a student attend
    @Override
    public List<Student_exam> searchByStudentnum(Integer studentId){
        return student_examDao.findByStudentnum(studentId);
    }


    // Search all the students in a certain exam
    @Override
    public Student_exam searchById(Integer id) {
        return student_examDao.findById(id).get();
    }


    // Search if a student attend a exam
    @Override
    public Student_exam searchByStudentnumAndExamnum(Integer studentnum, Integer examnum) {
        return student_examDao.findByStudentnumAndExamnum(studentnum, examnum);
    }

}

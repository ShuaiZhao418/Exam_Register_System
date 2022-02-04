package com.example.demo.dao;

import com.example.demo.entity.Student;
import com.example.demo.entity.Student_exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface Student_examDao extends JpaRepository<Student_exam, Integer> {
    // Search students by id
    List<Student_exam> findByStudentnum(Integer studentId);
    // Search students who join in certain exam
    Student_exam findByStudentnumAndExamnum(Integer studentnum, Integer examnum);
    // Delete students who join in certain exam
    @Modifying
    @Transactional
    @Query(value = "delete from student_exam where student_exam.studentnum=?1 and student_exam.examnum=?2", nativeQuery = true)
    Integer deleteByStudentnumAndExamnum(Integer studentnum, Integer examnum);
}

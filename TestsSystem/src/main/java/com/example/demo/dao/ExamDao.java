package com.example.demo.dao;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface ExamDao extends JpaRepository<Exam, Integer> {
    List<Exam> findByExamcityAndExamtime(String examcity, String examtime);


    // Set the sql to search exams by time and city which after current time by comparing exam.examtime and current_date()
    @Query(value = "select * from exam where exam.examtime>current_date() and exam.examcap>0 and exam.examcity =?1 and exam.examtime=?2", nativeQuery = true)
    List<Exam>  findByExamcityAndExamtimeafter(String examcity, String examtime);

    // search the exams which are before the current date by comparing exam.examtime and current_date()
    @Query(value = "select * from exam where exam.examtime<=current_date() and exam.id =?1", nativeQuery = true)
    Exam findByIdbefore(Integer id);

    // search the exams which are after the current date by comparing exam.examtime and current_date()
    @Query(value = "select * from exam where exam.examtime>current_date() and exam.id =?1", nativeQuery = true)
    Exam  findByIdafter(Integer id);
}

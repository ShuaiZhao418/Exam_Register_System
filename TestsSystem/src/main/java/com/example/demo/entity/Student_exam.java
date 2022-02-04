package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table
public class Student_exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Integer studentnum;

    @Column
    private Integer examnum;

    @Column
    private Integer grade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }



    public Integer getExamnum() {
        return examnum;
    }

    public void setExamnum(Integer examnum) {
        this.examnum = examnum;
    }

    public Integer getStudentnum() {
        return studentnum;
    }

    public void setStudentnum(Integer studentnum) {
        this.studentnum = studentnum;
    }
}

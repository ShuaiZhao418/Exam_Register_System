package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Exam {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private int id;
    @Column
    private String examtime;
    @Column
    private String examplace;
    @Column
    private String examcity;
    @Column
    private int examcap;

    public String getExamtime() {
        return examtime;
    }

    public void setExamtime(String examtime) {
        this.examtime = examtime;
    }

    public String getExamcity() {
        return examcity;
    }

    public void setExamcity(String examcity) {
        this.examcity = examcity;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", examtime='" + examtime + '\'' +
                ", examplace='" + examplace + '\'' +
                ", examcap=" + examcap +
                '}';
    }
    public int getId() {
        return id;
    }

    public String getExamplace() {
        return examplace;
    }

    public int getExamcap() {
        return examcap;
    }

    public void setId(int id) {
        this.id = id;
    }



    public void setExamplace(String examplace) {
        this.examplace = examplace;
    }

    public void setExamcap(int examcap) {
        this.examcap = examcap;
    }

}

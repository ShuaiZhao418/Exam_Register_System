package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table
public class Apply {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column
    private int studentid;
    @Column
    private String username;
    @Column
    private String memorandum;
    @Column
    private int messageread;
    @Column
    private Double refund;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMemorandum() {
        return memorandum;
    }

    public void setMemorandum(String memorandum) {
        this.memorandum = memorandum;
    }

    public int getMessageread() {
        return messageread;
    }

    public void setMessageread(int messageread) {
        this.messageread = messageread;
    }

    public Double getRefund() {
        return refund;
    }

    public void setRefund(Double refund) {
        this.refund = refund;
    }
}

package com.example.demo.service;

import com.example.demo.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    // update the password of current administrator
    boolean update(Admin adm);
    // Search the information of all administrators
    List<Admin> search();
}

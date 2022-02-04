package com.example.demo.service.ServiceImpl;

import com.example.demo.dao.AdminDao;
import com.example.demo.entity.Admin;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminDao adminDao;
    // update the password of current administrator
    @Override
    public boolean update(Admin adm) {
        Admin admin = adminDao.save(adm);
        return admin!=null;
    }
    // Search the information of all administrators
    @Override
    public List<Admin> search() {
        return adminDao.findAll();
    }
}

package com.example.demo.controller;

import com.example.demo.entity.Admin;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class Admincontroller {
    @Autowired
    AdminService adminService;

    // 修改管理员账号的密码
    // Update the administrator's username
    @PutMapping
    public String update(@RequestBody Admin admin){
        if(adminService.update(admin)){
            return "redirect:search";
        }else{
            return "update";
        }
    }
    // 对所有管理员信息的查询
    // Search info of all the administrators
    @GetMapping
    public List<Admin> search(){
        List<Admin> adminList=adminService.search();
        return adminList;
    }

}


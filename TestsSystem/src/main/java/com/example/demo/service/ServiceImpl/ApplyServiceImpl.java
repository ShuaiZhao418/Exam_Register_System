package com.example.demo.service.ServiceImpl;

import com.example.demo.dao.ApplyDao;
import com.example.demo.entity.Apply;
import com.example.demo.entity.Exam;
import com.example.demo.entity.Student;
import com.example.demo.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    ApplyDao applyDao;

    // Search for all the application information
    @Override
    public List<Apply> search() {
        return applyDao.findAll();
    }

    // add one application information
    @Override
    public boolean add(Apply apply) {
        Apply app=applyDao.save(apply);
        return app!=null;
    }

    // Update the information of application
    @Override
    public boolean update(Apply apply) {
        Apply app=applyDao.save(apply);
        return app!=null;
    }

    // Delete the information of application
    @Override
    public boolean delete(Integer id) {
        applyDao.deleteById(id);
        return true;
    }

    // search for the application by ID
    @Override
    public Apply searchById(Integer id) {
        return applyDao.findById(id).get();
    }

    // search for the application by attribute isRead or not
    @Override
    public List<Apply> searchByMessageread(Integer judge){
        return applyDao.findByMessageread(judge);
    }

}

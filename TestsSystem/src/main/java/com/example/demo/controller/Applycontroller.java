package com.example.demo.controller;

import com.example.demo.entity.Apply;
import com.example.demo.entity.Student;
import com.example.demo.service.ApplyService;
import com.example.demo.service.ExamService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("app")
public class Applycontroller {


        @Autowired
        ApplyService applyService;


        // Search all the application infos
        @GetMapping
        public List<Apply> search() {

            List<Apply> applyList = applyService.search();

            return applyList;
        }


        // Search if the application have existed
        @GetMapping("/singleapply/{id}")
        public Apply searchById(@PathVariable Integer id) {
            Apply app = applyService.searchById(id);
            return app;
        }


        // Send a new apply
        @PostMapping
        public String add(@RequestBody Apply apply) {
            boolean flag = applyService.add(apply);
            if (flag) {
                return "redirect:search";
            }
            return "";
        }

        // Update the application info
        @PutMapping
        public String update(@RequestBody Apply app){
            if(applyService.update(app)){
                return "redirect:search";
            }else{
                return "";
            }
        }

        // Delete certain application info when this application finished
        @DeleteMapping("{id}")
        public String delete(@PathVariable Integer id) {
            if (applyService.delete(id)) {
                return "redirect:search";
            } else {
                return "";
            }
        }

        // Search application by is readed or not
        @GetMapping("/applymessage/{messageread}")
        public List<Apply> searchByMessageread(@PathVariable Integer messageread) {
            List<Apply> applyList = applyService.searchByMessageread(messageread);
            return applyList;
        }

    }


package com.example.resttemplateexampleRestClient.controller;

import com.example.resttemplateexampleRestClient.model.Student;
import com.example.resttemplateexampleRestClient.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StudentRestClientController {

    @Autowired
    StudentService service;

    @PostMapping("/save")
    public String save(@RequestBody Student student) {
        service.save(student);
        return "Data saved";
    }

    @GetMapping("/get")
    public List<Student> get(){
        return service.get();
    }

    @GetMapping("/getById")
    public Student getById(@RequestParam("stuId") Integer stuId){
        return service.getById(stuId);
    }

    @PostMapping ("/update")
    public Student update(@RequestBody Student student){
        return service.update(student);
    }

}

package com.crud.springcrudoperations.controller;

import com.crud.springcrudoperations.entity.Student;
import com.crud.springcrudoperations.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    StudentService service;

    @PostMapping("/save")
    public String save(@RequestBody Student student){
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

    @PostMapping("/update")
    public Student update(@RequestBody Student student){
        return service.update(student);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam("stuId") Integer stuId){
        service.delete(stuId);
    }

}

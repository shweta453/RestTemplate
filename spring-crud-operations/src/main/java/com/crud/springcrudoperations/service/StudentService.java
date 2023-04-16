package com.crud.springcrudoperations.service;

import com.crud.springcrudoperations.entity.Student;
import com.crud.springcrudoperations.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public String save(Student student){
        studentRepository.save(student);
        return "Data saved";
    }

    public List<Student> get(){
        return studentRepository.findAll();
    }

    public Student getById(Integer stuId){
        Student student=studentRepository.findByStuId(stuId);
        return student;
    }

    public Student update(Student student){
        Student existingstudent=studentRepository.findByStuId(student.getStuId());
        if(existingstudent!=null){
            student=studentRepository.save(student);
        }
        else {
            System.out.println("please enter valid stuId");
        }
        return student;
    }

    public void delete(Integer stuId){
         studentRepository.deleteById(stuId);
    }
}

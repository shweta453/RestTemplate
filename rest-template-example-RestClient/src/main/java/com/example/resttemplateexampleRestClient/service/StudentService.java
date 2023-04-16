package com.example.resttemplateexampleRestClient.service;

import com.example.resttemplateexampleRestClient.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.example.resttemplateexampleRestClient.constants.ApplicationConstants.*;

@Service
public class StudentService {

    @Autowired
    private RestTemplate restTemplate;


    @Value("${rest.base.url}")
    private String restBaseUrl;


    public String save(Student student){

        String url = restBaseUrl + SAVE_STUDENT;

        HttpEntity<Student> entity = new HttpEntity<>(student, getStudentHeaders()); //setting request body headers

        return restTemplate.postForObject(url,entity,String.class);

    }

    private HttpHeaders getStudentHeaders(){
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }


    public List<Student> get() {
        String url = restBaseUrl + GET_ALL_STUDENTS;
        return restTemplate.getForObject(url,List.class);
    }

    public Student getById(Integer stuId) {
        String url = restBaseUrl + GET_BY_ID;
        HttpEntity<Integer> entity=new HttpEntity<>(stuId,getStudentHeaders());
        return restTemplate.getForObject(url+"?stuId=" +stuId, Student.class);
    }

    public Student update(Student student) {
        String url = restBaseUrl + UPDATE;
        HttpEntity<Student> entity=new HttpEntity<>(student, getStudentHeaders());
        return restTemplate.postForObject(url,entity,Student.class);
    }
}

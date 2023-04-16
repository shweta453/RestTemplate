package com.crud.springcrudoperations.controller;

import com.crud.springcrudoperations.entity.Student;
import com.crud.springcrudoperations.service.StudentService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration(classes = {StudentController.class, StudentService.class})
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    StudentService service;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    int stuId;

    @BeforeAll
    public void getMockBean() {
        stuId = 1;
    }

    public Student getStudent() {
        Student student = Student.builder()
                .stuId(1).stuName("Shweta").stuCourse("IT").build();
        return student;
    }

    @Test
    void getStudentTest() throws Exception {

       given(service.getById(Mockito.any())).willReturn(getStudent());
        ResultActions actions = this.mvc
                .perform(MockMvcRequestBuilders
                        .get("/getById")
                        .param("stuId", String.valueOf(stuId)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

        MvcResult result = actions.andReturn();
        String contentAsString=result.getResponse().getContentAsString();

        Student student=objectMapper.readValue(contentAsString,
                new TypeReference<Student>() {
                });

        assertEquals("Shweta",student.getStuName());
    }

}
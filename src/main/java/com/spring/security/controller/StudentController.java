package com.spring.security.controller;

import com.spring.security.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class StudentController {

    List<Student> studentList = new ArrayList<>(Arrays.asList(
            new Student(1, "Joy", "user@gmail.com"),
            new Student(2, "Max", "user@gmail.com")
    ));

    @GetMapping("/get-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
        if (csrfToken == null) {
            throw new IllegalStateException("CSRF token is not available");
        }
        return csrfToken;

    }

    // By - default spring security blocks put/patch/post req without a valid csrf token
    @PostMapping("/add-student")
    public Student addStudent(@RequestBody Student student) {
        studentList.add(student);
        return student;
    }

    // By - default spring security keeps get request without requiring any auth/token
    @GetMapping("/get-student")
    public List<Student> getStudent() {
        return studentList ;
    }

}

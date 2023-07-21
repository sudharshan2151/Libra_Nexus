package com.project.dao;

import java.util.List;

import com.project.entity.Student;
import com.project.exception.SomethingWentWrongException;

public interface StudentDAO {
    void addStudent(Student student) throws SomethingWentWrongException;
    void updateStudent(Student student) throws SomethingWentWrongException;
    void removeStudent(int studentId) throws SomethingWentWrongException;
    Student getStudentById(int studentId) throws SomethingWentWrongException;
    //Student getStudentByEmail(String email);
    List<Student> getAllStudents() throws SomethingWentWrongException;
}


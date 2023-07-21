package com.project.service;

import java.util.List;

import com.project.dao.StudentDAO;
import com.project.dao.StudentDAOImpl;
import com.project.entity.Student;
import com.project.exception.NoRecordFoundException;
import com.project.exception.SomethingWentWrongException;

public class StudentServiceImpl implements StudentService{

	@Override
	public void addStudent(Student student) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		StudentDAO k = new StudentDAOImpl();
		k.addStudent(student);
	}

	@Override
	public void updateStudent(Student student) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		StudentDAO k = new StudentDAOImpl();
		k.updateStudent(student);
	}

	@Override
	public void removeStudent(int studentId) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		StudentDAO k = new StudentDAOImpl();
		k.removeStudent(studentId);
		
	}

	@Override
	public Student getStudentById(int studentId) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		StudentDAO k = new StudentDAOImpl();
		
		return k.getStudentById(studentId);
	}

	@Override
	public List<Student> getAllStudents() throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		StudentDAO k = new StudentDAOImpl();
		//k.getAllStudents().forEach(p->System.out.println(p.getPassword()));
		return k.getAllStudents();
	}

	public Student loginStudent(String email, String password) throws NoRecordFoundException, SomethingWentWrongException {
		//List<Student> list = getAllStudents();
        List<Student> k = getAllStudents().stream().filter(p->p.getEmail().equalsIgnoreCase(email)&&p.getPassword().equalsIgnoreCase(password)).toList();
//        if (student != null && student.getPassword().equals(password)) {
//            return student;
//        }
        if(k.size()<=0) {
        	new NoRecordFoundException("Login Failed=======>Invalid Username Or password");
        }
       // System.out.println(k);
        return k.get(0); // Login failed
    }
}

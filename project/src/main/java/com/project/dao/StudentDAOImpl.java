package com.project.dao;

import java.util.List;


import com.project.entity.Student;
import com.project.exception.NoRecordFoundException;
import com.project.exception.SomethingWentWrongException;
import com.project.utility.DbUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class StudentDAOImpl implements StudentDAO{

	@Override
	public void addStudent(Student student) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		EntityManager em = DbUtil.getConnection();
		try {
			Student st = em.find(Student.class, student.getId());
			if(st!=null) {
				throw new SomethingWentWrongException("Already Data Exist");
			}
			em.getTransaction().begin();
			em.persist(student);
			em.getTransaction().commit();
			
		}catch(Exception e) {
			em.getTransaction().rollback();
			throw new SomethingWentWrongException(e.getMessage());
		}finally {
			em.close();
		}
		
		
	}

	@Override
	public void updateStudent(Student student) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		EntityManager em = DbUtil.getConnection();
		try {
			Student st = em.find(Student.class, student.getId());
			if(st==null) {
				throw new NoRecordFoundException("No Data Exist");
			}
			em.getTransaction().begin();
			em.merge(student);
			em.getTransaction().commit();
			
		}catch(Exception e) {
			em.getTransaction().rollback();
			throw new SomethingWentWrongException(e.getMessage());
		}finally {
			em.close();
		}
		
	}

	@Override
	public void removeStudent(int studentId) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		EntityManager em = DbUtil.getConnection();
		try {
			Student st = em.find(Student.class,studentId);
			if(st==null) {
				throw new NoRecordFoundException("No Data Exist");
			}
			em.getTransaction().begin();
			em.remove(st);
			em.getTransaction().commit();
			
		}catch(Exception e) {
			em.getTransaction().rollback();
			throw new SomethingWentWrongException(e.getMessage());
		}finally {
			em.close();
		}
		
	}

	@Override
	public Student getStudentById(int studentId) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		EntityManager em = DbUtil.getConnection();
		try {
			Student st = em.find(Student.class, studentId);
			if(st==null) {
				throw new NoRecordFoundException("No Data Exist");
			}
			return st;
			
		}catch(Exception e) {
			em.getTransaction().rollback();
			throw new SomethingWentWrongException(e.getMessage());
		}finally {
			em.close();
		}
	}

	
	@Override
	public List<Student> getAllStudents() throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		EntityManager em = DbUtil.getConnection();
		try {
			Query que = em.createQuery("SELECT e FROM Student e");
			List<Student> k = que.getResultList();
			if(k.size()==0) {
				throw new NoRecordFoundException("No Data Exist");
			}
			return k;
			
		}catch(Exception e) {
			em.getTransaction().rollback();
			throw new SomethingWentWrongException(e.getMessage());
		}finally {
			em.close();
		}
	}
	
	

}

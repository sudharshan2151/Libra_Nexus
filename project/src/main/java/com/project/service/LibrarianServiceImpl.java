package com.project.service;

import java.util.List;

import com.project.dao.LibrarianDAO;
import com.project.dao.LibrarianDAOImpl;
import com.project.dao.StudentDAO;
import com.project.dao.StudentDAOImpl;
import com.project.entity.Librarian;
import com.project.entity.Student;
import com.project.exception.NoRecordFoundException;
import com.project.exception.SomethingWentWrongException;

public class LibrarianServiceImpl implements LibrarianService{
	LibrarianDAO k = new LibrarianDAOImpl();

	@Override
	public void addLibrarian(Librarian librarian) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		k.addLibrarian(librarian);
		
	}

	@Override
	public void updateLibrarian(Librarian librarian) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		k.updateLibrarian(librarian);
		
	}

	@Override
	public void removeLibrarian(Librarian librarian) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		k.removeLibrarian(librarian);
	}

	@Override
	public Librarian getLibrarianById(int librarianId) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public List<Librarian> getAllLibrarians() throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		return k.getAllLibrarians();
	}
	
	@Override
	public Librarian loginStudent(String email, String password) throws NoRecordFoundException, SomethingWentWrongException {
		//List<Student> list = getAllStudents();
		
        Librarian k1 = k.getAllLibrarians().stream().filter(p->p.getEmail().equals(email)&&p.getPassword().equals(password)).findFirst().orElseThrow(()->new NoRecordFoundException("Login Failed=======>Invalid Username Or password"));
//        if (student != null && student.getPassword().equals(password)) {
//            return student;
//        }
        
       // System.out.println(k);
        return k1; // Login failed
    }

}

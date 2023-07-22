package com.project.service;

import java.util.List;

import com.project.dao.LibrarianDAO;
import com.project.dao.LibrarianDAOImpl;
import com.project.entity.Librarian;
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

}

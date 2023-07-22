package com.project.service;

import java.util.List;

import com.project.entity.Librarian;
import com.project.exception.SomethingWentWrongException;

public interface LibrarianService {
	
	void addLibrarian(Librarian librarian)  throws SomethingWentWrongException ;
    void updateLibrarian(Librarian librarian)  throws SomethingWentWrongException ;
    void removeLibrarian(Librarian librarian) throws SomethingWentWrongException ;
    Librarian getLibrarianById(int librarianId) throws SomethingWentWrongException ;
    List<Librarian> getAllLibrarians()  throws SomethingWentWrongException ;

}

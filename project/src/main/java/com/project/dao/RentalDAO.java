package com.project.dao;

import java.util.List;

import com.project.entity.Rental;
import com.project.exception.SomethingWentWrongException;

public interface RentalDAO {
	
	    void addRental(Rental rental) throws SomethingWentWrongException ;
	    void updateRental(Rental rental) throws SomethingWentWrongException ;
	    Rental getRentalById(int rentalId) throws SomethingWentWrongException ;
	    List<Rental> getAllRentals() throws SomethingWentWrongException ;
	    List<Rental> getRentalsByStudentId(int studentId) throws SomethingWentWrongException ;
	    // You can add additional methods as needed
		Rental getRentalByStudentAndBook(int studentId, int bookId) throws SomethingWentWrongException;
	


}

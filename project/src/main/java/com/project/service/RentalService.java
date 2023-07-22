package com.project.service;

import java.util.List;

import com.project.entity.Rental;
import com.project.exception.SomethingWentWrongException;

public interface RentalService {
	    void addRental(Rental rental) throws SomethingWentWrongException;
	    void updateRental(Rental rental) throws SomethingWentWrongException;
	    Rental getRentalById(int rentalId) throws SomethingWentWrongException;
	    List<Rental> getAllRentals() throws SomethingWentWrongException;
	    List<Rental> getRentalsByStudentId(int studentId) throws SomethingWentWrongException;
	    Rental getRentalByStudentAndBook(int studentId, int bookId) throws SomethingWentWrongException;
	    // You can add additional methods as needed
	}


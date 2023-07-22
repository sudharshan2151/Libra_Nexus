package com.project.service;

import java.util.List;

import com.project.dao.RentalDAO;
import com.project.dao.RentalDAOImpl;
import com.project.entity.Rental;
import com.project.exception.SomethingWentWrongException;

public class RentalServiceImpl implements RentalService {
    private final RentalDAO rentalDAO = new RentalDAOImpl();
    

    @Override
    public void addRental(Rental rental) throws SomethingWentWrongException {
        rentalDAO.addRental(rental);
    }

    @Override
    public void updateRental(Rental rental) throws SomethingWentWrongException {
        rentalDAO.updateRental(rental);
    }

    @Override
    public Rental getRentalById(int rentalId) throws SomethingWentWrongException {
        return rentalDAO.getRentalById(rentalId);
    }

    @Override
    public List<Rental> getAllRentals() throws SomethingWentWrongException {
        return rentalDAO.getAllRentals();
    }

    @Override
    public List<Rental> getRentalsByStudentId(int studentId) throws SomethingWentWrongException {
        return rentalDAO.getRentalsByStudentId(studentId);
    }

//	@Override
//	public Rental getRentalByStudentAndBook(int studentId, int bookId) throws SomethingWentWrongException {
//		// TODO Auto-generated method stub
//		return null;
//	}

    @Override
    public Rental getRentalByStudentAndBook(int studentId, int bookId) throws SomethingWentWrongException {
        return rentalDAO.getRentalByStudentAndBook(studentId, bookId);
    }

    }



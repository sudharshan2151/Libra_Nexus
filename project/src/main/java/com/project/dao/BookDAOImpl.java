package com.project.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.entity.Availability;
import com.project.entity.Book;
import com.project.entity.Feedback;
import com.project.entity.Rental;
import com.project.exception.NoRecordFoundException;
import com.project.exception.SomethingWentWrongException;
import com.project.service.RentalService;
import com.project.utility.DbUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class BookDAOImpl implements BookDAO {
    

    @Override
    public void addBook(Book book) throws SomethingWentWrongException {
        EntityManager entityManager = DbUtil.getConnection();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new SomethingWentWrongException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void updateBook(Book book) throws SomethingWentWrongException {
        EntityManager entityManager = DbUtil.getConnection();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            Book book1 = entityManager.find(Book.class, book.getId());
            if(book1==null) {
            	throw new NoRecordFoundException("NO books is available");
            }
            transaction.begin();
            entityManager.merge(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new SomethingWentWrongException("NO books is available");
        } finally {
            entityManager.close();
        }
    }

//    @Override
//    public void removeBook(int bookId) throws SomethingWentWrongException {
//        EntityManager entityManager = DbUtil.getConnection();
//        EntityTransaction transaction = null;
//
//        try {
//            transaction = entityManager.getTransaction();
//            transaction.begin();
//
//            Book book = entityManager.find(Book.class, bookId);
//            if (book == null) {
//                throw new NoRecordFoundException("No book is available with the given ID");
//            }
//
//            // Delete the associated rentals
//            RentalDAO rentalDAO = new RentalDAOImpl();
//            List<Rental> rentals = rentalDAO.getRentalByBookk(bookId);
//            if (rentals != null && !rentals.isEmpty()) {
//                entityManager.createQuery("DELETE FROM Rental r WHERE r.book.id = :bookId")
//                            .setParameter("bookId", bookId)
//                            .executeUpdate();
//            }
//
//            entityManager.createQuery("DELETE FROM Feedback f WHERE f.book.id = :bookId")
//            .setParameter("bookId", bookId)
//            .executeUpdate();
//
//            // Delete the references to feedback entries from the books_feedbacks table
//            entityManager.createQuery("DELETE FROM Book b WHERE b.id = :bookId")
//                        .setParameter("bookId", bookId)
//                        .executeUpdate();
//
//            // Delete the associated feedback entries for the given bookId
//            
//
//
//            // Now delete the book
//            entityManager.remove(book);
//
//            // Commit the transaction after all deletions
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null && transaction.isActive()) {
//                transaction.rollback();
//            }
//            throw new SomethingWentWrongException(e.getMessage());
//        } finally {
//            entityManager.close();
//        }
//    }
    @Override
    public void removeBook(int bookId) throws SomethingWentWrongException {
        EntityManager entityManager = DbUtil.getConnection();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Book book = entityManager.find(Book.class, bookId);
            if (book == null) {
                throw new NoRecordFoundException("No book is available with the given ID");
            }

            // Delete the associated rentals
            RentalDAO rentalDAO = new RentalDAOImpl();
            List<Rental> rentals = rentalDAO.getRentalByBookk(bookId);
            if (rentals != null && !rentals.isEmpty()) {
                entityManager.createQuery("DELETE FROM Rental r WHERE r.book.id = :bookId")
                            .setParameter("bookId", bookId)
                            .executeUpdate();
            }

            // Delete the references to feedback entries from the books_feedbacks table
            entityManager.createQuery("DELETE FROM Books_Feedbacks bf WHERE bf.book.id = :bookId")
                        .setParameter("bookId", bookId)
                        .executeUpdate();

            // Delete the associated feedback entries for the given bookId
            entityManager.createQuery("DELETE FROM Feedback f WHERE f.book.id = :bookId")
                        .setParameter("bookId", bookId)
                        .executeUpdate();

            // Now delete the book
            entityManager.remove(book);

            // Commit the transaction after all deletions
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new SomethingWentWrongException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }



    @Override
    public Book getBookById(int bookId) throws SomethingWentWrongException {
    	EntityManager entityManager = DbUtil.getConnection();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            Book book = entityManager.find(Book.class, bookId);
            if(book==null) {
            	throw new NoRecordFoundException("NO books is available");
            }
            return book;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new SomethingWentWrongException("NO books is available");
        } finally {
            entityManager.close();
        }
    }

	

	//@SuppressWarnings("unchecked")
	@SuppressWarnings({ "unchecked", "unchecked", "unchecked" })
	@Override
	public List<Book> getAvailableBooks() throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		EntityManager entityManager = DbUtil.getConnection();
        try {
        	Query query = entityManager.createQuery("SELECT b FROM Book b WHERE b.availability = :availability", Book.class);
            query.setParameter("availability",Availability.AVAILABLE);
            List<Book> k = query.getResultList();
           // System.out.println(k);
            if(k==null || k.size()==0) {
            	throw new NoRecordFoundException("NO books is available");
            }
            return k;
        }catch(Exception e) { 
        	throw new SomethingWentWrongException("NO books is available");
        }finally {
 
            entityManager.close();
        }
		
	}

	@Override
	public List<Book> getAllBooks() throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		Connection con=null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Sudhar@2001");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
	       //Statement sta = con.createStatement();
	       
	       PreparedStatement sta = con.prepareStatement("SELECT * FROM BOOKS ");
	       ResultSet set = sta.executeQuery();
	       List<Book> list = new ArrayList<>();
			boolean found = true;
			while(set.next()) {
				found = false;
				list.add(new Book(set.getInt(1),set.getString(5),set.getString(2),set.getString(4),Availability.valueOf(set.getString(3))));
			}
			if(found) {
				throw new NoRecordFoundException("No Books found");
			}
			return list;
		}
			catch(Exception e) { 
	        	throw new SomethingWentWrongException("NO books is available");
	        }finally {
	 
	            try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
		
	
	}


   
}

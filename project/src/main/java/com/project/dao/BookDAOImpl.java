package com.project.dao;


import java.util.List;

import com.project.entity.Availability;
import com.project.entity.Book;
import com.project.exception.NoRecordFoundException;
import com.project.exception.SomethingWentWrongException;
import com.project.utility.DbUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

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

    @Override
    public void removeBook(int bookId) throws SomethingWentWrongException {
        EntityManager entityManager = DbUtil.getConnection();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            Book book = entityManager.find(Book.class, bookId);
            if(book==null) {
            	throw new NoRecordFoundException("NO books is available");
            }
            transaction.begin();
            
            
                entityManager.remove(book);
        
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
	//@SuppressWarnings("unchecked")
	@Override
	public List<Book> getAllBooks() throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		EntityManager entityManager = DbUtil.getConnection();
		 try {
	           Query query = entityManager.createNativeQuery("SELECT * FROM BOOKS",Book.class);
	            List<Book> k = query.getResultList();
	            System.out.println(k);
	            if(k==null) {
	            	throw new NoRecordFoundException("NO books is available");
	            }
	            return k;
	        }catch(Exception e) { 
	        	throw new SomethingWentWrongException("NO books is available");
	        }finally {
	 
	            entityManager.close();
	        }
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getAvailableBooks() throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		EntityManager entityManager = DbUtil.getConnection();
        try {
        	Query query = entityManager.createQuery("SELECT b FROM Book b WHERE b.availability = :availability", Book.class);
            query.setParameter("availability",Availability.AVAILABLE);
            List<Book> k = query.getResultList();
            System.out.println(k);
            if(k==null) {
            	throw new NoRecordFoundException("NO books is available");
            }
            return k;
        }catch(Exception e) { 
        	throw new SomethingWentWrongException("NO books is available");
        }finally {
 
            entityManager.close();
        }
		
	}
    
   
}

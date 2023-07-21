package com.project.dao;

import java.util.List;

import com.project.entity.Librarian;
import com.project.exception.SomethingWentWrongException;
import com.project.utility.DbUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class LibrarianDAOImpl implements LibrarianDAO{

	    @Override
	    public void addLibrarian(Librarian librarian) throws SomethingWentWrongException {
	        EntityManager entityManager = DbUtil.getConnection();
	        try {
	            entityManager.getTransaction().begin();
	            entityManager.persist(librarian);
	            entityManager.getTransaction().commit();
	        } catch (Exception ex) {
	            entityManager.getTransaction().rollback();
	            throw new SomethingWentWrongException("Failed to add librarian."+ ex);
	        } finally {
	            entityManager.close();
	        }
	    }

	    @Override
	    public void updateLibrarian(Librarian librarian) {
	    	 EntityManager entityManager = DbUtil.getConnection();
	        try {
	            entityManager.getTransaction().begin();
	            entityManager.merge(librarian);
	            entityManager.getTransaction().commit();
	        } catch (Exception ex) {
	            entityManager.getTransaction().rollback();
	            throw new SomethingWentWrongException(("Failed to update librarian."+ ex);
	        } finally {
	            entityManager.close();
	        }
	    }

	    @Override
	    public void removeLibrarian(Librarian librarian) throws SomethingWentWrongException {
	    	 EntityManager entityManager = DbUtil.getConnection();
	        try {
	            entityManager.getTransaction().begin();
	            librarian = entityManager.merge(librarian);
	            entityManager.remove(librarian);
	            entityManager.getTransaction().commit();
	        } catch (Exception ex) {
	            entityManager.getTransaction().rollback();
	            throw new SomethingWentWrongException("Failed to remove librarian."+ ex);
	        } finally {
	            entityManager.close();
	        }
	    }

	    @Override
	    public Librarian getLibrarianById(int librarianId) throws SomethingWentWrongException {
	    	 EntityManager entityManager = DbUtil.getConnection();
	        try {
	            return entityManager.find(Librarian.class, librarianId);
	        } catch (Exception ex) {
	            throw new SomethingWentWrongException("Failed to get librarian by ID."+  ex);
	        } finally {
	            entityManager.close();
	        }
	    }

	    @Override
	    public List<Librarian> getAllLibrarians() throws SomethingWentWrongException {
	    	 EntityManager entityManager = DbUtil.getConnection();
	        try {
	            TypedQuery<Librarian> query = entityManager.createQuery("SELECT l FROM Librarian l", Librarian.class);
	            return query.getResultList();
	        } catch (Exception ex) {
	            throw new SomethingWentWrongException("Failed to get all librarians."+ ex);
	        } finally {
	            entityManager.close();
	        }
	    }
	

}

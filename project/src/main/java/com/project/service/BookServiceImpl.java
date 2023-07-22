package com.project.service;

import java.util.List;

import com.project.dao.BookDAO;
import com.project.dao.BookDAOImpl;
import com.project.entity.Book;
import com.project.exception.NoRecordFoundException;
import com.project.exception.SomethingWentWrongException;

public class BookServiceImpl implements BookService {
    private BookDAO bookDAO=new BookDAOImpl();

    @Override
    public void addBook(Book book) throws SomethingWentWrongException {
        bookDAO.addBook(book);
    }
    @Override
    public void updateBook(Book book) throws SomethingWentWrongException {
        bookDAO.updateBook(book);
    }
    @Override
    public void removeBook(int bookId) throws SomethingWentWrongException {
        bookDAO.removeBook(bookId);
    }

    @Override
    public Book getBookById(int bookId) throws SomethingWentWrongException {
    	
        return bookDAO.getBookById(bookId);
    }

    @Override
    public List<Book> getAllBooks() throws SomethingWentWrongException {
    	BookDAO k = new BookDAOImpl();
    	List<Book> k1 = k.getAllBooks();
        return k1;
    }

    @Override
    public List<Book> searchBooksByTitle(String title) throws NoRecordFoundException, SomethingWentWrongException {
    	List<Book> k = bookDAO.getAllBooks().stream().filter(p->p.getTitle().equals(title)).toList();
    	if(k.size()==0) {
    		throw new NoRecordFoundException("No Book Available with the title");
    	}
        return k;
    }

    @Override
    public List<Book> searchBooksByAuthor(String author) throws NoRecordFoundException, SomethingWentWrongException {
    	List<Book> k = bookDAO.getAllBooks().stream().filter(p->p.getAuthor().equals(author)).toList();
    	if(k.size()==0) {
    		throw new  NoRecordFoundException("No Book Available with the title");
    	}
        return k;
    }

    @Override
    public List<Book> searchBooksByGenre(String genre) throws NoRecordFoundException, SomethingWentWrongException {
    	List<Book> k = bookDAO.getAllBooks().stream().filter(p->p.getGenre().equals(genre)).toList();
    	if(k.size()==0) {
    		throw new NoRecordFoundException("No Book Available with the title");
    	}
        return k;
    }

    @Override
    public List<Book> getAvailableBooks() throws SomethingWentWrongException, NoRecordFoundException {
        return bookDAO.getAvailableBooks();
    }
}

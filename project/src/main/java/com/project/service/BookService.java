package com.project.service;

import java.util.List;

import com.project.entity.Book;
import com.project.exception.NoRecordFoundException;
import com.project.exception.SomethingWentWrongException;

public interface BookService {
    void addBook(Book book) throws SomethingWentWrongException;
    void updateBook(Book book)throws SomethingWentWrongException;
    void removeBook(int bookId)throws SomethingWentWrongException;
    Book getBookById(int bookId)throws SomethingWentWrongException;
    List<Book> getAllBooks()throws SomethingWentWrongException;
    List<Book> searchBooksByTitle(String title)throws NoRecordFoundException, SomethingWentWrongException;
    List<Book> searchBooksByAuthor(String author)throws NoRecordFoundException, SomethingWentWrongException;
    List<Book> searchBooksByGenre(String genre)throws NoRecordFoundException, SomethingWentWrongException;
    List<Book> getAvailableBooks() throws SomethingWentWrongException, NoRecordFoundException;
}


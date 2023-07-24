package com.project.dao;
import java.util.List;

import com.project.entity.Book;
import com.project.exception.NoRecordFoundException;
import com.project.exception.SomethingWentWrongException;

public interface BookDAO {
    void addBook(Book book) throws SomethingWentWrongException;
    void updateBook(Book book) throws SomethingWentWrongException;
    void removeBook(int bookId) throws SomethingWentWrongException;
    @SuppressWarnings("unchecked")
    Book getBookById(int bookId) throws SomethingWentWrongException;
    @SuppressWarnings("unchecked")
    List<Book> getAllBooks() throws SomethingWentWrongException;
    List<Book> getAvailableBooks() throws SomethingWentWrongException, NoRecordFoundException;
}

package com.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "feedbacks")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false)
    private String feedback;

    @Column(nullable = false)
    private int rating;

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", book=" + book +
                ", feedback='" + feedback + '\'' +
                ", rating=" + rating +
                '}';
    }

	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Feedback(Student student, Book book, String feedback, int rating) {
		super();
		this.student = student;
		this.book = book;
		this.feedback = feedback;
		this.rating = rating;
	}

	public Feedback(int id, Student student, Book book, String feedback, int rating) {
		super();
		this.id = id;
		this.student = student;
		this.book = book;
		this.feedback = feedback;
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}


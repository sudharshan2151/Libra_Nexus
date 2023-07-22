package com.project.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    public Rental() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rental(int id, Student student, Book book, Date rentalDate, Date returnDate) {
		super();
		this.id = id;
		this.student = student;
		this.book = book;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
	}

	public ReturnStatus getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(ReturnStatus returnStatus) {
		this.returnStatus = returnStatus;
	}

	@ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "rental_date", nullable = false)
    private Date rentalDate;

    @Enumerated(EnumType.STRING)
    private ReturnStatus returnStatus;
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

	public Date getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	@Column(name = "return_date")
    private Date returnDate;

    

    @Override
	public String toString() {
		return "Rental [id=" + id + ", student=" + student + ", book=" + book + ", rentalDate=" + rentalDate
				+ ", returnStatus=" + returnStatus + ", returnDate=" + returnDate + "]";
	}
}
 
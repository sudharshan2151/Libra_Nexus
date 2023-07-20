package com.project.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

	@ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "rental_date", nullable = false)
    private Date rentalDate;

    @Column(name = "return_date")
    private Date returnDate;

    // Other attributes, constructors, getters/setters

    // Constructors, getters, and setters as before

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", student=" + student +
                ", book=" + book +
                ", rentalDate=" + rentalDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
 
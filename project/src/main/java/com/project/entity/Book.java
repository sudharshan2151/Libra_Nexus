package com.project.entity;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String genre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Availability availability;
    
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Feedback> feedBack;
    
    
   

	

	public List<Feedback> getFeedBack() {
		return feedBack;
	}


	public void setFeedBack(Feedback feedBack) {
		this.feedBack.add(feedBack);
	}


	@Override
        	    public String toString() {
            return "Book ID: " + id + "\n"
                 + "Title: " + title + "\n"
                 + "Author: " + author + "\n"
                 + "Genre: " + genre + "\n"
                 + "Availability: " + availability;
        }
    


	public int getId() {
		return id;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Availability getAvailability() {
		return availability;
	}

	public void setAvailability(Availability availability) {
		this.availability = availability;
	}

	public Book(String title, String author, String genre, Availability availability) {
		super();
		
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.availability = availability;
	}

	public Book(int id, String title, String author, String genre, Availability availability) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.availability = availability;
	}
}

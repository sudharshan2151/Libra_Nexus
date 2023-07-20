package com.project.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    // Other attributes, constructors, getters/setters

    public boolean isAvailable() {
        return availability == Availability.AVAILABLE;
    }

    public void setAvailable(boolean available) {
        this.availability = available ? Availability.AVAILABLE : Availability.NOT_AVAILABLE;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", availability=" + availability +
                '}';
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

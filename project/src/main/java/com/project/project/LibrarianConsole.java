package com.project.project;
import java.util.List;
import java.util.Scanner;

import com.project.entity.Availability;
import com.project.entity.Book;
import com.project.entity.Feedback;
import com.project.entity.Librarian;
import com.project.entity.Rental;
import com.project.exception.NoRecordFoundException;
import com.project.exception.SomethingWentWrongException;
import com.project.service.BookService;
import com.project.service.BookServiceImpl;
import com.project.service.FeedbackService;
import com.project.service.FeedbackServiceImpl;
import com.project.service.LibrarianService;
import com.project.service.LibrarianServiceImpl;
import com.project.service.RentalService;
import com.project.service.RentalServiceImpl;
import com.project.service.StudentService;
import com.project.service.StudentServiceImpl;

public class LibrarianConsole {
	
	    private  static BookService bookService = new BookServiceImpl();
	    private static StudentService studentService = new StudentServiceImpl();
	    private static RentalService rental = new RentalServiceImpl();
	    private static FeedbackService feedback = new FeedbackServiceImpl();
	    private static  Scanner scanner = new Scanner(System.in);

	    public static void run(Librarian k ) {
	    	boolean running = true;
	        while (running) {
	            System.out.println("Welcome, Librarian!"+k.getName());
	            System.out.println("1. Add new book");
	            System.out.println("2. Update book information");
	            System.out.println("3. Remove book");
	            System.out.println("4. View student rentals");
	            System.out.println("5. View feedback and ratings");
	            System.out.println("6. Log out");
	            //System.out.println("7. Exit");
	            System.out.print("Enter your choice: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine(); // Consume the newline character

	            switch (choice) {
	                case 1:
	                    addNewBook();
	                    break;
	                case 2:
	                    updateBookInformation();
	                    break;
	                case 3:
	                    removeBook();
	                    break;
	                case 4:
	                    viewStudentRentals();
	                    break;
	                case 5:
	                    viewFeedbackAndRatings();
	                    break;
	                case 6:
	                	running = false;
	                    System.out.println("You have been logged out.");
	                    return;
	                case 7:
	                    running = false;
	                    System.out.println("Thank you for using the Library Management System. Goodbye!");
	                    break;
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        }
	    }

	    private static void addNewBook() {
	        System.out.println("Enter the details of the new book:");

	        System.out.print("Title: ");
	        String title = scanner.nextLine();

	        System.out.print("Author: ");
	        String author = scanner.nextLine();

	        System.out.print("Genre: ");
	        String genre = scanner.nextLine();

	        Book book = new Book(title, author, genre, Availability.AVAILABLE);
	        try {
				bookService.addBook(book);
				System.out.println("Added Sucessfully----------->-------");
			} catch (SomethingWentWrongException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}

	        System.out.println("Book added successfully.");
	    }

	    private static void updateBookInformation() {
	        System.out.print("Enter the ID of the book you want to update: ");
	        int bookId = scanner.nextInt();
	        scanner.nextLine(); // Consume the newline character

	        Book book=null;
			try {
				book = bookService.getBookById(bookId);
			} catch (SomethingWentWrongException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
	      

	        System.out.println("Enter the updated details of the book:");

	        System.out.print("Title: ");
	        String title = scanner.nextLine();

	        System.out.print("Author: ");
	        String author = scanner.nextLine();

	        System.out.print("Genre: ");
	        String genre = scanner.nextLine();

	        book.setTitle(title);
	        book.setAuthor(author);
	        book.setGenre(genre);

	        try {
				bookService.updateBook(book);
				System.out.println("Book information updated successfully.");
			} catch (SomethingWentWrongException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}

	        
	    }

	    private static void removeBook() {
	        System.out.print("Enter the ID of the book you want to remove: ");
	        int bookId = Integer.parseInt(scanner.nextLine());
	         // Consume the newline character

	        BookService k = new BookServiceImpl();
	    	try {
				 k.removeBook(bookId);;
				 System.out.println("Deleted Sucessfully============>");
				//System.out.println(b);
			} catch (SomethingWentWrongException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       
	    }

	    private static void viewStudentRentals() {
//	        System.out.println("Student Rentals:");
	        List<Rental> studentRentals;
			try {
				studentRentals = rental.getAllRentals();
				for (Rental rental : studentRentals) {
		            System.out.println(rental);
		        }
			} catch (SomethingWentWrongException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
	        
	    }

	    private static void viewFeedbackAndRatings() {
//	        System.out.println("Feedback and Ratings:");
	        List<Feedback> feedbacks;
			try {
				feedbacks = feedback.getAllFeedbacks();
				feedbacks.forEach(System.out::println);
				
			} catch (SomethingWentWrongException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
	        
	    }

	    public static void main(String[] args) {
	        // Initialize the EntityManagerFactory
	        //EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("library_management");

	    	Scanner scanner = new Scanner(System.in);
	        boolean running = true;
	        while (running) {
	            System.out.println("Welcome to the Library Management System!");
	            System.out.println("1. Register for a Librarian account");
	            System.out.println("2. Log in to the Librarian account");
	            System.out.println("3. Exit");
	            System.out.print("Enter your choice: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine(); // Consume the newline character

	            switch (choice) {
	                case 1:
	                  registerStudent();
	                    break;
	                case 2:
	                   loginStudent();
	                    break;
	                case 3:
	                    running = false;
	                    System.out.println("Thank you for using the Library Management System. Goodbye!");
	                    break;
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        }
	        
	    }

		private static void loginStudent() {
			// TODO Auto-generated method stub
			Scanner scanner = new Scanner(System.in);
	        System.out.println("Log in to the student account");
	        System.out.print("Enter your email: ");
	        String email = scanner.nextLine();

	        System.out.print("Enter your password: ");
	        String password = scanner.nextLine();
	        
	        LibrarianService ser = new LibrarianServiceImpl();
	        try {
				Librarian k = ser.loginStudent(email, password);
				//System.out.println(k);
				//System.out.println("Login");
				run(k);
			} catch (NoRecordFoundException | SomethingWentWrongException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}

		private static void registerStudent() {
			// TODO Auto-generated method stub
			Scanner scanner = new Scanner(System.in);
	        System.out.println("Register for a Libranian account");
	        System.out.print("Enter your name: ");
	        String name = scanner.nextLine();

	        System.out.print("Enter your email: ");
	        String email = scanner.nextLine();

	        System.out.print("Enter your password: ");
	        String password = scanner.nextLine();

	        Librarian newStudent = new Librarian(name, email, password);
	        LibrarianService ser = new LibrarianServiceImpl();
	        try {
				ser.addLibrarian(newStudent);
				System.out.println("Registered SucessfullY==================");
			} catch (SomethingWentWrongException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}

}

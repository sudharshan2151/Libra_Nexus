package com.project.project;

import java.util.Scanner;

import com.project.entity.Availability;
import com.project.entity.Book;
import com.project.entity.Librarian;
import com.project.entity.Student;
import com.project.exception.NoRecordFoundException;
import com.project.exception.SomethingWentWrongException;
import com.project.service.BookService;
import com.project.service.BookServiceImpl;
import com.project.service.LibrarianService;
import com.project.service.LibrarianServiceImpl;
import com.project.service.StudentService;
import com.project.service.StudentServiceImpl;

public class LibrarianConsole {
	
	    private  static BookService bookService = new BookServiceImpl();
	    private static StudentService studentService = new StudentServiceImpl();
	    private static  Scanner scanner = new Scanner(System.in);

	   

	    public static void run() {
	    	boolean running = true;
	        while (running) {
	            System.out.println("Welcome, Librarian!");
	            System.out.println("1. Add new book");
	            System.out.println("2. Update book information");
	            System.out.println("3. Remove book");
	            System.out.println("4. View student rentals");
	            System.out.println("5. View feedback and ratings");
	            System.out.println("6. Log out");
	            System.out.println("7. Exit");
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
			} catch (SomethingWentWrongException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        System.out.println("Book information updated successfully.");
	    }

	    private static void removeBook() {
	        System.out.print("Enter the ID of the book you want to remove: ");
	        int bookId = scanner.nextInt();
	        scanner.nextLine(); // Consume the newline character

	        Book book=null;
			try {
				book = bookService.getBookById(bookId);
			} catch (SomethingWentWrongException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
	        try {
				bookService.removeBook(book.getId());
			} catch (SomethingWentWrongException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}

	        System.out.println("Book removed successfully.");
	    }

	    private static void viewStudentRentals() {
//	        System.out.println("Student Rentals:");
//	        List<Rental> studentRentals = studentService.getAllRentals();
//	        for (Rental rental : studentRentals) {
//	            System.out.println(rental);
//	        }
	    }

	    private static void viewFeedbackAndRatings() {
//	        System.out.println("Feedback and Ratings:");
//	        List<Feedback> feedbacks = studentService.getAllFeedbacks();
//	        for (Feedback feedback : feedbacks) {
//	            System.out.println(feedback);
//	        }
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
	        // Create instances of the services using the DAO implementation

	        // Run the application
	      //run();
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
				Student k = ser.(email, password);
				//System.out.println(k);
				//mainSub(k);
			} catch (NoRecordFoundException | SomethingWentWrongException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}

		private static void registerStudent() {
			// TODO Auto-generated method stub
			Scanner scanner = new Scanner(System.in);
	        System.out.println("Register for a student account");
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
				e.printStackTrace();
			}
		}
		
		
		

		
	

}

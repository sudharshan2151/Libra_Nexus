package com.project.project;

import java.util.Scanner;

import com.project.entity.Student;
import com.project.exception.NoRecordFoundException;
import com.project.exception.SomethingWentWrongException;
import com.project.service.StudentService;
import com.project.service.StudentServiceImpl;

public class StudentConsole {
    
	 private static void loginStudent() {
	    	Scanner scanner = new Scanner(System.in);
	        System.out.println("Log in to the student account");
	        System.out.print("Enter your email: ");
	        String email = scanner.nextLine();

	        System.out.print("Enter your password: ");
	        String password = scanner.nextLine();
	        
	        StudentService ser = new StudentServiceImpl();
	        try {
				Student k = ser.loginStudent(email, password);
				//System.out.println(k);
				mainSub(k);
			} catch (NoRecordFoundException | SomethingWentWrongException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
	       
	    }

    private static void mainSub(Student stu) {
    	  // Example:
    	Scanner br =new Scanner(System.in);
    	int choice ;
    	do {
    		choice = Integer.parseInt(br.nextLine());
	        System.out.println("Welcome, Student: "+stu.getName());
	        System.out.println("1. View available books in the library");
	        System.out.println("2. Apply filters and sorting options to search and browse books");
	        System.out.println("3. Rent a book");
	        System.out.println("4. Return a rented book");
	        System.out.println("5. Provide feedback and ratings on");
	        System.out.println("0. Exit");
	        switch (choice) {
            case 1:
                // Register for a librarian account
                // Read necessary information from the user
                // Create a new Librarian object and call librarianService.addLibrarian() to add to the system
            	//addStudent(br);
                break;
            case 2:
                // Log in to the librarian account
                // Read the email and password from the user
                // Call librarianService.getLibrarianByEmail() to retrieve the librarian by email
                // Check if the retrieved librarian's password matches the provided password
                break;
            case 3:
                // Add new books to the library system
                // Read necessary information from the user
                // Create a new Book object and call bookService.addBook() to add to the system
                break;
            case 4:
                // Update book information
                // Read book ID from the user
                // Retrieve the book by ID using bookService.getBookById()
                // Update the book object with new details
                // Call bookService.updateBook() to update the book in the system
                break;
            case 5:
                // Remove books from the system
                // Read book ID from the user
                // Call bookService.removeBook() to remove the book from the system
            case 0:
            	choice = 0;
                System.out.println("Exiting...");
                break;
            default:
            	System.out.println("Invalid choice. Please try again.");
	      }
    	} while (choice != 0);

    	}

	
	 public static void main(String[] args) {
		 Scanner scanner = new Scanner(System.in);
	        boolean running = true;
	        while (running) {
	            System.out.println("Welcome to the Library Management System!");
	            System.out.println("1. Register for a student account");
	            System.out.println("2. Log in to the student account");
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

	    private static void registerStudent() {
	    	Scanner scanner = new Scanner(System.in);
	        System.out.println("Register for a student account");
	        System.out.print("Enter your name: ");
	        String name = scanner.nextLine();

	        System.out.print("Enter your email: ");
	        String email = scanner.nextLine();

	        System.out.print("Enter your password: ");
	        String password = scanner.nextLine();

	        Student newStudent = new Student(name, email, password);
	        StudentService ser = new StudentServiceImpl();
	        try {
				ser.addStudent(newStudent);
				System.out.println("Registered SucessfullY==================");
			} catch (SomethingWentWrongException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	   
	}
	
        // Implement the console-based application for the student
        // You can use the scanner to take input from the user and interact with the service and DAO

      



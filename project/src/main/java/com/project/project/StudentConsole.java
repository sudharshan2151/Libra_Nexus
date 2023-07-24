package com.project.project;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import com.project.entity.Availability;
import com.project.entity.Book;
import com.project.entity.Feedback;
import com.project.entity.Rental;
import com.project.entity.ReturnStatus;
import com.project.entity.Student;
import com.project.entity.StudentStatus;
import com.project.exception.NoRecordFoundException;
import com.project.exception.SomethingWentWrongException;
import com.project.service.BookService;
import com.project.service.BookServiceImpl;
import com.project.service.FeedbackService;
import com.project.service.FeedbackServiceImpl;
import com.project.service.RentalService;
import com.project.service.RentalServiceImpl;
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
				System.out.println("Loggedin Sucessfully===========>");
				mainSub(k);
			} catch (NoRecordFoundException | SomethingWentWrongException e) {
				// TODO Auto-generated catch block
				System.out.println(colorChange.ANSI_RED+e.getMessage()+colorChange.ANSI_RESET);
			}
	       
	    }

    private static void mainSub(Student stu) {
    	  // Example:
    	
    	System.out.println("====================================================");
    	Scanner br =new Scanner(System.in);
    	
    	int choice ;
    	do {
    		System.out.println("=================================================================");
    		System.out.println("=> Welcome, Student: "+stu.getName());
	        System.out.println("=> 1. View all books in the library");
	        System.out.println("=> 2. Apply filters and sorting options to search and browse books");
	        System.out.println("=> 3. Rent a book");
	        System.out.println("=> 4. Return a rented book");
	        System.out.println("=> 5. View all rented book");
	        System.out.println("=> 6. View available books");
	        System.out.println("=> 7. Delete the account");
	        System.out.println("=> 8. Change the Password");
	        System.out.println("=> 0. LogOut");
	        System.out.println("===================================================================");
    		choice = Integer.parseInt(br.nextLine());
	        
	        switch (choice) {
            case 1:
               
            	viewAvailabile();
                break;
            case 2:
            	showMenu();
                break;
            case 3:
            	rent(stu,br);
                break;
            case 4:
              
            	returnBook(stu);
                break;
            case 5:
                
            	rentById(stu);
            	break;
            case 6:
            	availble();
            	break;
            case 7:
            	if(deleteAccount(br)) {
            		choice=0;
            	}
            	break;
            case 8:
            	changePassword(br);
            	break;
            case 0:
            	choice = 0;
                System.out.println("Loging Out .....................");
                break;
            default:
            	System.out.println("Invalid choice. Please try again.");
	      }
    	} while (choice != 0);

    	}

    
	
	 private static void returnBook(Student stu) {
		// TODO Auto-generated method stub
		 Scanner scanner = new Scanner(System.in);
	        System.out.print("Enter the book ID or title you want to return: ");
	        int bookIdentifier = Integer.parseInt(scanner.nextLine());
	        BookService bookService=new BookServiceImpl();

	        Book book=null;
			try {
				book = bookService.getBookById(bookIdentifier);
			} catch (SomethingWentWrongException e) {
				// TODO Auto-generated catch block
				System.out.println(colorChange.ANSI_RED+e.getMessage()+colorChange.ANSI_RESET);
			}

	        if (book != null && book.getAvailability() == Availability.NOT_AVAILABLE) {
	            // Check if the book is rented by the student (you may need additional checks here)
	            Rental rental =null;
	            RentalService rentalService = new RentalServiceImpl();
				try {
					rental = rentalService.getRentalByStudentAndBook(stu.getId(), book.getId());
				} catch (SomethingWentWrongException e) {
					// TODO Auto-generated catch block
					System.out.println(colorChange.ANSI_RED+e.getMessage()+colorChange.ANSI_RESET);
				}

	            if (rental != null ) {
	                // Update the return date to the current date
	                rental.setReturnDate(LocalDate.now());
	                rental.setReturnStatus(ReturnStatus.RETURNED);
	                try {
						rentalService.updateRental(rental);
					} catch (SomethingWentWrongException e) {
						// TODO Auto-generated catch block
						System.out.println(colorChange.ANSI_RED+e.getMessage()+colorChange.ANSI_RESET);
					}

	                // Update the book's availability to AVAILABLE
	                book.setAvailability(Availability.AVAILABLE);
	                try {
						bookService.updateBook(book);
					} catch (SomethingWentWrongException e) {
						// TODO Auto-generated catch block
						System.out.println(colorChange.ANSI_RED+e.getMessage()+colorChange.ANSI_RESET);
					}

	                System.out.println(colorChange.ANSI_GREEN+"Book successfully returned."+colorChange.ANSI_RESET);
	                System.out.println("Please provide feedback===========>");
	                feedback(stu,book);
	            } else {
	                System.out.println("You have not rented this book or it has already been returned.");
	            }
	        } else {
	            System.out.println("Book not found or not available for return.");
	        }
	    }

	 
	private static void feedback(Student stu,Book book) {
		// TODO Auto-generated method stub
		Scanner br = new Scanner(System.in);
		System.out.println("Enter the rating out of 5===>");
		int rat = Integer.parseInt(br.nextLine());
		System.out.println("Enter the feedback===>");
		String des = br.nextLine();
		Feedback fed = new Feedback(stu,book,des,rat);
		BookService bs  = new BookServiceImpl();
		FeedbackService fs = new FeedbackServiceImpl();
		try {
			
			fs.addFeedback(fed);
			System.out.println(colorChange.ANSI_GREEN+"Thank You for Your Feedback=========>"+colorChange.ANSI_RESET);
		} catch (SomethingWentWrongException e) {
			System.out.println(colorChange.ANSI_RED+e.getMessage()+colorChange.ANSI_RESET);
		}
		
		
	}

	private static void rentById(Student stu) {
		// TODO Auto-generated method stub
		RentalService k = new RentalServiceImpl();
		try {
			k.getRentalsByStudentId(stu.getId()).forEach(System.out::println);
		} catch (SomethingWentWrongException e) {
			// TODO Auto-generated catch block
			System.out.println(colorChange.ANSI_RED+e.getMessage()+colorChange.ANSI_RESET);
		}
		
	}

	private static void rent(Student stu,Scanner br) {
		// TODO Auto-generated method stub
		// Get book ID from the student
	        // ...
		 
		 System.out.println("Enter the BookId");
		 int bookId = Integer.parseInt(br.nextLine());

	        // Retrieve the book from the database
		 	BookService bookService = new BookServiceImpl();
	        Book book=null;
			try {
				book = bookService.getBookById(bookId);
				System.out.println(book);
			} catch (SomethingWentWrongException e) {
				// TODO Auto-generated catch block
				System.out.println(colorChange.ANSI_RED+e.getMessage()+colorChange.ANSI_RESET);
			}
	        if (book == null || book.getAvailability() != Availability.AVAILABLE) {
	            System.out.println("Book not available for rent.");
	            return;
	        }

	        // Create a new rental object
	        Rental rental = new Rental();
	        rental.setStudent(stu);
	        rental.setBook(book);
	        rental.setRentalDate(LocalDate.now());
	        rental.setReturnDate(LocalDate.now().plusDays(7));
	        rental.setReturnStatus(ReturnStatus.NOT_RETURNED);

	        // Add the rental to the database
	        RentalService rentalService = new RentalServiceImpl();
	        
	        try {
				rentalService.addRental(rental);
			} catch (SomethingWentWrongException e) {
				// TODO Auto-generated catch block
				System.out.println(colorChange.ANSI_RED+e.getMessage()+colorChange.ANSI_RESET);
			}

	        // Update the book availability to "RENTED"
	        book.setAvailability(Availability.NOT_AVAILABLE);
	        try {
				bookService.updateBook(book);
			} catch (SomethingWentWrongException e) {
				// TODO Auto-generated catch block
				System.out.println(colorChange.ANSI_RED+e.getMessage()+colorChange.ANSI_RESET);
			}

	        System.out.println("Book rented successfully.");
		
	}

	private static void changePassword(Scanner scanner) {
		// TODO Auto-generated method stub
		 System.out.println("Log in to the student account");
	        System.out.print("Enter your email: ");
	        String email = scanner.nextLine();

	        System.out.print("Enter your password: ");
	        String password = scanner.nextLine();
	        
	        StudentService ser = new StudentServiceImpl();
	        try {
				Student k = ser.loginStudent(email, password);
				//System.out.println(k);
				System.out.println("Enter the New Password");
				String pass1 = scanner.nextLine();
				System.out.println("Reenter the Password");
				String pass2 = scanner.nextLine();
				if(pass1.equals(pass2)) {
					k.setPassword(pass2);
					ser.updateStudent(k);
					System.out.println(colorChange.ANSI_GREEN+"Updated Sucessfully------------->"+colorChange.ANSI_RESET);
				}
			} catch (NoRecordFoundException | SomethingWentWrongException e) {
				// TODO Auto-generated catch block
				System.out.println(colorChange.ANSI_RED+e.getMessage()+colorChange.ANSI_RESET);
			}
	       
		
	}

	private static boolean deleteAccount(Scanner scanner) {
		// TODO Auto-generated method stub
		 System.out.println("Log in to the student account");
	        System.out.print("Enter your email: ");
	        String email = scanner.nextLine();

	        System.out.print("Enter your password: ");
	        String password = scanner.nextLine();
	        
	        StudentService ser = new StudentServiceImpl();
	        try {
				Student k = ser.loginStudent(email, password);
				//System.out.println(k);
				k.setS(StudentStatus.NOT_ACTIVE);
				
				RentalService k1 = new RentalServiceImpl();
				List<Rental> rent = k1.getRentalsByStudentId(k.getId()).stream().filter(p->p.getReturnStatus()==ReturnStatus.NOT_RETURNED).toList();
				if(rent.size()==0 || rent==null) {
					ser.updateStudent(k);
					System.out.println(colorChange.ANSI_GREEN+"Deleted Sucessfully------->------"+colorChange.ANSI_RESET);
					return true;
				}else {
					rent.forEach(System.out::println);
					System.out.println("Please Return all the books");
					return false;
				}
				
			} catch (NoRecordFoundException | SomethingWentWrongException e) {
				// TODO Auto-generated catch block
				System.out.println(colorChange.ANSI_RED+e.getMessage()+colorChange.ANSI_RESET);
			}
			return false;
	       
	}

	private static void availble() {
		// TODO Auto-generated method stub
		 BookService k = new BookServiceImpl();
		 try {
			k.getAvailableBooks().forEach(System.out::println);
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(colorChange.ANSI_RED+e.getMessage()+colorChange.ANSI_RESET);
		}
		
	}

	private static void showMenu() {
		// TODO Auto-generated method stub
		
	    	Scanner br =new Scanner(System.in);
	    	
	    	int choice ;
	    	do {
	    		System.out.println("====================================================");
		        System.out.println("=> 1. Search by Book Name");
		        System.out.println("=> 2. Search by Genre");
		        System.out.println("=> 3. Search by Author");
		        System.out.println("=> 0. Exit");
		        System.out.println("====================================================");
	    		choice = Integer.parseInt(br.nextLine());
		        
		        switch (choice) {
	            case 1:
	               
	            	searchBookName(br);
	                break;
	            case 2:
	                
	            	searchBookGenre(br);
	                break;
	            case 3:
	                
	                searchAuthor(br);
	            	break;
	            case 0:
	            	choice = 0;
	                System.out.println("Exiting...");
	                break;
	            default:
	            	System.out.println("Invalid choice. Please try again.");
		      }
	    	} while (choice != 0);

	}

	private static void searchAuthor(Scanner br) {
		// TODO Auto-generated method stub
		System.out.println("Enter the Author name");
		String name = br.nextLine();
		BookService k = new BookServiceImpl();
		 try {
			k.searchBooksByAuthor(name).forEach(System.out::println);;
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	private static void searchBookGenre(Scanner br) {
		// TODO Auto-generated method stub
		System.out.println("Enter the Genre name");
		String name = br.nextLine();
		BookService k = new BookServiceImpl();
		 try {
			k.searchBooksByGenre(name).forEach(System.out::println);;
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(colorChange.ANSI_RED+e.getMessage()+colorChange.ANSI_RESET);
		}
	}

	private static void searchBookName(Scanner br) {
		// TODO Auto-generated method stub
		System.out.println("Enter the Title name");
		String name = br.nextLine();
		BookService k = new BookServiceImpl();
		 try {
			k.searchBooksByTitle(name).forEach(System.out::println);
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(colorChange.ANSI_RED+e.getMessage()+colorChange.ANSI_RESET);
		}
	}

	private static void viewAvailabile() {
		// TODO Auto-generated method stub
		 BookService k = new BookServiceImpl();
		 try {
			 List<Book> k1 = k.getAllBooks();
			 for (Book book : k1) {
				System.out.println(book.toString());
			}
		} catch (SomethingWentWrongException e) {
			// TODO Auto-generated catch block
			System.out.println(colorChange.ANSI_RED+e.getMessage()+colorChange.ANSI_RESET);
		}
		
	}

	//@SuppressWarnings("unchecked")
	public static void main(String[] args)  {
		 Scanner scanner = new Scanner(System.in);
	        boolean running = true;
	        while (running) {
	        	System.out.println(colorChange.ANSI_YELLOW+"=====================================================");
	            System.out.println("       Welcome to the Library Management System!👋👋👋"+colorChange.ANSI_RESET);
	            System.out.println("=> 1. Register for a student account");
	            System.out.println("=> 2. Log in to the student account");
	            System.out.println("=> 3. Exit");
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
	                    System.out.println(colorChange.ANSI_GREEN+"Thank you for using the Library Management System. Goodbye!🙌🙌🙌"+colorChange.ANSI_RESET);
	                    break;
	                default:
	                    System.out.println(colorChange.ANSI_RED+"Invalid choice. Please try again."+colorChange.ANSI_RESET);
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

	        Student newStudent = new Student(name, email, password,StudentStatus.ACTIVE);
	        StudentService ser = new StudentServiceImpl();
	        try {
				ser.addStudent(newStudent);
				System.out.println(colorChange.ANSI_GREEN+"Registered SucessfullY=================="+colorChange.ANSI_RESET);
			} catch (SomethingWentWrongException e) {
				// TODO Auto-generated catch block
				System.out.println(colorChange.ANSI_RED+e.getMessage()+colorChange.ANSI_RESET);
			}
	    }

	   
	}
	
    



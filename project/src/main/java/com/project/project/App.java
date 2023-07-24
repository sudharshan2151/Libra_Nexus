package com.project.project;

import java.util.List;
import java.util.Scanner;

import com.project.dao.FeedbackDAO;
import com.project.dao.FeedbackDAOImpl;
import com.project.entity.Feedback;
import com.project.exception.NoRecordFoundException;
import com.project.exception.SomethingWentWrongException;

/**
 * Hello world!
 *
 */

public class App 
{
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	//onstants here...

	 private static void printHorizontalLine(String color, int width) {
	        System.out.print(color);
	        for (int i = 0; i < width; i++) {
	            System.out.print(" ");
	        }
	        System.out.println(ANSI_RESET);
	    }

	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        int choice;

	        // Define the width of the console/terminal (adjust this value as needed)
	        int consoleWidth = 80;

	        // Print the header with appropriate alignment
	        printHorizontalLine(ANSI_YELLOW_BACKGROUND, consoleWidth);
	        System.out.print(ANSI_YELLOW_BACKGROUND + String.format("%" + ((consoleWidth - 103) / 2) + "s", ""));
	        System.out.print("💕💕💕💕💕💕" + "WELCOME TO LIBRANEXUS📚📚📚📚");
	        System.out.println(String.format("%" + ((consoleWidth - 135) / 2) + "s", "") + ANSI_RESET); // Reset color here
	        printHorizontalLine(ANSI_YELLOW_BACKGROUND, consoleWidth);

	        do {
	        	System.out.println("======================================");
	           // System.out.println("Enter your choice" );
	            System.out.println(ANSI_BLUE +"=> 1. Librarian");
	            System.out.println("=> 2. Student" );
	            System.out.println("=> 3. Exit"+ ANSI_RESET );
	            System.out.println("========================================");
	            choice = Integer.parseInt(sc.nextLine());

	            switch (choice) {
	                case 1:
	                    LibrarianConsole.main(args);
	                    break;
	                case 2:
	                    StudentConsole.main(args);
	                    break;
	                case 3:
	                    choice = 0;
	                    break;
	                default:
	                    System.out.println("Invalid Input======" + ANSI_RED_BACKGROUND);
	            }

	        } while (choice != 0);
	    }
	}

     
    
    



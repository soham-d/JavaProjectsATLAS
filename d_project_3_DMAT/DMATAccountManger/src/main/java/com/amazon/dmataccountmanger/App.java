package com.amazon.dmataccountmanger;

import com.amazon.dmataccountmanger.db.DB;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import com.amazon.dmataccountmanger.Menu;
import com.amazon.dmataccountmanger.controller.AuthenticationService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws NoSuchAlgorithmException
    {
    	
    	DB db = DB.getInstance();
    	AuthenticationService aService = AuthenticationService.getInstance();
    	
    	
    	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println( "Welcome BlazingBulls DMAT Account Application" );
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
        
    	
    	Menu menu = new Menu();
    	
    	try {
            if(args.length > 0) {
                DB.FILEPATH = args[0];
                }
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("exception caught");
		}
    	
    	
    	System.out.println();
    	System.out.println("1. Login");
    	System.out.println("2. Register");
    	System.out.println("Enter your choice...");
    	int userChoice = new Scanner(System.in).nextInt();

        if(userChoice == 1) {
        	menu.showMainMenu();
        }else if(userChoice == 2) {
        	boolean registerResult = aService.registerUser();
        	if(registerResult) {
        		System.out.println("Registration Successful...");
        		System.out.println();
        		menu.showMainMenu();
        	}else {
        		System.err.println("Registration failed. Please try again...");
        		menu.showMainMenu();
        	}

        }else {
        	System.err.println("Invalid Choice... Please Try Again...");
        }
        
        
        
        
    }
}

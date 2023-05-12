package com.amazon.buspassmanagement;

import java.util.List;

import com.amazon.buspassmanagement.db.DB;
import com.amazon.buspassmanagement.db.StopDAO;
import com.amazon.buspassmanagement.model.Stop;

public class App {
	
    public static void main( String[] args ) throws ArrayIndexOutOfBoundsException{
    	
    	try {
			

       
    	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println( "Welcome to Bus Pass Management Application" );
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
    	Menu menu = new Menu();
    	
    	try {
            if(args.length > 0) {
                DB.FILEPATH = args[0];
                }
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("exception caught");
		}

        
        menu.showMainMenu();
		
  
    	
	} catch (Exception e) {
		// TODO: handle exception
	}
    
}
}

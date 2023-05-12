package com.amazon.internalclassifieds;

import com.amazon.internalclassifieds.db.DB;

public class App {
	
    public static void main( String[] args ) throws ArrayIndexOutOfBoundsException{
    	
    	DB db = DB.getInstance();
    	
    	try {
			

       
    	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println( "Welcome to Amazon Internal Classifieds Application" );
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
		e.printStackTrace();
	}
    
}
}

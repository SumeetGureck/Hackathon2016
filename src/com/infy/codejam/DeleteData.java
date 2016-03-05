package com.infy.codejam;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class DeleteData {
	private void psvm() {
		// TODO Auto-generated method stub
		//To remove console warnings from mongo
 		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
	    mongoLogger.setLevel(Level.SEVERE); 
	    
	   //Connecting to Mongo DB
	  Mongo mongo = new Mongo("127.0.0.1", 27017);//Server URL & Port
		DB db = mongo.getDB("rtl"); //DB name
		
		// get a single collection
		DBCollection collection = db.getCollection("promo");
		
		 try{   
			 		
		          
		         
		    		BasicDBObject document = new BasicDBObject();
		    					    		
	

		    	
		 }
		 catch(Exception e){
		     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		     e.printStackTrace();
		  }
		 finally{
			 mongo.close();
		 }
		
		
		System.out.println("DONE");
	}

}

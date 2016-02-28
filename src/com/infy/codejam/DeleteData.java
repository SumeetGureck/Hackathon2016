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
		    					    		
		    		    	
		    	
		    		int sku1=2000200001;
		    		int sku2=2000300000;
		    		int promoNum=300000;
		    		int count=2000200001;
		    		
		    		while( sku1 <= sku2 ){
		    			
		    			count++;
		    		//SKU1	
		    	document.clear();
		    		document.put("pid", promoNum++);
		    		document.put("brand", 1);
		    		document.put("market", 1);
		    		document.put("div", 102);
		    		document.put("dept", "");
		    		document.put("style", 100003003);
		    		document.put("sku","" );
		    		document.put("color", 11);
		    		document.put("type", 1 );
		    		document.put("value", 10);
		    		document.put("zone", 1);
		    		document.put("loc", 1111);
		    		document.put("startDate", "1-Jan-2016");
		    		document.put("endDate", "1-Dec-2016");
		    		
		    	collection.insert(document);


		    	
		    	////
		    	
		    	
		    	
		    		}

		    	
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

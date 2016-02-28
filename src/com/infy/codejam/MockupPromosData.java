package com.infy.codejam;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.infy.utilities.ToLog;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;


public class MockupPromosData {
	  public static void main(String[] args) {
		 
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
			    	
			    		int sku1=2000720000;  //2000400501
			    		int sku2=2001010000;
			    		int style=204000000;
			    		
			    		int promoNum=7200240;
			    		int count=1;
			    		int end=10000;
			    		
			    		while( sku1 <= sku2 ){
			    			count++;
			    			
			    		//SKU1	
			    	document.clear();
			    		document.put("pid", promoNum++);
			    		document.put("brand", 1);
			    		document.put("market", 1);
			    		document.put("zone", 2);
			    		document.put("loc",1122);
			    		document.put("div", 101);
			    		document.put("dept", 1011);
			    		document.put("style", style++);
			    		document.put("sku","");
			    		document.put("color", 12);
			    		document.put("type", 2 );
			    		document.put("value", 10);
			    		document.put("startDate", "1-Jan-2015");
			    		document.put("endDate", "1-Dec-2016");
			    		
			    	collection.insert(document);
	
			    		}
			 }
			 catch(Exception e){
			     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			     e.printStackTrace();
			  }
			 finally{
				 mongo.close();
			 }
			
			 ToLog.logData("DONE");
			
		} 




}

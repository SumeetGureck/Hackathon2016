package com.infy.codejam;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;


public class MockupData {
	  public static void main(String[] args) {
		 
		//To remove console warnings from mongo
	 		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
  	    mongoLogger.setLevel(Level.SEVERE); 
  	    
  	   //Connecting to Mongo DB
  	  Mongo mongo = new Mongo("127.0.0.1", 27017);//Server URL & Port
  		DB db = mongo.getDB("rtl"); //DB name
  		
  		// get a single collection
  		DBCollection collection = db.getCollection("sku");
  		
			 try{   
				 		
			          
			         
			    		BasicDBObject document = new BasicDBObject();
			    					    		
			    		
			    		int brand=2;
			    		int market=1;
			    		int div=202;
			    		int dept=2022;
			    				    	
			    		 int style=100020000;
			    		int style2=100120000;
			    		int sku1=2000120000;
			    		int sku2=2000720000;
			    		
			    		
			    		while( style<style2 || sku1 <sku2 ){
			    			
			    			
			    		//SKU1	
			    	document.clear();
			    		document.put("brand", brand);
			    		document.put("market", market);
			    		document.put("div", div);
			    		document.put("dept", dept);
			    		document.put("style", style);
			    		document.put("sku", sku1++);
			    		document.put("color", 11);
			    		document.put("size", 1 );
			    		document.put("price", 100.00);
			    		document.put("promo", "0");
			    	collection.insert(document);
	
			    	//SKU2
			    	document.clear();
			    		document.put("brand", brand);
			    		document.put("market", market);
			    		document.put("div", div);
			    		document.put("dept", dept);
			    		document.put("style", style);
			    		document.put("sku", sku1++);
			    		document.put("color", 11);
			    		document.put("size", 2 );
			    		document.put("price", 200.00);
			    		document.put("promo", "0");
			    	collection.insert(document);
			    	//SKU3	
			    	document.clear();
			    		document.put("brand", brand);
			    		document.put("market", market);
			    		document.put("div", div);
			    		document.put("dept", dept);
			    		document.put("style", style);
			    		document.put("sku", sku1++);
			    		document.put("color", 11);
			    		document.put("size", 3 );
			    		document.put("price", 300.00);
			    		document.put("promo", "0");
			    	collection.insert(document);
			    	//SKU4	
			    	document.clear();
			    		document.put("brand", brand);
			    		document.put("market", market);
			    		document.put("div", div);
			    		document.put("dept", dept);
			    		document.put("style", style);
			    		document.put("sku", sku1++);
			    		document.put("color", 22);
			    		document.put("size", 1 );
			    		document.put("price", 100.00);
			    		document.put("promo", "0");
			    	collection.insert(document);
			    	//SKU5	
			    	document.clear();
			    		document.put("brand", brand);
			    		document.put("market", market);
			    		document.put("div", div);
			    		document.put("dept", dept);
			    		document.put("style", style);
			    		document.put("sku", sku1++);
			    		document.put("color", 22);
			    		document.put("size", 2 );
			    		document.put("price", 200.00);
			    		document.put("promo", "0");
			    	collection.insert(document);
			    	//SKU6	
			    	document.clear();
			    		document.put("brand", brand);
			    		document.put("market", market);
			    		document.put("div", div);
			    		document.put("dept", dept);
			    		document.put("style", style);
			    		document.put("sku", sku1++);
			    		document.put("color", 22);
			    		document.put("size", 3 );
			    		document.put("price", 300.00);
			    		document.put("promo", "0");
			    	collection.insert(document);
			  
			    	
			    	////
			    	style++;
			    	
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
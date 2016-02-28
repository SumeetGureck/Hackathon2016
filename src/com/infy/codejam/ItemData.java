package com.infy.codejam;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

public class ItemData {
	
	public Map<String,String> itemCached;
	
	public void cacheItems(){
		
		try{
			//To remove console warnings from mongo
	 		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		    mongoLogger.setLevel(Level.SEVERE); 
		    
		   //Connecting to Mongo DB
		    Mongo mongo = new Mongo("127.0.0.1", 27017);//Server URL & Port
			DB db = mongo.getDB("rtl"); //DB name
			
			// get a single collection
			DBCollection coll = db.getCollection("sku");
	        System.out.println("Starting caching of SKUs");
	     
	        BasicDBObject searchQuery = new BasicDBObject();
	    	//searchQuery.put("brand", brand);
	    	//DBCursor cursor = coll.find(searchQuery);
	        
	    	DBCursor cursor = coll.find();
	    
	    	
	    	 itemCached=new HashMap<String,String>();
	    	
	    	long d1=System.currentTimeMillis();
	      	BasicDBObject itemObj = (BasicDBObject) cursor.next();
	    	
	    	  while (cursor.hasNext()) { 
	    		 
	    		  //System.out.println("loop");
	    		  String tStr="";
	    		  itemObj = (BasicDBObject) cursor.next();
			       	Map m = new HashMap<>();
					m=itemObj.toMap();
					String skuTemp=m.get("sku").toString();
					String skuDetail=m.get("brand").toString()+m.get("market").toString()+m.get("div").toString()+m.get("dept").toString()+m.get("price").toString();
					
					itemCached.put(skuTemp, skuDetail);
					
					
	    	  }//end of while
	    	  
	    	  
	    	  long d2=System.currentTimeMillis();
	    	  mongo.close();
	    	  System.out.println("Caching of Items took : "+ (d2-d1) + " ms for "+itemCached.size()+" SKUs ");
	    	  System.out.println("------------------------------------------------------------------------ \n");
	    	  
		//	System.out.println(itemCached.get("2000005978"));
	    	  
	    	  mongo.close();

			}
			 catch(Exception e){
			     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			     e.printStackTrace();
			  }
			
			
		}
	public static void main(String args[]) {
		// TODO Auto-generated method stub
ItemData l=new ItemData();
l.cacheItems();

	}
}
                                                                     
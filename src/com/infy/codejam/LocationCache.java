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

public class LocationCache {
	Mongo mongo;
	public String[] locVal;
  	public String loc;
  	
  	public Map<String,String[]> locCached;
	public void cacheLocations(){
		
		
		try{
			//To remove console warnings from mongo
	 		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		    mongoLogger.setLevel(Level.SEVERE); 
		    
		   //Connecting to Mongo DB
		     mongo = new Mongo("127.0.0.1", 27017);//Server URL & Port
			DB db = mongo.getDB("rtl"); //DB name
			
			// get a single collection
			DBCollection coll = db.getCollection("loc");
	        System.out.println("Starting caching of Location Details");
	     
	        BasicDBObject searchQuery = new BasicDBObject();
	   
	    	DBCursor cursor = coll.find();
	    
	    	
	    	locCached=new HashMap<String, String[]>();
	    	
	    	long d1=System.currentTimeMillis();
	      	BasicDBObject locObj = (BasicDBObject) cursor.next();
	      	
	    	  while (cursor.hasNext()) { 
	    		 
	    		  //System.out.println("loop");
	    		
	    		  locObj = (BasicDBObject) cursor.next();
			       	Map m = new HashMap<>();
					m=locObj.toMap();
					loc=m.get("loc").toString();
					locVal=new String[3];
					locVal[0]=m.get("brand").toString();
					locVal[1]=m.get("market").toString();
					locVal[2]=m.get("zone").toString();
    				 locCached.put(loc, locVal);
					
					
	    	  }//end of while
	    	  long d2=System.currentTimeMillis();
	    	  mongo.close();
	    	  System.out.println("Caching of Locations took : "+ (d2-d1) + " ms ");
	    	  System.out.println("------------------------------------------------------------------------");
			}
			 catch(Exception e){
				 
			     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			     e.printStackTrace();
			  }
			finally{
				
			}
			
		}
	
/*	public static void main(String args[]) {
		// TODO Auto-generated method stub
LocationCache l=new LocationCache();
l.cacheLocations();
	}*/
}
                                                                     
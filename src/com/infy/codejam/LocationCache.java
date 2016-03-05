package com.infy.codejam;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.infy.utilities.ToLog;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

public class LocationCache {
	static Mongo mongo;

	static String[] locVal;
	static String loc;
	
	// Map<String, String[]> locCached;
	static  Map<String, String[]> locCached = new HashMap<String, String[]>();
	 
	
	protected LocationCache() {
	cacheLocations();
	}
 

	public static Map<String, String[]> getInstance() {
		if(locCached.size()<1)
			cacheLocations();
		return locCached;
	}
	
	public static void destroyLocationCache() {
		locCached.clear();
		}
	 

	/**
	* The cacheLocations function will load all locations from Mongo 
	* Collection and hold the required Location hierarchy related data 
	* in a HashMap.
	* 
	* Map<String, String[]> : <Location/Store , {Brand,Market,Zone}>
	*
	* @author  Retail Wiz
	* @version 1.0
	* @since   2016-02-27 
	*/
	public static void cacheLocations() {
		
		
		
		try {
			// To remove console warnings from mongo
			Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
			mongoLogger.setLevel(Level.SEVERE);

			// Connecting to Mongo DB
			mongo = new Mongo("127.0.0.1", 27017);// Server URL & Port
			DB db = mongo.getDB("rtl"); // DB name

			// get a single collection
			DBCollection coll = db.getCollection("loc");
			ToLog.logData("Starting caching of Location Details");

			BasicDBObject searchQuery = new BasicDBObject();

			DBCursor cursor = coll.find();

			

			long d1 = System.currentTimeMillis();
			BasicDBObject locObj;

			while (cursor.hasNext()) {

				// System.out.println("loop");

				locObj = (BasicDBObject) cursor.next();
				Map m = new HashMap<>();
				m = locObj.toMap();
				loc = m.get("loc").toString();
				locVal = new String[3];
				locVal[0] = m.get("brand").toString();
				locVal[1] = m.get("market").toString();
				locVal[2] = m.get("zone").toString();
				locCached.put(loc, locVal);
				
			}
			long d2 = System.currentTimeMillis();
			mongo.close();
			ToLog.logData("Caching of Locations took : " + (d2 - d1) + " ms ");
			ToLog.logData("------------------------------------------------------------------------");
		} catch (Exception e) {

			ToLog.logData(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
		} finally {

		}

	}

	/*
	 * public static void main(String args[]) { LocationCache l=new
	 * LocationCache(); l.cacheLocations(); }
	 */
}

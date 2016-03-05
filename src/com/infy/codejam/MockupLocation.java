package com.infy.codejam;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.infy.utilities.ToLog;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class MockupLocation {
	public static void main(String[] args) {

		// To remove console warnings from mongo
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		// Connecting to Mongo DB
		Mongo mongo = new Mongo("127.0.0.1", 27017);// Server URL & Port
		DB db = mongo.getDB("rtl"); // DB name

		// get a single collection
		DBCollection collection = db.getCollection("loc");

		try {

			BasicDBObject document = new BasicDBObject();
			
/*
			Brand	Market	Zone	loc
			1	1	1	1111
			1	1	1	1112
			1	1	2	1121
			1	1	2	1122
			1	2	1	1211
			1	2	1	1212
			1	2	2	1221
			1	2	2	1222
						
			2	1	1	2111
			2	1	1	2112
			2	1	2	2121
			2	1	2	2122
			2	2	1	2211
			2	2	1	2212
			2	2	2	2221
			2	2	2	2222
*/

			
			for(int b=1;b<3;b++){
				for(int m=1;m<3;m++){
					for(int z=1;z<3;z++){
						for(int l=1;l<3;l++){
							document.clear();

							document.put("brand", b);
							document.put("market", m);
							document.put("zone", z);
							document.put("loc", Integer.parseInt(""+b+m+z+l));
							
							collection.insert(document);

						}
					}
				}
				
				
			}
				
		

			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
		} finally {
			mongo.close();
		}

		ToLog.logData("DONE");

	}

}

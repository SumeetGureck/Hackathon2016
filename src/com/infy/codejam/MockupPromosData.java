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

		// To remove console warnings from mongo
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		// Connecting to Mongo DB
		Mongo mongo = new Mongo("127.0.0.1", 27017);// Server URL & Port
		DB db = mongo.getDB("rtl"); // DB name

		// get a single collection
		DBCollection collection = db.getCollection("promo");

		try {

			BasicDBObject document = new BasicDBObject();
/*				
100	102	1	1	1	1111	101	1011	11	1	20
102	104	1	1	1	1111	101	1011	11	2	10
104	107	1	1	1	1111	101	1012	22	1	15
108	110	1	1	1	1111	101	1012	11	2	10
110	115	1	1	1	1111	102	1021	11	1	30
115	119	1	1	1	1111	102	1021	22	2	12
*/

			int promoNum1 = 100;
			int promoNum2=102;
			int div=101;
			int dept=1011;
			int type=1;
			int value=20;
			
			int brand=1;
			int market=1;
			int zone = 1;
			int loc = 1111;
			
			
			int sku1 = 2000000011; 
			int sku2 = 2000006000;
			
			while (promoNum1 <= promoNum2) {
				document.clear();
				
				document.put("pid", promoNum1++);
				document.put("brand", brand);
				document.put("market", market);
				document.put("zone", zone);
				document.put("loc", loc);
				document.put("div", div);
				document.put("dept", dept);
				document.put("sku", "");
				document.put("color", 11);
				document.put("type", type);
				document.put("value", value);
				document.put("startDate", "1-Jan-2015");
				document.put("endDate", "1-Dec-2016");

				collection.insert(document);

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

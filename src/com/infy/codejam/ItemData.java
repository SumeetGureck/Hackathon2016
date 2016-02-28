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

public class ItemData {

	public Map<String, String> itemCached;

	public void cacheItems() {

		try {
			// To remove console warnings from mongo
			Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
			mongoLogger.setLevel(Level.SEVERE);

			// Connecting to Mongo DB
			Mongo mongo = new Mongo("127.0.0.1", 27017);// Server URL & Port
			DB db = mongo.getDB("rtl"); // DB name

			// get a single collection
			DBCollection coll = db.getCollection("sku");
			ToLog.logData("Starting caching of SKUs");

			BasicDBObject searchQuery = new BasicDBObject();

			DBCursor cursor = coll.find();

			itemCached = new HashMap<String, String>();

			long d1 = System.currentTimeMillis();
			BasicDBObject itemObj = (BasicDBObject) cursor.next();

			while (cursor.hasNext()) {

				String tStr = "";
				itemObj = (BasicDBObject) cursor.next();
				Map m = new HashMap<>();
				m = itemObj.toMap();
				String skuTemp = m.get("sku").toString();
				String skuDetail = m.get("brand").toString()
						+ m.get("market").toString() + m.get("div").toString()
						+ m.get("dept").toString() + m.get("price").toString();

				itemCached.put(skuTemp, skuDetail);

			}

			long d2 = System.currentTimeMillis();
			mongo.close();
			ToLog.logData("Caching of Items took : " + (d2 - d1) + " ms for "
					+ itemCached.size() + " SKUs ");
			ToLog.logData("------------------------------------------------------------------------ \n");

			mongo.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
		}

	}
	/*
	 * public static void main(String args[]) { ItemData l=new ItemData();
	 * l.cacheItems();
	 * 
	 * }
	 */
}

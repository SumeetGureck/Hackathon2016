package com.infy.codejam;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.infy.utilities.ToLog;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

public class Promotion {

	public Map<String, HashSet<String>> promoCached;

	public void cachePromos() {

		try {
			// To remove console warnings from mongo
			Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
			mongoLogger.setLevel(Level.SEVERE);

			// Connecting to Mongo DB
			Mongo mongo = new Mongo("127.0.0.1", 27017);// Server URL & Port
			DB db = mongo.getDB("rtl"); // DB name

			// get a single collection
			DBCollection coll = db.getCollection("promo");
			System.out.println("Starting caching of Promotions");

			BasicDBObject searchQuery = new BasicDBObject();
			int brand = 1;
			searchQuery.put("brand", brand);

			DBCursor cursor = coll.find(searchQuery);

			promoCached = new HashMap<String, HashSet<String>>();

			long d1 = System.currentTimeMillis();
			BasicDBObject promoObj = (BasicDBObject) cursor.next();
			int promoDBSize = cursor.size();

			while (cursor.hasNext()) {

				// System.out.println("loop");
				String tStr = "";
				promoObj = (BasicDBObject) cursor.next();
				Map m = new HashMap<>();
				m = promoObj.toMap();
				Set<String> tmp;

				String b = "", mkt = "", z = "", l = "", div = "", dept = "";

				if (m.containsKey("brand"))
					b = m.get("brand").toString();

				if (m.containsKey("market"))
					mkt = m.get("market").toString();

				if (m.containsKey("zone"))
					z = m.get("zone").toString();

				if (m.containsKey("loc"))
					l = m.get("loc").toString();

				if (m.containsKey("div"))
					div = m.get("div").toString();

				if (m.containsKey("dept"))
					dept = m.get("dept").toString();

				String promoLevelkey = b + mkt + z + l + div + dept;

				if (!promoCached.containsKey(promoLevelkey)) {

					tmp = new HashSet<String>();
					tmp.add(m.get("type").toString()
							+ m.get("value").toString());
					promoCached.put(promoLevelkey, (HashSet<String>) tmp);

					continue;
				}

				tmp = new HashSet<String>();
				tmp = promoCached.get(promoLevelkey);
				tmp.add(m.get("type").toString() + m.get("value").toString());
				promoCached.put(promoLevelkey, (HashSet<String>) tmp);

			}// end of while

			long d2 = System.currentTimeMillis();

			ToLog.logData("Caching of Promos took : " + (d2 - d1) + " ms ");
			ToLog.logData("------------------------------------------------------------------------ \n");

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * public static void main(String args[]){ Promotion p=new Promotion();
	 * p.cachePromos(); }
	 */

}

/*
 * class Node { public List<Node> children = null; public String value; public
 * int amountoff; public int percentOff;
 * 
 * public Node(String value) {
 * 
 * }
 * 
 * public void addChild(Node child) {
 * 
 * }
 * 
 * }
 */
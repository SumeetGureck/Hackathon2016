package com.infy.codejam;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.infy.utilities.ToLog;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class MockupData {
	public static void main(String[] args) {

		// To remove console warnings from mongo
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		// Connecting to Mongo DB
		Mongo mongo = new Mongo("127.0.0.1", 27017);// Server URL & Port
		DB db = mongo.getDB("rtl"); // DB name

		// get a single collection
		DBCollection collection = db.getCollection("sku");

		try {

			BasicDBObject document = new BasicDBObject();
/*			
		Brand	Market	Div	Dept	Sty1	Sty2	SKu1	SKu2
1	1	101	1011	100000000	100001000	2000000000	2000006000
1	1	101	1012	100001000	100002000	2000006000	2000012000
1	1	102	1021	100002000	100003000	2000012000	2000018000
1	1	102	1022	100003000	100004000	2000018000	2000024000
1	2	101	1011	100004000	100005000	2000024000	2000030000
1	2	101	1012	100005000	100006000	2000030000	2000036000
1	2	102	1021	100006000	100007000	2000036000	2000042000
1	2	102	1022	100007000	100008000	2000042000	2000048000
							
2	1	201	2011	100009000	100010000	2000054000	2000060000
2	1	202	2021	100010000	100011000	2000060000	2000066000
2	2	201	2011	100011000	100012000	2000066000	2000072000
2	2	202	2021	100012000	100020000	2000072000	2000120000
2	2	202	2022	100020000	100120000	2000120000	2000720000

*/	
			int brand = 2;
			int market = 2;
			int div = 202;
			int dept = 2022;

			 int style = 100020000;
			int style2 = 100120000;
			int sku1 = 2000120000;
			int sku2 = 2000720000;

			while (style < style2 ) {

				// SKU1
				document.clear();
				document.put("brand", brand);
				document.put("market", market);
				document.put("div", div);
				document.put("dept", dept);
				document.put("style", style);
				document.put("sku", sku1++);
				document.put("color", 11);
				document.put("size", 1);
				document.put("price", 100.00);
				collection.insert(document);

				// SKU2
				document.clear();
				document.put("brand", brand);
				document.put("market", market);
				document.put("div", div);
				document.put("dept", dept);
				document.put("style", style);
				document.put("sku", sku1++);
				document.put("color", 11);
				document.put("size", 2);
				document.put("price", 200.00);
				collection.insert(document);
				// SKU3
				document.clear();
				document.put("brand", brand);
				document.put("market", market);
				document.put("div", div);
				document.put("dept", dept);
				document.put("style", style);
				document.put("sku", sku1++);
				document.put("color", 11);
				document.put("size", 3);
				document.put("price", 300.00);
				collection.insert(document);
				// SKU4
				document.clear();
				document.put("brand", brand);
				document.put("market", market);
				document.put("div", div);
				document.put("dept", dept);
				document.put("style", style);
				document.put("sku", sku1++);
				document.put("color", 22);
				document.put("size", 1);
				document.put("price", 100.00);
				collection.insert(document);
				// SKU5
				document.clear();
				document.put("brand", brand);
				document.put("market", market);
				document.put("div", div);
				document.put("dept", dept);
				document.put("style", style);
				document.put("sku", sku1++);
				document.put("color", 22);
				document.put("size", 2);
				document.put("price", 200.00);
				collection.insert(document);
				// SKU6
				document.clear();
				document.put("brand", brand);
				document.put("market", market);
				document.put("div", div);
				document.put("dept", dept);
				document.put("style", style);
				document.put("sku", sku1++);
				document.put("color", 22);
				document.put("size", 3);
				document.put("price", 300.00);
				collection.insert(document);

				style++;

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

/**
 * 
 */
package com.infy.TestPromoEngine;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

import com.infy.codejam.ItemData;
import com.infy.codejam.LocationCache;
import com.infy.codejam.Promotion;
import com.infy.utilities.ToLog;

/**
* The Basket1 function will hold the input data to test promotion
* function and call caching functions before applying promos.
* 
*
* @author  Retail Wiz
* @version 1.0
* @since   2016-02-27 
*/
public class Basket1 {

	@Test
	public void test() {
		// Basket List to drive test
		Map<String, String> basket = new HashMap<String, String>();
		Map<String, String> bestPrice = new HashMap<String, String>();
		bestPrice = basket;

		// Load SKUs
		basket.put("2000000010", "1111");
		basket.put("2000000020", "1111");
		basket.put("2000000021", "1211");
		basket.put("2000012000", "1112");
		basket.put("2000072000", "2111");
		basket.put("2000006105", "1112");
		basket.put("2000042000", "1221");
		basket.put("2000012020", "2111");
		basket.put("2000012030", "1122");
		basket.put("2000012220", "1112");
		basket.put("2000000022", "1111");
		basket.put("2000120350", "2111");
		basket.put("2000120120", "2121");
		basket.put("2000006100", "1111");
		basket.put("2999000611", "1112");

		
	try{
		// Cache SKU
		Map<String, String> itmCache = ItemData.getInstance();
		//itmCache.cacheItems();

		// Cache Promos
		Map<String, HashSet<String>> promoCache = Promotion.getInstance();
		//promoCache.cachePromos();

		// Cache Location
		Map<String, String[]>  locCache = LocationCache.getInstance();
		//locCache.cacheLocations();
		
		// **Start Tracking Execution Time Post Caching
		long startTime = System.currentTimeMillis();

		// Form SKU Hierarchy
		Set<Entry<String, String>> entries = basket.entrySet();

		// Traversing the Items in Basket
		
		Iterator<Entry<String, String>> it = entries.iterator();

		long endTime = 0;

		while (it.hasNext()) {
			Entry<String, String> e = it.next();
			String skuNumber = e.getKey();
			ToLog.logData("\n\t SKU : " + skuNumber);
			
			String skuLocationBasket = e.getValue();
			
			String skuDetailsFromCache;

			// Get zone details for SKU
			String[] locDetails = locCache.get(skuLocationBasket);
						
			if (itmCache.containsKey(skuNumber)) {
				skuDetailsFromCache = itmCache.get(skuNumber);
			} else {

				ToLog.logData("\t SKU does not exist in Master Item");
				continue;

			}

			BigDecimal mrp = new BigDecimal(skuDetailsFromCache.substring(9,
					skuDetailsFromCache.length()));

			// Create SKU hierarchy
			// Add Brand & Market details
			StringBuffer skuHierarchy = new StringBuffer(skuDetailsFromCache.substring(0, 2));
			// Append Zone detail
			skuHierarchy.append(locDetails[2]);
			// Append Location
			skuHierarchy.append(skuLocationBasket);
			// Append division & department
			skuHierarchy.append(skuDetailsFromCache.substring(2, 9));

			
			// ****** Get All eligible promoDetails *************
			Set promoSet = new HashSet<String>();

			// Get All eligible Promos for SKU only
			if (skuHierarchy.length() > 14
					&& promoCache.containsKey(skuHierarchy
							+ skuNumber)) {
				promoSet = promoCache.get(skuHierarchy + skuNumber);

			}

			// Get All eligible Promos at DEPT level
			if (skuHierarchy.length() > 10
					&& promoCache.containsKey(skuHierarchy)) {
				promoSet.addAll(promoCache.get(skuHierarchy));

			}

			// Get All eligible Promos at DIVISION level
			if (skuHierarchy.length() > 7
					&& promoCache.containsKey(skuHierarchy
							.substring(0, 10))) {

				promoSet.addAll(promoCache.get(skuHierarchy.substring(0, 10)));

			}

			// Get All eligible Promos at Location level
			if (skuHierarchy.length() > 3
					&& promoCache.containsKey(skuHierarchy
							.substring(0, 7))) {

				promoSet.addAll(promoCache.get(skuHierarchy.substring(0, 7)));

			}

			// Get All eligible Promos at Zone level
			if (skuHierarchy.length() > 2
					&& promoCache.containsKey(skuHierarchy
							.substring(0, 3)))
				promoSet.addAll(promoCache.get(skuHierarchy
						.substring(0, 3)));

			BigDecimal finalSellingPrice = mrp;
			Iterator<String> itr2 = promoSet.iterator();
			while (itr2.hasNext()) {
				String promoTemp = itr2.next().toString();
				int promoType = Integer.parseInt(promoTemp.substring(0, 1));

				BigDecimal promoValue = new BigDecimal(promoTemp.substring(1,
						promoTemp.length()));

				// For currency calculation
				ToLog.logData("\t\t Type : " + promoType + " , Value : "
						+ promoValue);

				// Amount Off Promo Calculations
				if (promoType == 1) {
					if (mrp.subtract(promoValue).compareTo(finalSellingPrice) == -1) {
						finalSellingPrice = mrp.subtract(promoValue);
					}

				}

				// Percentage Off Promo Calculations
				else if (promoType == 2) {

					if (mrp.subtract(
							((promoValue.divide(new BigDecimal(100.0)))
									.multiply(mrp))).compareTo(
							finalSellingPrice) == -1) {
						finalSellingPrice = mrp.subtract(((promoValue
								.divide(new BigDecimal(100.0))).multiply(mrp)));
					}
				}

			}
			ToLog.logData("\t Best Price for SKU : " + skuNumber + " is = "
					+ finalSellingPrice + " INR, Initial Price was : " + mrp
					+ " INR\n");

			endTime = System.currentTimeMillis();

		}// end of While for single SKU promotion calc
		ToLog.logData("------------------------------------------------------------------------ \n");

		ToLog.logData("BEST PROMO IDENTIFIED FOR " + basket.size()
				+ " SKUs in " + (endTime - startTime) + "ms");
		
}
	catch(Exception e){
		System.out.println("ERROR: "+e.getMessage());

	}
	finally{
		ItemData.destroyItemCache();
		Promotion.destroyPromoCache();
		LocationCache.destroyLocationCache();
	}

	}

}

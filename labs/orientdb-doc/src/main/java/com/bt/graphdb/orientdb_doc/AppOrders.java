package com.bt.graphdb.orientdb_doc;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;

/**
 * Hello world!
 *
 */
public class AppOrders {
	public static void main(String[] args) {
		// OPEN THE DATABASE
		ODatabaseDocumentTx db = null;
		try {
			db = new ODatabaseDocumentTx("remote:localhost/northwinddocdb").open("root", "root");

			// CREATE A NEW DOCUMENT AND FILL IT
			ODocument itemDoc1 = new ODocument("item");
			itemDoc1.fields("sno", 1, "quantity", 100, "price", 10000F, "is", getProductId(db, "MOB0001"));
			itemDoc1.save();
			
			ODocument itemDoc2 = new ODocument("item");
			itemDoc2.fields("sno", 2, "quantity", 200, "price", 20000F, "is", getProductId(db, "MOB0002"));
			itemDoc2.save();
			
			Set<ORID> lineItems = new TreeSet<ORID>();
			lineItems.add(itemDoc1.getIdentity());
			lineItems.add(itemDoc2.getIdentity());
			
			ODocument orderDoc = new ODocument("order");
			orderDoc.fields("orderNo","ORD0001", "orderDate", new Date(), "lineitems", lineItems);
			orderDoc.save();
			
		} catch (Exception e) {

		} finally {
			if (db != null) {
				db.close();
			}
		}
	}

	private static ORID getProductId(ODatabaseDocumentTx db, String prodId) {
		OSQLSynchQuery<ODocument> osqlSynchQuery = new OSQLSynchQuery<ODocument>("select * from product where id=:prodId ");
		Map<String,Object> params = new HashMap();
		params.put("prodId", prodId);
		
		List<ODocument> products = db.command(osqlSynchQuery).execute(params);
		
		for (ODocument prod : products) {
			return  prod.getIdentity();
		}
		return null;
	}
}

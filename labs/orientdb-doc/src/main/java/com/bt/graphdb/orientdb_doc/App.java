package com.bt.graphdb.orientdb_doc;

import com.orientechnologies.orient.core.db.ODatabaseFactory;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.record.impl.ODocument;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		// OPEN THE DATABASE
		ODatabaseDocumentTx db = null;
		try {
			db = new ODatabaseDocumentTx("remote:localhost/northwinddocdb").open("root", "root");

			// CREATE A NEW DOCUMENT AND FILL IT
//			ODocument categoryDoc = new ODocument("category");
////			ODocument categoryDoc = new ODocument(new ORecordId("#16:0"));
//			categoryDoc.field("id", "mobile");
//			categoryDoc.field("name", "mobile Phone");
////			
////			// SAVE THE DOCUMENT
//			categoryDoc = categoryDoc.save();
			
			
			ODocument prodDoc = new ODocument("product");
			prodDoc.fields("id","MOB0002", "name", "SamsungGalaxy", 
					"price", 22250F, "inCategory", new ORecordId("#16:1"));
			prodDoc.save();

			ODocument prodDoc2 = new ODocument("product");
			prodDoc2.fields("id","MOB0001", "name", "SamsungGalaxy", 
					"price", 2250F, "inCategory", new ORecordId("#16:0"));
			prodDoc2.save();

		} catch (Exception e) {

		} finally {
			if (db != null) {
				db.close();
			}
		}
	}
}

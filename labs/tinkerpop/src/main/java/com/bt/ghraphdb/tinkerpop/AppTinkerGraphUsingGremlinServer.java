package com.bt.ghraphdb.tinkerpop;

import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.driver.ResultSet;

/**
 * Hello world!
 *
 */
public class AppTinkerGraphUsingGremlinServer {
	public static void main(String[] args) throws Exception {
		Cluster cluster = Cluster.open("remote.yaml");
		Client client = cluster.connect();
		ResultSet results = client.submit("graph");
		
		//check the results
		
		client.close();
		cluster.close();
	}
}

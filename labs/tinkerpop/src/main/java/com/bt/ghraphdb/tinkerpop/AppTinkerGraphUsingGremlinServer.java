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
		ResultSet results = client.submit("[1,2,3,4]");
		System.out.println(results.stream().map(i -> i.get(Integer.class) * 2));
		
		client.close();
		cluster.close();
	}
}

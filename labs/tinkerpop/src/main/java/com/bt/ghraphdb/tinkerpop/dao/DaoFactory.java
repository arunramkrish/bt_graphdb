package com.bt.ghraphdb.tinkerpop.dao;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;

import com.bt.ghraphdb.tinkerpop.dao.tinkerpop.TpOrderDao;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class DaoFactory {
	private OrderDao tpOrderDao;
	private OrderDao orientOrderDao;
	
	private Graph graph;
	private ODatabaseDocumentTx odb;
	
	public void init() {
		Configuration configuration = new PropertiesConfiguration();
		configuration.addProperty(TinkerGraph.GREMLIN_TINKERGRAPH_GRAPH_LOCATION, "E:/northwind.kryo");
		configuration.addProperty(TinkerGraph.GREMLIN_TINKERGRAPH_GRAPH_FORMAT, "gryo");//"graphson");// "graphml");
		graph = TinkerGraph.open(configuration);
		tpOrderDao = new TpOrderDao();
		((TpOrderDao)tpOrderDao).setGraph(graph);
	
		odb = new ODatabaseDocumentTx("remote:localhost/petshop").open("root", "root");
		odb.set
	}

	public void shutdown() throws Exception {
		if (graph != null) {
			graph.close();
		}
		
		if (odb != null) {
			odb.close();
		}
	}
	
	public OrderDao getOrderDao() {
		return tpOrderDao;
	}
}

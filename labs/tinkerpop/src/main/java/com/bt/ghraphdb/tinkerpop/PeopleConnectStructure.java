package com.bt.ghraphdb.tinkerpop;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.T;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.apache.tinkerpop.gremlin.util.iterator.IteratorUtils;

public class PeopleConnectStructure {

	public static void main(String[] args) throws Exception {
		Configuration configuration = new PropertiesConfiguration();
		configuration.addProperty(TinkerGraph.GREMLIN_TINKERGRAPH_GRAPH_LOCATION, "db/peopledb.json");
		configuration.addProperty(TinkerGraph.GREMLIN_TINKERGRAPH_GRAPH_FORMAT, "graphson");// "graphml");

		Graph graph = TinkerGraph.open(configuration);
		long nodeCount = IteratorUtils.count(graph.vertices()) + IteratorUtils.count(graph.edges());

		int count = 12;
		Vertex[] vertices = new Vertex[12];
		for (int i = 0; i < count; i++) {
			Vertex v = graph.addVertex(T.label, "person", T.id, nodeCount++, "name", "Person " + i);
			vertices[i] = v;
		}

		String[] circles = { "Friend", "Relative", "Coworker" };
		vertices[0].addEdge(circles[0], vertices[0+3]);
		vertices[1].addEdge(circles[1], vertices[1+3]);
		vertices[2].addEdge(circles[2], vertices[2+3]);
		
		vertices[0 + 3].addEdge(circles[0], vertices[0+(3*2)]);
		vertices[1 + 3].addEdge(circles[1], vertices[1+(3*2)]);
		vertices[2 + 3].addEdge(circles[2], vertices[2+(3*2)]);

		vertices[0 + (3 * 2)].addEdge(circles[0], vertices[0+(3*3)]);
		vertices[1 + (3 * 2)].addEdge(circles[1], vertices[1+(3*3)]);
		vertices[2 + (3 *2)].addEdge(circles[2], vertices[2+(3*3)]);

//		for (int i = 0; i < (count / 3 - 1); i++) {
//			for (int j = 0; j < 3; j++) {
//				vertices[j + (i * 3)].addEdge(circles[j], vertices[(i + 1) * 3]);
//			}
//		}

		System.out.println(IteratorUtils.count(graph.vertices()));
		System.out.println(IteratorUtils.count(graph.edges()));

		graph.close();
	}

}

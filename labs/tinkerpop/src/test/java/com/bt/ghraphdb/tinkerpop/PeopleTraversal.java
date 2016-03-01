package com.bt.ghraphdb.tinkerpop;

import static org.junit.Assert.*;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.apache.tinkerpop.gremlin.process.traversal.Traverser;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.process.traversal.util.TraversalHelper;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PeopleTraversal {
	static Graph graph;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Configuration configuration = new PropertiesConfiguration();
		configuration.addProperty(TinkerGraph.GREMLIN_TINKERGRAPH_GRAPH_LOCATION, "db/peopledb.json");
		configuration.addProperty(TinkerGraph.GREMLIN_TINKERGRAPH_GRAPH_FORMAT, "graphson");// "graphml");

		graph = TinkerGraph.open(configuration);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		graph.close();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		System.out.println(graph.vertices(0).next().property("name"));
		
		GraphTraversalSource travSource = graph.traversal(GraphTraversalSource.standard());

		GraphTraversal<Vertex, Vertex> traversal = travSource.V().has("name", P.eq("Person 0")).out("Friend");
		traversal.sideEffect(it -> 
			System.out.println("Inside side effect 1 " + ((Vertex) it.get()).property("name")));
		traversal.out("Friend")
				.sideEffect(it -> System.out.println("Inside side effect 2" + ((Vertex) it.get()).property("name")));

		while (traversal.hasNext()) {
			System.out.println("Printing");
			Vertex v = traversal.next();
			Object current = v.property("name").value();
			// v.propert
			System.out.println(current);

			// v.property("name", "Modified" + current);
		}

		GraphTraversal<Vertex, Vertex> traversal2 = travSource.V();
		traversal2.sideEffect(it -> 
			System.out.println("Inside side effect 1 " + ((Vertex) it.get()).property("name")));
		GraphTraversal<Vertex, Long> trav3 = traversal2.count();

		while (trav3.hasNext()) {
			System.out.println("Printing count");
			System.out.println(trav3.next());

			// v.property("name", "Modified" + current);
		}
}
}

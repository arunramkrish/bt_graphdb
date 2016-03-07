package com.bt.graphdb.traversal;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.T;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;

public class App {
	private static final String FORMAT = "gryo";
	private static final String LOCATION = "E:/Training/BritishTelecom/bt_graphdb/feb2016/northwind/northwind.kryo";
	// "graphson");// "graphml");

	public static void main(String[] args) {
		Graph graph = loadGraph();

		GraphTraversalSource traverser = graph.traversal();

		GraphTraversal<Vertex, Vertex> t = traverser.V().hasLabel("customer").out("ordered").out("contains").out("is");
		
		while(t.hasNext()) {
			Vertex resultVertex = t.next();
			Iterator values = resultVertex.values();
			while (values.hasNext()) {
				System.out.print("Aggregate:" + values.next() + "---");
			}
			System.out.println();
		}
		
		// traverse both sides
//		traverseOrdersAndRegions(traverser);

		try {
			graph.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void traverseOrdersAndRegions(GraphTraversalSource traverser) {
		GraphTraversal<Vertex, Vertex> t = traverser.V().hasLabel("customer")
				.sideEffect(it -> System.out.println("side effect " + it.get().value("name")))
				.out("ordered", "livesInRegion").as("a");

		while (t.hasNext()) {
			Vertex resultVertex = t.next();
			Iterator values = resultVertex.values();
			while (values.hasNext()) {
				System.out.print(resultVertex.label() + ":" + values.next() + "---");
			}
			System.out.println();
			// System.out.println(product.value("name").toString() +
			// product.value("units").toString());
		}
	}

	private static Graph loadGraph() {
		Configuration configuration = new PropertiesConfiguration();
		configuration.addProperty(TinkerGraph.GREMLIN_TINKERGRAPH_GRAPH_LOCATION, LOCATION);
		configuration.addProperty(TinkerGraph.GREMLIN_TINKERGRAPH_GRAPH_FORMAT, FORMAT);

		Graph graph = TinkerGraph.open(configuration);

		return graph;
	}

	static class Palindrome implements Predicate<String> {

		@Override
		public boolean test(String t) {
			return t.equals(StringUtils.reverse(t));
		}

	}

	static class MyConsumer implements Consumer<String> {

		@Override
		public void accept(String t) {
			// TODO Auto-generated method stub

		}

	}
}

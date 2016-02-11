package com.bt.ghraphdb.tinkerpop;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.T;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.apache.tinkerpop.gremlin.util.iterator.IteratorUtils;

/**
 * Hello world!
 *
 */
public class AppTinkerGraph 
{
    public static void main( String[] args ) throws Exception
    {
        System.out.println( "Hello World!" );
//        Graph graph = TinkerGraph.open();
        Configuration configuration = new PropertiesConfiguration();
        configuration.addProperty(TinkerGraph.GREMLIN_TINKERGRAPH_GRAPH_LOCATION, "db/demodb.json");
        configuration.addProperty(TinkerGraph.GREMLIN_TINKERGRAPH_GRAPH_FORMAT, "graphson");//"graphml");
        
        Graph graph = TinkerGraph.open(configuration);
        System.out.println(IteratorUtils.count(graph.vertices()));
        
        long nodeCount = IteratorUtils.count(graph.vertices()) + IteratorUtils.count(graph.edges());
        
        Vertex marko = graph.addVertex(T.label, "person", T.id, nodeCount+ 1, "name", "marko", "age", 29);
        Vertex vadas = graph.addVertex(T.label, "person", T.id, nodeCount+ 2, "name", "vadas", "age", 27);
        Vertex lop = graph.addVertex(T.label, "software", T.id, nodeCount+ 3, "name", "lop", "lang", "java");
        Vertex josh = graph.addVertex(T.label, "person", T.id, nodeCount+ 4, "name", "josh", "age", 32);
        Vertex ripple = graph.addVertex(T.label, "software", T.id, nodeCount+ 5, "name", "ripple", "lang", "java");
        Vertex peter = graph.addVertex(T.label, "person", T.id, nodeCount+  6, "name", "peter", "age", 35);
        
        marko.addEdge("knows", vadas, T.id, nodeCount + 7, "weight", 0.5f); 
        marko.addEdge("knows", josh, T.id, nodeCount + 8, "weight", 1.0f);
        marko.addEdge("created", lop, T.id, nodeCount + 9, "weight", 0.4f);
        josh.addEdge("created", ripple, T.id, nodeCount + 10, "weight", 1.0f);
        josh.addEdge("created", lop, T.id, nodeCount + 11, "weight", 0.4f);
        peter.addEdge("created", lop, T.id, nodeCount + 12, "weight", 0.2f);

        System.out.println(IteratorUtils.count(graph.vertices()));
        
        graph.close();
    }
}

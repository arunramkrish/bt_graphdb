package com.bt.ghraphdb.tinkerpop;

//import org.apache.tinkerpop.gremlin.orientdb.OrientGraphFactory;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.T;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.util.iterator.IteratorUtils;

/**
 * Hello world!
 *
 */
public class AppOrientDb 
{
	/*
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        OrientGraphFactory ogf = new  OrientGraphFactory("remote:localhost/GratefulDeadConcerts", "root", "root");
        Graph graph = ogf.getNoTx(false, true);
        Vertex marko = graph.addVertex(T.label, "person", "name", "marko", "age", 29);
        
        Vertex vadas = graph.addVertex(T.label, "person", "name", "vadas", "age", 27);
        Vertex lop = graph.addVertex(T.label, "software", "name", "lop", "lang", "java");
        Vertex josh = graph.addVertex(T.label, "person", "name", "josh", "age", 32);
        Vertex ripple = graph.addVertex(T.label, "software", "name", "ripple", "lang", "java");
        Vertex peter = graph.addVertex(T.label, "person", "name", "peter", "age", 35);
        
        System.out.println("Label " + T.label.apply(peter) + " id " + peter.id());
        
        Vertex stud1 = graph.addVertex(T.label, "Student", "name", "arun");
        marko.addEdge("knows", stud1, "weight", 0.5f);
        
        marko.addEdge("knows", vadas, "weight", 0.5f); 
        marko.addEdge("knows", josh, "weight", 1.0f);
        marko.addEdge("created", lop, "weight", 0.4f);
        josh.addEdge("created", ripple, "weight", 1.0f);
        josh.addEdge("created", lop, "weight", 0.4f);
        peter.addEdge("created", lop, "weight", 0.2f);

        System.out.println(IteratorUtils.count(graph.vertices()));
    }*/
}

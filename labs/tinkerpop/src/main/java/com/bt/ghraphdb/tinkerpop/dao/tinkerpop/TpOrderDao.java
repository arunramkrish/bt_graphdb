package com.bt.ghraphdb.tinkerpop.dao.tinkerpop;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.T;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.util.iterator.IteratorUtils;

import com.bt.ghraphdb.tinkerpop.dao.OrderDao;
import com.bt.ghraphdb.tinkerpop.entity.Customer;
import com.bt.ghraphdb.tinkerpop.entity.Item;
import com.bt.ghraphdb.tinkerpop.entity.Order;

public class TpOrderDao implements OrderDao {
	private Graph graph;

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	@Override
	public Long create(Order order) {
		Vertex orderVtx = graph.addVertex(T.label, "order", "shipCity", order.getShipCity(), "orderDate",
				order.getOrderDate(), "requiredDate", order.getRequiredDate());
		for (Item item : order.getLineItems()) {
			Vertex itemVtx = graph.addVertex(T.label, "item", "unitPrice", item.getUnitPrice(), "quantity",
					item.getQuantity());
			orderVtx.addEdge("contains", itemVtx);

			Vertex prodVtx = graph.vertices(item.getProduct().getId().intValue()).next();
			itemVtx.addEdge("is", prodVtx);
		}
		// IteratorUtils.count(graph.vertices(order.getCustomer().getId()));

		Vertex customerVtx = graph.vertices(order.getCustomer().getId()).next();
		customerVtx.addEdge("ordered", orderVtx);

		return Long.parseLong(orderVtx.id().toString());
	}

	@Override
	public Order get(Long id) {
		Order o = new Order();
		Vertex orderVertex = graph.vertices(id).next();
		o.setId(Long.parseLong(orderVertex.id().toString()));
		
		Vertex customerVertex = orderVertex.edges(Direction.IN, "ordered").next().outVertex();
		
		o.setCustomer(new Customer(Integer.parseInt(customerVertex.id().toString())));
		o.setShipCity(orderVertex.property("shipCity").toString());

		// TODO load line Items
		return o;
	}

}

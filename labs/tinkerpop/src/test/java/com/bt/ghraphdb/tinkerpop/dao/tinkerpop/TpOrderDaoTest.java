package com.bt.ghraphdb.tinkerpop.dao.tinkerpop;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bt.ghraphdb.tinkerpop.dao.DaoFactory;
import com.bt.ghraphdb.tinkerpop.dao.OrderDao;
import com.bt.ghraphdb.tinkerpop.entity.Customer;
import com.bt.ghraphdb.tinkerpop.entity.Item;
import com.bt.ghraphdb.tinkerpop.entity.Order;
import com.bt.ghraphdb.tinkerpop.entity.Product;

public class TpOrderDaoTest {
	private static OrderDao orderDao;
	private static DaoFactory daoFactory;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		daoFactory = new DaoFactory();
		daoFactory.init();

		orderDao = daoFactory.getOrderDao();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		daoFactory.shutdown();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Order order = new Order();
		Customer customer = new Customer(8L);
		order.setCustomer(customer);
		order.setShipCity("Bangalore");
		order.setOrderDate(new Date());
		order.setRequiredDate(new Date());

		Product product = new Product(3132L);
		Item[] items = new Item[1];
		items[0] = new Item(-1L, 10.5F, 100, product);

		order.setLineItems(items);

		Long orderId = orderDao.create(order);

		Order orderInDb = orderDao.get(orderId);

		assertEquals("customer id not matching", 8L, orderInDb.getCustomer().getId().longValue());
	}

}

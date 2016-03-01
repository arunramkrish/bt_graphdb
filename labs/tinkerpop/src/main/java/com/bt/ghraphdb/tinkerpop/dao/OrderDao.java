package com.bt.ghraphdb.tinkerpop.dao;

import com.bt.ghraphdb.tinkerpop.entity.Order;

public interface OrderDao {
	Long create(Order order);
	
	Order get(Long id);
}

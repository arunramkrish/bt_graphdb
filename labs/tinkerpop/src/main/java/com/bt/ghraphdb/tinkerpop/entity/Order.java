package com.bt.ghraphdb.tinkerpop.entity;

import java.util.Date;

public class Order {
	private Long id;
	private String shipCity;
	private Customer customer;
	private Date orderDate;
	private Date requiredDate;
	private Item[] lineItems;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getShipCity() {
		return shipCity;
	}
	public void setShipCity(String shipCity) {
		this.shipCity = shipCity;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getRequiredDate() {
		return requiredDate;
	}
	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}
	public Item[] getLineItems() {
		return lineItems;
	}
	public void setLineItems(Item[] lineItems) {
		this.lineItems = lineItems;
	}
}

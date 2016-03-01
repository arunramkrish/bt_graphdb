package com.bt.ghraphdb.tinkerpop.entity;

public class Product {
	private Long id;

	public Product(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

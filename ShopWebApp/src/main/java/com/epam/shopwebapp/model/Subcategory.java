package com.epam.shopwebapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 *  * The Subcategory class is a Java Bean, representing the subcategory.
 * @author Yuliya_Shydlouskaya
 *
 */
public class Subcategory {
	
	private String name;
	private List<Product> products;

	public Subcategory() {
		this.products = new ArrayList<Product>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Subcategory [name=" + name + ", products=" + products + "]";
	}
	
	

}

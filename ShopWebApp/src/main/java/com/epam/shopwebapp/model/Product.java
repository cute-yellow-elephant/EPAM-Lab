package com.epam.shopwebapp.model;

import java.util.Date;

/**
 *  * The Product class is a Java Bean, representing the product.
 * @author Yuliya_Shydlouskaya
 *
 */
public class Product {
	
	private String name;
	private String provider;
	private String model;
	private Date dateOfIssue;
	private String color;
	private Double price;
	private boolean inStock;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Date getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(Date dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", provider=" + provider + ", model="
				+ model + ", dateOfIssue=" + dateOfIssue + ", color=" + color
				+ ", price=" + price + ", inStock=" + inStock + "]";
	}
	
	

}

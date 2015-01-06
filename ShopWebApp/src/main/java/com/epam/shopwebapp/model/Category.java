package com.epam.shopwebapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Category class is a Java Bean, representing the category.
 * @author Yuliya_Shydlouskaya
 *
 */
public class Category{
	
	private String name;
	private List<Subcategory> subcategories;

	public Category() {
		this.subcategories = new ArrayList<Subcategory>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Subcategory> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<Subcategory> subcategories) {
		this.subcategories = subcategories;
	}

	@Override
	public String toString() {
		return "Category [name=" + name + ", subcategories=" + subcategories
				+ "]";
	}
	
	
	

}

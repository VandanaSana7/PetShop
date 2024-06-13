package com.petshop.in.model;

import jakarta.persistence.*;




@Entity
@Table(name = "pet_categories")
public class PetCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

	public int getCategoryId() {
		return categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PetCategories(int categoryId, String name) {
		
		this.categoryId = categoryId;
		this.name = name;
	}

	public PetCategories(String name) {
		
		this.name = name;
	}

	public PetCategories() {
		
	}

	@Override
	public String toString() {
		return "PetCategories [categoryId=" + categoryId + ", name=" + name + "]";
	}

    
}


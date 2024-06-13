package com.petshop.in.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pets")
public class Pets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private int petId;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "breed", nullable = false, length = 50)
    private String breed;

    @Column(name = "age")
    private int age;

    @Column(name = "price", precision = 10, scale = 2,columnDefinition = "Decimal")
    private int price;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private PetCategories category;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url", length = 255)
    private String imageUrl;


    public int getPetId() {
        return petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public PetCategories getCategory() {
        return category;
    }

    public void setCategory(PetCategories category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

//	public Pets(int petId, String name, String breed, Integer age, int price, PetCategories category,
//			String description, String imageUrl) {
//		super();
//		this.petId = petId;
//		this.name = name;
//		this.breed = breed;
//		this.age = age;
//		this.price = price;
//		this.category = category;
//		this.description = description;
//		this.imageUrl = imageUrl;
//	}

	public Pets(String name, String breed, Integer age, int price, PetCategories category, String description,
			String imageUrl) {

		this.name = name;
		this.breed = breed;
		this.age = age;
		this.price = price;
		this.category = category;
		this.description = description;
		this.imageUrl = imageUrl;
	}

	public Pets() {
		
	}

	@Override
	public String toString() {
		return "Pets [petId=" + petId + ", name=" + name + ", breed=" + breed + ", age=" + age + ", price=" + price
				+ ", category=" + category + ", description=" + description + ", imageUrl=" + imageUrl + "]";
	}
    
}


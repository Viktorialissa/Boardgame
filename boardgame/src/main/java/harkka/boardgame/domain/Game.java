package harkka.boardgame.domain;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Game {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	//attribuutit
	@NotEmpty(message = "Name is mandatory")
	private String name;
	private String brand;
	@NotNull
	private int publishingYear;
	@NotNull
	private double price;
	private String description;
	private String gamerAmount;
	private int lik;
	

	//{"hibernateLazyInitializer"}
	
	@JsonIgnoreProperties ("games")
	@ManyToOne
    @JoinColumn(name = "makeId")
    private Make make;
	
	@ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
	
	
	
	
	//parametriton konstruktori
	public Game() {
		super();
		this.name = null;
		this.brand = null;
		this.publishingYear = 0;
		this.price = 00.00;
		this.description = null;
		this.gamerAmount = null;
		this.lik = 0;
		this.make = null;
		this.category = null;
		
		
	}
	
	//parametrillinen konstruktori
	public Game(String name, String brand, int publishingYear, double price, String description, String gamerAmount, int lik, Category category, Make make) {
		super();
		this.name = name;
		this.brand = brand;
		this.publishingYear = publishingYear;
		this.price = price;
		this.description = description;
		this.gamerAmount = gamerAmount;
		this.lik = lik;
		this.category = category;
		this.make = make;
		
		
	}
	
	//getterit ja setterit
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}	

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPublishingYear() {
		return publishingYear;
	}

	public void setPublishingYear(int publishingYear) {
		this.publishingYear = publishingYear;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getGamerAmount() {
		return gamerAmount; 
	}
	
	public void setGamerAmount(String gamerAmount) {
		this.gamerAmount = gamerAmount;
	}
	
	public int getLik() {
			return lik;
	
	}
	
	public void setLik(int lik) {
		
		this.lik = lik;
	}
	
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Make getMake() {
		return make;
	}

	public void setMake(Make make) {
		this.make = make;
	}
	
	public int getTotal() {
	     this.lik = this.lik + 50 ;
	     return this.lik;
	}
	
	// toString
	@Override //dokumentoiva annotaatio (ei vaikuta järjestelmään mitenkään vrt springin annotaatiot joilla väliä)
	public String toString() {
		if (this.make != null)
				return "Game [id=" + id + ", name= " + name + ", brand= " + brand + ", price= " + price + ", year= " + publishingYear  + ", description= " + description + ", player amount= " + gamerAmount + ", like=" + lik + ", kategoria= " + this.getCategory() + ", valmistaja=" + this.getMake() + " ]";
		else
			return "Game [id=" + id + ", name= " + name + ", brand= " + brand + ", price= " + price + ", year= " + publishingYear  + " description= " + description + " player amount= " + gamerAmount + ", valmistaja" + " ]";
	}

			

}

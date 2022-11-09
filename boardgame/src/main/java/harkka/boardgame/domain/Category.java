package harkka.boardgame.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	//attribuutit
	@NotEmpty
	private String name;
	
	@JsonIgnoreProperties ("categories")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private List<Game> game;
	
	
	//parametriton konstruktori
	public Category() {
		super();
		this.name = null;
	}
	
	public Category(String name) {
		super();
		this.name = name;
	}
	
	
	//getterit ja setterit
			public void setId(Long id) {
				this.id = id;
			}
			
			public Long getId() {
				return id;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}
			
			public List<Game> getGame() {
				return game;
			}

			public void setGame(List<Game> game) {
				this.game = game;
			}

			
			@Override
			public String toString() {
				return "[id= " + id + ", name=" + name + "]";
			}

		
	}

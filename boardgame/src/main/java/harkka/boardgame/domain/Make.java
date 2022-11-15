package harkka.boardgame.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
public class Make {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long makeId;
	
	//attribuutit
	@NotEmpty
	private String name;
	
	//@JsonIgnoreProperties ("makes") aiheutuu looppi tällä, kun kaikilla controllereilla on resti.
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "make")
	private List<Game> game;
	
	
	//parametriton konstruktori
		public Make() {
			super();
			this.name = null;
		}

		//konstruktori
		public Make(String name) {
			super();
			this.name = name;
		}
		
		
		//getterit ja setterit
		public void setId(Long makeId) {
			this.makeId = makeId;
		}
		
		public Long getId() {
			return makeId;
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
			return "[id= " + makeId + ", name=" + name + "]";
		}

		

}

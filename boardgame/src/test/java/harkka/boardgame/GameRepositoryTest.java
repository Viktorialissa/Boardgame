package harkka.boardgame;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import harkka.boardgame.domain.Category;
import harkka.boardgame.domain.CategoryRepository;
import harkka.boardgame.domain.Game;
import harkka.boardgame.domain.GameRepository;
import harkka.boardgame.domain.Make;
import harkka.boardgame.domain.MakeRepository;



@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GameRepositoryTest {
	
	@Autowired
	private GameRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@Autowired
	private MakeRepository mrepository;

	@Test
	public void findByNameShouldReturnGamerAmount() {
		List<Game> game = repository.findByName("NIMI4");
		assertThat(game).hasSize(1);
		assertThat(game.get(0).getGamerAmount()).isEqualTo("2-10");
	}
	
	@Test
	public void createNewGame() {
		Game game = new Game("Monopoli", "ENTIIÄ", 1980, 25.90, "Kuvailu",  "2-5", 400, crepository.findByName("kodinrikkoja").get(0), mrepository.findByName("Valmistaja1").get(0));
		repository.save(game);
		assertThat(game.getId()).isNotNull();
	}
	//"NIMI2", "BRANDI", 2022 , 2.00, "KUVAUS2", "1-2", 3, c2, m2 
	
	@Test
	public void deleteGame() {
		repository.deleteById((long) 2);
		assertThat(repository.findById((long) 2)).isEmpty();
		
	}
	
	@Test
	public void findByNameShouldReturnMakeName() {
		List<Make> make = mrepository.findByName("Valmistaja1"); 
		
		assertThat(make).hasSize(1);
		assertThat(make.get(0).getName()).isEqualTo("Valmistaja1");
	}
	
	@Test
	public void findByNameShouldReturnCategoryName() {
		List<Category> category = crepository.findByName("strategiapeli");
		
		assertThat(category).hasSize(1);
		assertThat(category.get(0).getName()).isEqualTo("strategiapeli");
	}
	
	@Test
	public void createNewMake() {
		Make make = new Make("AtlantisGame");
		mrepository.save(make);
		
		assertThat(make.getId()).isNotNull();
	}
	
	@Test
	public void createNewCategory() {
		Category category = new Category("noppapeli");
		crepository.save(category);
		
		assertThat(category.getId()).isNotNull();
	}
	
	@Test
	public void deleteMake() {
		mrepository.deleteById((long) 1);
		assertThat(mrepository.findById((long) 1)).isEmpty();
		
	}
	
	@Test
	public void deleteCategory() {
		crepository.deleteById((long) 1);
		assertThat(crepository.findById((long) 1)).isEmpty();
		
	}

}


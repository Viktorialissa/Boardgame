package harkka.boardgame;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import harkka.boardgame.domain.CategoryRepository;
import harkka.boardgame.domain.Game;
import harkka.boardgame.domain.GameRepository;
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
		List<Game> game = repository.findByName("NIMI2");
		assertThat(game).hasSize(1);
		assertThat(game.get(0).getGamerAmount()).isEqualTo("1-2");
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
		repository.deleteById((long) 1);
		assertThat(repository.findById((long) 1)).isEmpty();
		
	}

}


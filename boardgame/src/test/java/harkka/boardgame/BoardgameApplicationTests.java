package harkka.boardgame;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import harkka.boardgame.web.CategoryController;
import harkka.boardgame.web.GameController;
import harkka.boardgame.web.MakeController;



@ExtendWith(SpringExtension.class)
@SpringBootTest
class BoardgameApplicationTests {
	
	@Autowired
	private GameController gameController;
	
	@Autowired
	private MakeController makeController;
	
	@Autowired
	private CategoryController categoryController;
	

	@Test
	void contextLoads() throws Exception {
		assertThat(gameController).isNotNull();
		assertThat(makeController).isNotNull();
		assertThat(categoryController).isNotNull();
	}

}

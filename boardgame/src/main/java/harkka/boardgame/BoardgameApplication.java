package harkka.boardgame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import harkka.boardgame.domain.Category;
import harkka.boardgame.domain.CategoryRepository;
import harkka.boardgame.domain.Game;
import harkka.boardgame.domain.GameRepository;
import harkka.boardgame.domain.Make;
import harkka.boardgame.domain.MakeRepository;
import harkka.boardgame.domain.User;
import harkka.boardgame.domain.UserRepository;

@SpringBootApplication
public class BoardgameApplication {
	private static final Logger logger = LoggerFactory.getLogger(BoardgameApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BoardgameApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(GameRepository repository, MakeRepository mrepository, CategoryRepository crepository, UserRepository urepository) {
		
		return (args) -> {
			
			Category c1 = new Category("korttipeli");
			Category c2 = new Category("kodinrikkoja");
			Category c3 = new Category("strategiapeli");
			
			crepository.save(c1);
			crepository.save(c2);
			crepository.save(c3);
			
			
			crepository.findAll().forEach((category) -> {
				logger.info("{}", category);
			});
			
			Make m1 = new Make("Valmistaja1");
			Make m2 = new Make("Valmistaja2");
			Make m3 = new Make("Valmistaja3");
			
			mrepository.save(m1);
			mrepository.save(m2);
			mrepository.save(m3);
			
			
			mrepository.findAll().forEach((make) -> {
				logger.info("{}", make);
			});
			
			logger.info("get all makers");
			for (Make make : mrepository.findAll()) {
				logger.info(make.toString());
			} 
			//String name, String brand, int publishingYear, double price, String description, String gamerAmount, LISÄÄ MAKE
			Game g1 = new Game("NIMI", "BRANDI", 2021 , 1.00, "KUVAUS", "1", 0, c1, mrepository.findByName("Valmistaja1").get(0) );
			Game g2 = new Game("NIMI2", "BRANDI", 2022 , 2.00, "KUVAUS2", "1-2", 3, c2, m2 );
			Game g3 = new Game("NIMI3", "BRANDI", 2023 , 3.00, "KUVAUS3", "2-3", 13, c3, m3 );
			Game g4 = new Game("NIMI4", "BRANDI", 1900 , 15.00, "Hauska peli", "2-10", 600, c3, m3 );
			
			repository.save(g1);
			repository.save(g2);
			repository.save(g3);
			repository.save(g4);
			
			
			
			logger.info("get all games");
			for (Game game : repository.findAll()) {
				logger.info(game.toString());
			}
		
			repository.findAll().forEach((game) -> {
				logger.info("{}", game);
			});
			
			
			// Create users: admin/admin user/user
			User user1 = new User("user", "$2a$10$.jsEJvQtaUtWjLvfSIW6JORMWDxLS8XdWUxQjcZRShn6VM85T19QO", "user@user.com", "USER");
			User user2 = new User("admin", "$2a$10$znXD2WEvoyZnpwxQ1F.DB.qXMDbONVPN5nLVbmi4/Pv766QfhBKim", "admin@admin.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
		};
	}

}

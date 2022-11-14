package harkka.boardgame.domain;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;


public interface GameRepository extends CrudRepository<Game, Long> {
	
	@RestResource
	List<Game> findByName(@Param("name") String name);
	List<Make> findByMake(Long id);
	List<Category> findByCategory(Long id);
	@Transactional
	@Modifying
	@Query("update Game g set g.lik = g.lik +1 where g.gameId = :gameId")
	void addLikByOne(@Param("gameId") Long gameId);
	

}

package harkka.boardgame.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;


public interface GameRepository extends CrudRepository<Game, Long> {
	
	@RestResource
	List<Game> findByName(@Param("name") String name);
	List<Make> findByMakeId(Long makeId);
	
	//@Query(update game set lik = lik +1 where id = gameId)
	//void addLikByOne(Long gameId);
	//

}

package harkka.boardgame.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;


public interface MakeRepository extends CrudRepository<Make, Long> {
	
	@RestResource
	List<Make> findByName(@Param("name") String name);
	List<Make> findByMakeId(Long makeId);


}

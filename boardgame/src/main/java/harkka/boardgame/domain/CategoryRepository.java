package harkka.boardgame.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	@RestResource
	List<Category> findByName(@Param("name") String name);
	List<Category> findByCategoryId(Long categoryId);


}

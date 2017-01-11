package pl.bills.dao;

import org.springframework.data.repository.CrudRepository;
import pl.bills.entities.CategoryEntity;

/**
 * Created by trot on 09.01.17.
 */
public interface CategoryDao extends CrudRepository<CategoryEntity, Integer> {
    CategoryEntity findByName(String name);
}

package pl.bills.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bills.entities.CategoryEntity;

import java.util.Collection;

/**
 * Created by trot on 09.01.17.
 */

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    CategoryEntity findByName(String name);

    CategoryEntity findByCategoryId(Integer categoryId);

    Collection<CategoryEntity> findAllByName(String name);
}

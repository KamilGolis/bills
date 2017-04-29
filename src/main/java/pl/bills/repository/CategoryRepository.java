package pl.bills.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bills.entities.CategoryEntity;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    Optional<CategoryEntity> findByName(String name);

    Optional<CategoryEntity> findByCategoryId(Integer categoryId);

    Optional<Collection<CategoryEntity>> findAllByName(String name);
}

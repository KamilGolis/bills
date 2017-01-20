package pl.bills.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bills.entities.StatusEntity;

import java.util.Collection;

/**
 * Created by trot on 09.01.17.
 */
@Repository
public interface StatusRepository extends JpaRepository<StatusEntity, Integer> {
    StatusEntity findById(Integer id);

    StatusEntity findByName(String name);

    Collection<StatusEntity> findAllByName(String nam);
}

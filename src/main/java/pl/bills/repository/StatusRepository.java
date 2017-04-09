package pl.bills.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bills.entities.StatusEntity;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by trot on 09.01.17.
 */
@Repository
public interface StatusRepository extends JpaRepository<StatusEntity, Integer> {

    Optional<StatusEntity> findById(Integer id);

    Optional<StatusEntity> findByName(String name);

    Optional<Collection<StatusEntity>> findAllByName(String name);

}

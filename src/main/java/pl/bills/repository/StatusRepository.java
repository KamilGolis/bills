package pl.bills.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bills.entities.StatusEntity;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<StatusEntity, Integer> {

    Optional<StatusEntity> findById(Integer id);

    Optional<StatusEntity> findByName(String name);

    Optional<Collection<StatusEntity>> findAllByName(String nam);
}

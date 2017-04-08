package pl.bills.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bills.entities.BillsEntity;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by trot on 08.01.17.
 */

@Repository
public interface BillsRepository extends JpaRepository<BillsEntity, Integer> {

    Optional<Collection<BillsEntity>> findAllByTitle(String title);

    Optional<Collection<BillsEntity>> findAllByCategoryName(String category);

    Optional<Collection<BillsEntity>> findAllByStatusName(String status);

    Optional<Collection<BillsEntity>> findAllByLoanHolderName(String name);

    Optional<BillsEntity> findByTitle(String title);

    Optional<BillsEntity> findById(Integer id);

}

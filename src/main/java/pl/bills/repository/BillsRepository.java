package pl.bills.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bills.entities.BillsEntity;

import java.util.Collection;

/**
 * Created by trot on 08.01.17.
 */

@Repository
public interface BillsRepository extends JpaRepository<BillsEntity, Integer> {

    Collection<BillsEntity> findAllByTitle(String title);

    Collection<BillsEntity> findAllByCategoryName(String category);

    Collection<BillsEntity> findAllByStatusName(String status);

    Collection<BillsEntity> findAllByLoanHolderName(String name);

    BillsEntity findByTitle(String title);

    BillsEntity findById(Integer id);

}

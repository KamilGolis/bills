package pl.bills.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bills.entities.LoanHolderEntity;

/**
 * Created by trot on 09.02.17.
 */
public interface LoanHolderRepository extends JpaRepository<LoanHolderEntity, Integer> {

    LoanHolderEntity findByName(String name);
}

package pl.bills.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bills.entities.LoanHolderEntity;

import java.util.Optional;

public interface LoanHolderRepository extends JpaRepository<LoanHolderEntity, Integer> {

    Optional<LoanHolderEntity> findByName(String name);
}

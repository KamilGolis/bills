package pl.bills.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bills.entities.LoanHolderEntity;
import pl.bills.repository.LoanHolderRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class LoanHolderService {

    private final LoanHolderRepository loanHolderRepository;

    @Autowired
    public LoanHolderService(LoanHolderRepository loanHolderRepository) {
        this.loanHolderRepository = loanHolderRepository;
    }

    public Optional<Collection<LoanHolderEntity>> getAllLoanHolders() {
        return Optional.ofNullable(loanHolderRepository.findAll());
    }

    public Optional<LoanHolderEntity> getLoan(String loanHolderName) {
        return loanHolderRepository.findByName(loanHolderName);
    }
}

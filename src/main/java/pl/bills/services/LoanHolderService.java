package pl.bills.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bills.entities.LoanHolderEntity;
import pl.bills.repository.LoanHolderRepository;

import java.util.Collection;

/**
 * Created by trot on 09.02.17.
 */
@Service
public class LoanHolderService {

    @Autowired
    LoanHolderRepository loanHolderRepository;

    public Collection<LoanHolderEntity> getAllLoanHolders() {
        return loanHolderRepository.findAll();
    }

    public LoanHolderEntity getLoan(String loanHolderName) {
        return loanHolderRepository.findByName(loanHolderName);
    }
}

package pl.bills.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import pl.bills.entities.LoanHolderEntity;
import pl.bills.repository.LoanHolderRepository;
import pl.bills.services.LoanHolderService;

import java.text.ParseException;
import java.util.Locale;
import java.util.NoSuchElementException;

/**
 * Created by trot on 09.02.17.
 */
@Component
public class LoanHolderFormatter implements Formatter<LoanHolderEntity> {

    @Autowired
    LoanHolderService loanHolderService;


    @Override
    public LoanHolderEntity parse(String name, Locale locale) throws ParseException {
        return loanHolderService.getLoan(name)
                .orElseThrow(() -> new NoSuchElementException(String.format("Loan holder=%s was not found", name)));
    }

    @Override
    public String print(LoanHolderEntity loanEntity, Locale locale) {
        return loanEntity.getName();
    }
}

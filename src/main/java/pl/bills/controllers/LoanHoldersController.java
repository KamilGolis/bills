package pl.bills.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.bills.entities.LoanHolderEntity;
import pl.bills.services.LoanHolderService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class LoanHoldersController {

    private final Logger log = LoggerFactory.getLogger(LoanHoldersController.class);

    private LoanHolderService loanHolderService;

    @Autowired
    public LoanHoldersController(LoanHolderService loanHolderService) {
        this.loanHolderService = loanHolderService;
    }

    @RequestMapping(value = "/loans", method = RequestMethod.GET)
    public ResponseEntity<Collection<LoanHolderEntity>> getAllLoanHolders() {
        Optional<Collection<LoanHolderEntity>> loans = loanHolderService.getAllLoanHolders();
        log.info("Getting all loan holders.");
        if (loans.isPresent()) {
            log.info("Found " + loans.get().size() + " items.");
            return new ResponseEntity<Collection<LoanHolderEntity>>(loans.get(), HttpStatus.OK);
        }
        log.info("Nothing found.");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

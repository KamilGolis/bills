package pl.bills.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.bills.entities.LoanHolderEntity;
import pl.bills.services.LoanHolderService;

import java.util.Collection;

@RestController
public class LoanHoldersController {

    private final Logger log = LoggerFactory.getLogger(LoanHoldersController.class);

    @Autowired
    LoanHolderService loanHolderService;

    @RequestMapping(value = "/loans", method = RequestMethod.GET)
    @ResponseBody
    public Collection<LoanHolderEntity> getAllLoanHolders() {
        return loanHolderService.getAllLoanHolders();
    }

}

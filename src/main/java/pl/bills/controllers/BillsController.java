package pl.bills.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.bills.entities.BillDTO;
import pl.bills.entities.BillsEntity;
import pl.bills.services.BillsService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
public class BillsController {

    private final Logger log = LoggerFactory.getLogger(BillsController.class);
    @Autowired
    BillsService billsService;

    @RequestMapping(value = "/bills", method = RequestMethod.GET)
    @ResponseBody
    public Collection<BillDTO> getAllBills() {
        Collection<BillDTO> bills = billsService.getBills();
        log.info("Getting bills. " + bills.size() + " items.");
        return bills;
    }

    @RequestMapping(value = "/bill/search/{searchVal}", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Collection<BillsEntity> searchBills(@PathVariable String searchVal) {
        log.info("Searching for " + searchVal);
        Collection<BillsEntity> bills = billsService.search(searchVal);
        log.info("Found " + bills.size() + " items.");
        return bills;
    }

    @RequestMapping(value = "/bill/remove/{id}", method = RequestMethod.PATCH)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public BillsEntity trash(@PathVariable Integer id) {
        log.info("Removing bill id=" + id);
        billsService.removeBill(id);
        return billsService.getOneBill(id);
    }

    @RequestMapping(value = "/bill/removeall", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void trash() {
        log.info("Removing all bills.");
        billsService.removeAllBills();
    }

    @RequestMapping(value = "/bill/add", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public BillsEntity add(@Valid @RequestBody BillsEntity billsEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("Binding error -> Bills / add");
            bindingResult.getAllErrors().forEach(l -> log.error(l.getDefaultMessage()));
        }
        log.info("Creating bill " + billsEntity.toString());
        billsService.addBill(billsEntity);
        return billsEntity;
    }
}

package pl.bills.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bills.entities.BillsEntity;
import pl.bills.services.BillsService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class BillsController {

    private final Logger log = LoggerFactory.getLogger(BillsController.class);

    private BillsService billsService;

    @Autowired
    public BillsController(BillsService billsService) {
        this.billsService = billsService;
    }

    @GetMapping(value = "/bills")
    public ResponseEntity<Collection<BillsEntity>> getAllBills() {
        Optional<Collection<BillsEntity>> bills = billsService.getBills();
        if (bills.isPresent()) {
            log.info("Getting bills. " + bills.get().size() + " items.");
            return new ResponseEntity<>(bills.get(), HttpStatus.OK);
        }
        log.info("Nothing found.");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/bill/{id}")
    public ResponseEntity<BillsEntity> getBillById(@PathVariable Integer id) {
        log.info("Getting bill id=" + id);
        Optional<BillsEntity> bill = billsService.getOneBill(id);
        if (bill.isPresent()) {
            return new ResponseEntity<>(bill.get(), HttpStatus.OK);
        }
        log.info("Nothing found.");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/bill/search/{searchVal}")
    public ResponseEntity<Collection<BillsEntity>> searchBills(@PathVariable String searchVal) {
        log.info("Searching for " + searchVal);
        Optional<Collection<BillsEntity>> bills = billsService.search(searchVal);
        if (bills.isPresent()) {
            log.info("Found " + bills.get().size() + " items.");
            return new ResponseEntity<>(bills.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping(value = "/bill/remove/{id}")
    public ResponseEntity<BillsEntity> trash(@PathVariable Integer id) {
        log.info("Removing bill id=" + id);
        billsService.removeBill(id);
        Optional<BillsEntity> bill = billsService.getOneBill(id);
        if (bill.isPresent()) {
            return new ResponseEntity<>(bill.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/bill/removeall")
    public ResponseEntity<Void> trash() {
        log.info("Removing all bills.");
        billsService.removeAllBills();
        return new ResponseEntity<>(HttpStatus.GONE);
    }

    @PostMapping(value = "/bill/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BillsEntity> createBill(@Valid @RequestBody BillsEntity billsEntity) {
        log.info("Creating Bill : {}", billsEntity);
        billsService.addBill(billsEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

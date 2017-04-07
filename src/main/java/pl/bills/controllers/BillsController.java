package pl.bills.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.bills.entities.BillsEntity;
import pl.bills.services.BillsService;
import pl.bills.services.CustomErrorType;

import java.util.Collection;

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
        Collection<BillsEntity> bills = billsService.getBills();
        if (bills.isEmpty() || bills == null) {
            log.info("Nothing found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            log.info("Getting bills. " + bills.size() + " items.");
            return new ResponseEntity<>(bills, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/bill/{id}")
    public ResponseEntity<BillsEntity> getBillById(@PathVariable Integer id) {
        log.info("Getting bill id=" + id);
        BillsEntity bill = billsService.getOneBill(id);
        if (bill == null) {
            log.info("Nothing found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }

    @GetMapping(value = "/bill/search/{searchVal}")
    public ResponseEntity<Collection<BillsEntity>> searchBills(@PathVariable String searchVal) {
        log.info("Searching for " + searchVal);
        Collection<BillsEntity> bills = billsService.search(searchVal);
        log.info("Found " + bills.size() + " items.");
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    @GetMapping(value = "/bill/remove/{id}")
    public ResponseEntity<BillsEntity> trash(@PathVariable Integer id) {
        log.info("Removing bill id=" + id);
        billsService.removeBill(id);
        BillsEntity bill = billsService.getOneBill(id);
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }

    @GetMapping(value = "/bill/removeall")
    public ResponseEntity<Void> trash() {
        log.info("Removing all bills.");
        billsService.removeAllBills();
        return new ResponseEntity<Void>(HttpStatus.GONE);
    }

//    @RequestMapping(value = "/bill/add", method = RequestMethod.POST)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.CREATED)
//    public BillsEntity add(@Valid @RequestBody BillsEntity billsEntity, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            log.error("Binding error -> Bills / add");
//            bindingResult.getAllErrors().forEach(l -> log.error(l.getDefaultMessage()));
//        }
//        log.info("Creating bill " + billsEntity.toString());
//        billsService.addBill(billsEntity);
//        return billsEntity;
//    }

    @PostMapping(value = "/bill/add")
    public ResponseEntity<BillsEntity> createBill(@RequestBody BillsEntity billsEntity, BindingResult errors) {
        log.info("Creating Bill : {}", billsEntity);

//        if (errors.hasErrors()) {
//            return new ResponseEntity(new CustomErrorType("Unable to create. "), HttpStatus.CONFLICT);
//        }
        billsService.checkBill(billsEntity, errors);
        if (errors.hasErrors()) {
            log.error("Unable to create. ");
            return new ResponseEntity(new CustomErrorType("Unable to create. "), HttpStatus.CONFLICT);
        }
        billsService.addBill(billsEntity);

//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(ucBuilder.path("/bill/{id}").buildAndExpand(billsEntity.getId()).toUri());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

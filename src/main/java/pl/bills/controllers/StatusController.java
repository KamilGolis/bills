package pl.bills.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.bills.entities.StatusEntity;
import pl.bills.services.StatusService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class StatusController {

    private final Logger log = LoggerFactory.getLogger(StatusController.class);

    private StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @RequestMapping(value = "/statuses", method = RequestMethod.GET)
    public ResponseEntity<Collection<StatusEntity>> getAllStatuses() {
        Optional<Collection<StatusEntity>> statuses = statusService.getAllStatuses();
        log.info("Getting all statuses.");
        if (statuses.isPresent()) {
            log.info("Found " + statuses.get().size() + " items.");
            return new ResponseEntity<Collection<StatusEntity>>(statuses.get(), HttpStatus.OK);
        }
        log.info("Nothing found.");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

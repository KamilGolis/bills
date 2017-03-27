package pl.bills.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.bills.entities.StatusEntity;
import pl.bills.services.StatusService;

import java.util.Collection;

@RestController
public class StatusController {

    private final Logger log = LoggerFactory.getLogger(StatusController.class);

    @Autowired
    StatusService statusService;

    @RequestMapping(value = "/statuses", method = RequestMethod.GET)
    @ResponseBody
    public Collection<StatusEntity> getAllStatuses() {
        return statusService.getAllStatuses();
    }

}

package pl.bills.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.bills.services.BillsService;
import pl.bills.services.StatusService;

/**
 * Created by trot on 23.01.17.
 */
@Controller
public class EditBillsController {

    @Autowired
    BillsService billsService;

    @Autowired
    StatusService statusService;


}

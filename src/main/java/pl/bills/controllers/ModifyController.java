package pl.bills.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.bills.repository.BillsRepository;
import pl.bills.repository.StatusRepository;

/**
 * Created by trot on 19.01.17.
 */

@Controller
@RequestMapping(value = "/modify")
public class ModifyController {

    @Autowired
    BillsRepository billsRepository;

    @Autowired
    StatusRepository statusRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String modify(@RequestParam Integer id, @RequestParam String mode) {
        switch (mode) {
            case "status":

                break;
        }

        return "redirect:bills";
    }
}

package pl.bills.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.bills.services.BillsService;

import java.util.ArrayList;

/**
 * Created by trot on 23.01.17.
 */

@Controller
public class DeletedBillsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeletedBillsController.class);

    private BillsService billsService;

    @Autowired
    public DeletedBillsController(BillsService billsService) {
        this.billsService = billsService;
    }

    @RequestMapping(value = "/trash")
    public ModelAndView trash(Model model) {
        LOGGER.info("Getting deleted bills");
        model.addAttribute("activeMenu", "trash");
        ModelAndView mav = new ModelAndView("trash");
        mav.addObject("billsList", billsService.getDeletedBills().orElse(new ArrayList<>()));
        return mav;
    }

    @RequestMapping(value = "/undo")
    public String undo(@RequestParam Integer id) {
        LOGGER.info("Doing undo on bill id=%s", id);
        billsService.undoBill(id);
        return "redirect:trash";
    }

    @RequestMapping(value = "/delete")
    public String delete(@RequestParam Integer id) {
        LOGGER.info("Deleting bill id=%s", id);
        billsService.deleteBill(id);
        return "redirect:trash";
    }
}

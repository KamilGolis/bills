package pl.bills.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.bills.services.BillsService;

/**
 * Created by trot on 23.01.17.
 */

@Controller
public class DeletedBillsController {

    @Autowired
    BillsService billsService;

    @RequestMapping(value = "/trash")
    public ModelAndView trash(Model model) {
        model.addAttribute("activeMenu", "trash");
        ModelAndView mav = new ModelAndView("trash");
        mav.addObject("billsList", billsService.getDeletedBills());
        return mav;
    }

    @RequestMapping(value = "/undo")
    public String undo(@RequestParam Integer id) {
        billsService.undoBill(id);
        return "redirect:trash";
    }

    @RequestMapping(value = "/delete")
    public String delete(@RequestParam Integer id) {
        billsService.deleteBill(id);
        return "redirect:trash";
    }
}

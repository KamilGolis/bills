package pl.bills.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.bills.entities.BillsEntity;
import pl.bills.repository.BillsRepository;
import pl.bills.repository.CategoryRepository;
import pl.bills.repository.StatusRepository;
import pl.bills.services.BillsService;

import java.util.Collection;

/**
 * Created by trot on 19.01.17.
 */

@Controller
public class BillsController {

    @Autowired
    BillsService billsService;

    @RequestMapping(value = "/bills", method = RequestMethod.GET)
    public ModelAndView bills(Model model) {
        model.addAttribute("activeMenu", "bills");
        ModelAndView mav = new ModelAndView("bills");
        mav.addObject("billsList", billsService.getBills());
        return mav;
    }

    @RequestMapping(value = "/search")
    public ModelAndView search(ModelAndView mav, @RequestParam String search) {
        mav = new ModelAndView("bills");
        mav.addObject("billsList", billsService.search(search));
        return mav;
    }

//    @RequestMapping(value = "/addRecord", method = RequestMethod.GET)
//    public ModelAndView addRecord() {
//
//        ModelAndView mav = new ModelAndView("home");
//        BillsEntity billsEntity = new BillsEntity();
//        mav.addObject("billsEntity", billsEntity);
//        mav.addObject("statusList", statusRepository.findAll());
//        return mav;
//    }

    @RequestMapping(value = "/remove")
    public String trash(@RequestParam Integer id) {
        if (billsService.removeBill(id)) {
            return "redirect:bills";
        }
        return "bills";
    }

}

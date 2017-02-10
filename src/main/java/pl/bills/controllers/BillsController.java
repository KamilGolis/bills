package pl.bills.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.bills.entities.BillsEntity;
import pl.bills.services.BillsService;
import pl.bills.services.CategoryService;
import pl.bills.services.LoanHolderService;
import pl.bills.services.StatusService;

import javax.validation.Valid;

/**
 * Created by trot on 19.01.17.
 */

@Controller
public class BillsController {

    @Autowired
    BillsService billsService;

    @Autowired
    StatusService statusService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    LoanHolderService loanHolderService;

    @RequestMapping(value = "/bills", method = RequestMethod.GET)
    public ModelAndView bills(Model model) {
        model.addAttribute("activeMenu", "bills");
        ModelAndView mav = new ModelAndView("bills");
        mav.addObject("billsList", billsService.getBills());
        mav.addObject("statusList", statusService.getAllStatuses());
        mav.addObject("categoryList", categoryService.getAll());
        mav.addObject("loanList", loanHolderService.getAllLoanHolders());
        mav.addObject("form", new BillsEntity());
        return mav;
    }

    @RequestMapping(value = "/search")
    public ModelAndView search(ModelAndView mav, @RequestParam String search) {
        mav = new ModelAndView("bills");
        mav.addObject("billsList", billsService.search(search));
        return mav;
    }

    @RequestMapping(value = "/remove")
    public String trash(@RequestParam Integer id) {
        if (billsService.removeBill(id)) {
            return "redirect:bills";
        }
        return "bills";
    }

    @RequestMapping(value = "/removeall")
    public String trash() {
        billsService.removeAllBills();
        return "redirect:bills";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute BillsEntity billsEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.err.println("Binding error -> Bills / add");
            bindingResult.getAllErrors().forEach(System.err::println);
            return "error";
        }
        billsService.addBill(billsEntity);
        return "redirect:bills";
    }

}

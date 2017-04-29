package pl.bills.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.bills.entities.BillsEntity;
import pl.bills.other.CurrentUser;
import pl.bills.services.BillsService;
import pl.bills.services.CategoryService;
import pl.bills.services.LoanHolderService;
import pl.bills.services.StatusService;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class BillsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BillsController.class);

    private final BillsService billsService;
    private final StatusService statusService;
    private final CategoryService categoryService;
    private final LoanHolderService loanHolderService;

    @Autowired
    public BillsController(BillsService billsService, StatusService statusService, CategoryService categoryService, LoanHolderService loanHolderService) {
        this.billsService = billsService;
        this.statusService = statusService;
        this.categoryService = categoryService;
        this.loanHolderService = loanHolderService;
    }

    @RequestMapping(value = "/bills", method = RequestMethod.GET)
    public ModelAndView bills(Model model) {
        LOGGER.info("Getting all bills list");
        model.addAttribute("activeMenu", "bills");
        ModelAndView mav = new ModelAndView("bills");
        mav.addObject("billsList", billsService.getBills().orElse(new ArrayList<>()));
        mav.addObject("statusList", statusService.getAllStatuses().orElse(new ArrayList<>()));
        mav.addObject("categoryList", categoryService.getAll().orElse(new ArrayList<>()));
        mav.addObject("loanList", loanHolderService.getAllLoanHolders().orElse(new ArrayList<>()));
        mav.addObject("form", new BillsEntity());
        return mav;
    }

    @RequestMapping(value = "/search")
    public ModelAndView search(@RequestParam String search) {
        ModelAndView mav = new ModelAndView("bills");
        mav.addObject("billsList", billsService.search(search).orElse(new ArrayList<>()));
        return mav;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @RequestMapping(value = "/remove")
    public String trash(@RequestParam Integer id) {
        LOGGER.info("Removing bill id={}", id);
        billsService.removeBill(id);
        return "redirect:bills";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @RequestMapping(value = "/removeall")
    public String trash() {
        LOGGER.info("Removing all bills");
        billsService.removeAllBills();
        return "redirect:bills";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute BillsEntity billsEntity,
                      @ModelAttribute("currentUser") CurrentUser currentUser,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            LOGGER.info("Binding error -> Bills / add");
            bindingResult.getAllErrors().forEach(e -> LOGGER.error("Binding errors " + e.getDefaultMessage()));
            return "error";
        }
        billsEntity.setUser(currentUser.getUser());
        billsService.addBill(billsEntity);
        return "redirect:bills";
    }
}

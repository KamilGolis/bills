package pl.bills.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.bills.entities.BillsEntity;
import pl.bills.services.BillsService;
import pl.bills.services.CategoryService;
import pl.bills.services.LoanHolderService;
import pl.bills.services.StatusService;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * Created by trot on 19.01.17.
 */

@Controller
public class ModifyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModifyController.class);

    private BillsService billsService;
    private StatusService statusService;
    private CategoryService categoryService;
    private LoanHolderService loanHolderService;

    @Autowired
    public ModifyController(BillsService billsService, StatusService statusService, CategoryService categoryService, LoanHolderService loanHolderService) {
        this.billsService = billsService;
        this.statusService = statusService;
        this.categoryService = categoryService;
        this.loanHolderService = loanHolderService;
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public ModelAndView modifyForm(@RequestParam Integer id, Model model) {
        LOGGER.info("Modifying bill id={}", id);
        model.addAttribute("activeMenu", "bills");
        ModelAndView mav = new ModelAndView("editRecord");
        mav.addObject("billsList", billsService.getBills().orElse(new ArrayList<>()));
        mav.addObject("statusList", statusService.getAllStatuses().orElse(new ArrayList<>()));
        mav.addObject("categoryList", categoryService.getAll().orElse(new ArrayList<>()));
        mav.addObject("loanHoldersList", loanHolderService.getAllLoanHolders().orElse(new ArrayList<>()));
        mav.addObject("form", billsService.getOneBill(id).orElse(new BillsEntity()));
        return mav;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }

    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public String modify(@Valid @ModelAttribute("form") BillsEntity billsEntity, @RequestParam Integer id,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            LOGGER.error("Binding error -> Modify / apply");
            bindingResult.getAllErrors().forEach(e -> LOGGER.error(e.getDefaultMessage()));
            return "error";
        }
        billsEntity.setId(id);
        billsService.addBill(billsEntity);
        return "redirect:bills";
    }
}

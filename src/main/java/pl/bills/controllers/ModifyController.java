package pl.bills.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.bills.entities.BillsEntity;
import pl.bills.services.BillsService;
import pl.bills.services.CategoryService;
import pl.bills.services.StatusService;

import javax.validation.Valid;

/**
 * Created by trot on 19.01.17.
 */

@Controller
public class ModifyController {

    @Autowired
    BillsService billsService;

    @Autowired
    StatusService statusService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public ModelAndView modifyForm(@RequestParam Integer id, Model model) {
        model.addAttribute("activeMenu", "bills");
        ModelAndView mav = new ModelAndView("editRecord");
        mav.addObject("billsList", billsService.getBills());
        mav.addObject("statusList", statusService.getAllStatuses());
        mav.addObject("categoryList", categoryService.getAll());
        mav.addObject("form", billsService.getOneBill(id));
        return mav;
    }

    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public String modify(@Valid @ModelAttribute("form") BillsEntity billsEntity, @RequestParam Integer id,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "error";
        }
        billsEntity.setId(id);
        billsService.addBill(billsEntity);
        return "redirect:bills";
    }
}

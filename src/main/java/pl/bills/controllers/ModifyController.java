package pl.bills.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.bills.forms.RecordForm;
import pl.bills.services.BillsService;
import pl.bills.services.StatusService;

/**
 * Created by trot on 19.01.17.
 */

@Controller
public class ModifyController {

    @Autowired
    BillsService billsService;

    @Autowired
    StatusService statusService;

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public ModelAndView modifyForm(@RequestParam Integer id, Model model) {
        model.addAttribute("activeMenu", "bills");
        ModelAndView mav = new ModelAndView("editRecord");
        mav.addObject("billsList", billsService.getBills());
        mav.addObject("statusList", statusService.getAllStatuses());
        RecordForm form = billsService.getOneBill(id);
        mav.addObject("form", form);
        return mav;
    }

    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public String modify(@RequestParam Integer id, @ModelAttribute RecordForm form) {
        form.setId(id);
        billsService.addBillFromForm(form);
        return "redirect:bills";
    }
}

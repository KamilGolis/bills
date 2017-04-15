package pl.bills.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bills.services.BillsService;
import pl.bills.services.CountingServices;

/**
 * Created by trot on 09.01.17.
 */

@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    private CountingServices countingServices;

    @Autowired
    public HomeController(CountingServices countingServices) {
        this.countingServices = countingServices;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        LOGGER.info("Counting values (biggest, frequent, most used)");
        model.addAttribute("activeMenu", "home");
        model.addAttribute("total", countingServices.totalBillsPrice());
        model.addAttribute("biggest", countingServices.biggestBillPrice());
        model.addAttribute("frequent", countingServices.mostFrequentBill());
        return "home";
    }
}

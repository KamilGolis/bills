package pl.bills.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.bills.dao.BillsDao;
import pl.bills.dao.CategoryDao;
import pl.bills.dao.StatusDao;
import pl.bills.entities.BillsEntity;
import pl.bills.entities.CategoryEntity;

import java.util.List;

/**
 * Created by trot on 09.01.17.
 */

@Controller
public class HomeController {

    @Autowired
    BillsDao billsDao;

    @Autowired
    StatusDao statusDao;

    @Autowired
    CategoryDao categoryDao;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/home")
    public String home(Model model) {

        List<BillsEntity> billsEntityList = billsDao.findBillsByCategoryName("main");
        model.addAttribute("billList", billsEntityList);
        model.addAttribute("statusList", statusDao.findAll());
        model.addAttribute("billsCount", billsEntityList.size());
        return "home";
    }


//    @RequestMapping(value = "/trash")
//    public String remove(@RequestParam Integer id) {
//        return "redirect:home";
//    }

    @RequestMapping(value = "/remove")
    public String trash(@RequestParam Integer id) {
        BillsEntity bill = billsDao.findOne(id);
        CategoryEntity category = categoryDao.findByName("trash");
        bill.setCategory(category);
        billsDao.save(bill);
        return "redirect:home";
    }

    @RequestMapping(value = "/search")
    public String search(Model model, @RequestParam String search) {
//        model.addAttribute("billList", billsDao.searchBills(search));
//        model.addAttribute("search", search);
        return "home";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(BillsDao billsDao) {

//        BillWrapper bw = new BillWrapper();
//        Bill bill = bw.createBill(billDao);

//        List<Status> statuses = statusRepository.findByName(bill.getStatus());
//        if(statuses.isEmpty()){
//            Status status = new Status();
//            status.setName(bill.getStatus());
//            statusRepository.save(status);
//        }
//
//        bill.setCategory("main");
//        billRepository.save(bill);
        return "redirect:home";
    }
}

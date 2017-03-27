package pl.bills.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.bills.entities.CategoryEntity;
import pl.bills.services.CategoryService;

import java.util.Collection;

@RestController
public class CategoryController {

    private final Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    @ResponseBody
    public Collection<CategoryEntity> getAllCategories() {
        return categoryService.getAll();
    }

}

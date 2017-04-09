package pl.bills.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.bills.entities.CategoryEntity;
import pl.bills.services.CategoryService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class CategoryController {

    private final Logger log = LoggerFactory.getLogger(CategoryController.class);

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ResponseEntity<Collection<CategoryEntity>> getAllCategories() {
        log.info("Getting all categories.");
        Optional<Collection<CategoryEntity>> categories = categoryService.getAll();
        if (categories.isPresent()) {
            log.info("Found " + categories.get().size() + " items.");
            return new ResponseEntity<Collection<CategoryEntity>>(categories.get(), HttpStatus.OK);
        }
        log.info("Nothing found.");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

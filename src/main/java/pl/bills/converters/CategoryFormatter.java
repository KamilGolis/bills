package pl.bills.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import pl.bills.entities.CategoryEntity;
import pl.bills.services.CategoryService;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

/**
 * Created by trot on 04.02.17.
 */
@Component
public class CategoryFormatter implements Formatter<CategoryEntity> {
    @Autowired
    CategoryService categoryService;

    @Override
    public CategoryEntity parse(String categoryName, Locale locale) throws ParseException {
        return categoryService.getCategory(categoryName).get();
    }

    @Override
    public String print(CategoryEntity category, Locale locale) {
        return category.getName();
    }
}

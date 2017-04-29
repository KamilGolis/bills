package pl.bills.converters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import pl.bills.entities.CategoryEntity;
import pl.bills.services.CategoryService;

import java.text.ParseException;
import java.util.Locale;
import java.util.NoSuchElementException;

@Component
public class CategoryFormatter implements Formatter<CategoryEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryFormatter.class);

    @Autowired
    private CategoryService categoryService;

    @Override
    public CategoryEntity parse(String categoryName, Locale locale) throws ParseException {
        LOGGER.info("Converting category={} to entity", categoryName);
        return categoryService.getCategory(categoryName)
                .orElseThrow(() -> new NoSuchElementException(String.format("Category=%s was not found", categoryName)));
    }

    @Override
    public String print(CategoryEntity category, Locale locale) {
        LOGGER.info("Converting category entity to string");
        return category.getName();
    }
}

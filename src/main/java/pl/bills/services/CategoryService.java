package pl.bills.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bills.entities.CategoryEntity;
import pl.bills.enums.CategoryEnum;
import pl.bills.repository.CategoryRepository;

import java.util.Collection;

/**
 * Created by trot on 04.02.17.
 */
@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Collection<CategoryEntity> getAll() {
        return categoryRepository.findAll();
    }

    public CategoryEntity getDefaultCategory() {
        return categoryRepository.findByName(CategoryEnum.MAIN.get());
    }

    public CategoryEntity getCategory(String categoryName) {
        return categoryRepository.findByName(categoryName);
    }
}

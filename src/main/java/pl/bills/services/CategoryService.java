package pl.bills.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bills.entities.CategoryEntity;
import pl.bills.enums.CategoryEnum;
import pl.bills.repository.CategoryRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<Collection<CategoryEntity>> getAll() {
        return Optional.ofNullable(categoryRepository.findAll());
    }

    public Optional<CategoryEntity> getDefaultCategory() {
        return categoryRepository.findByName(CategoryEnum.MAIN.get());
    }

    public Optional<CategoryEntity> getCategory(String categoryName) {
        return categoryRepository.findByName(categoryName);
    }
}

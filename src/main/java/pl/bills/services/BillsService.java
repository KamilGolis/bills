package pl.bills.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bills.entities.BillsEntity;
import pl.bills.entities.CategoryEntity;
import pl.bills.repository.BillsRepository;
import pl.bills.repository.CategoryRepository;

import java.util.Collection;

/**
 * Created by trot on 09.01.17.
 */
@Service
public class BillsService {

    @Autowired
    BillsRepository billsRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public Collection<BillsEntity> getBills() {
        return billsRepository.findAllByCategoryName("main");
    }

    public boolean removeBill(Integer id) {
        BillsEntity bill = billsRepository.findById(id);
        if (bill != null) {
            CategoryEntity category = categoryRepository.findByName("trash");
            if(category==null) {
                CategoryEntity newCategory = new CategoryEntity();
                newCategory.setName("trash");
                categoryRepository.save(newCategory);
                category = categoryRepository.findByName("trash");
            }
            bill.setCategory(category);
            billsRepository.save(bill);
            return true;
        } else return false;
    }

    public Collection<BillsEntity> search(String searchValue) {
        Collection<BillsEntity> billsEntities = billsRepository.findAllByTitle(searchValue);
        if (billsEntities.isEmpty()) {
            billsEntities = billsRepository.findAllByStatusName(searchValue);
            if (billsEntities.isEmpty()) {
                billsEntities = billsRepository.findAllByLoanHolderName(searchValue);
            }
        }
        return billsEntities;
    }
}

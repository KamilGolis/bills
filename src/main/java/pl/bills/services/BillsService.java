package pl.bills.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bills.entities.BillsEntity;
import pl.bills.entities.CategoryEntity;
import pl.bills.entities.StatusEntity;
import pl.bills.forms.AddRecordForm;
import pl.bills.repository.BillsRepository;
import pl.bills.repository.CategoryRepository;
import pl.bills.repository.StatusRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by trot on 09.01.17.
 */
@Service
public class BillsService {

    @Autowired
    BillsRepository billsRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    StatusRepository statusRepository;

    public Collection<BillsEntity> getBills() {
        return billsRepository.findAllByCategoryName("main");
    }

    public boolean removeBill(Integer id) {
        BillsEntity bill = billsRepository.findById(id);
        if (bill != null) {
            CategoryEntity category = categoryRepository.findByName("trash");
            if (category == null) {
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

    public void addBillFromForm(AddRecordForm form) {
        billsRepository.save(createBillFromForm(form));
    }

    private BillsEntity createBillFromForm(AddRecordForm form) {
        BillsEntity bills = new BillsEntity();

        bills.setTitle(form.getTitle());
        bills.setComment(form.getComment());
        bills.setPrice(form.getPrice());

        CategoryEntity category = categoryRepository.findByName("main");
        if (category == null) {
            category.setName("main");
            categoryRepository.save(category);
        }
        bills.setCategory(category);

        StatusEntity status = statusRepository.findByName(form.getStatus());
        if (status != null) {
            bills.setStatus(status);
        } else {
            status.setName(form.getStatus());
            statusRepository.save(status);
        }

        return bills;
    }

    public String totalBillsPrice() {
        return billsRepository.findAllByCategoryName("main")
                .stream()
                .map(x -> x.getPrice())
                .reduce((x, y) -> x.add(y))
                .get().toString() + " PLN";
    }

    public String biggestBillPrice() {
        BillsEntity be = billsRepository.findAllByCategoryName("main").stream()
                .max(Comparator.comparing(i -> i.getPrice()))
                .get();
        return be.getTitle() + " " + be.getPrice() + " PLN";
    }

    public String mostFrequentBill() {

        return "Not implemented yet.";
    }
}

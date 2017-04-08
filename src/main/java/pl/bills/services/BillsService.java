package pl.bills.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bills.entities.BillsEntity;
import pl.bills.entities.CategoryEntity;
import pl.bills.enums.CategoryEnum;
import pl.bills.repository.BillsRepository;
import pl.bills.repository.CategoryRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by trot on 09.01.17.
 */
@Service
public class BillsService {

    private BillsRepository billsRepository;

    private CategoryRepository categoryRepository;

    @Autowired
    public BillsService(BillsRepository billsRepository, CategoryRepository categoryRepository) {
        this.billsRepository = billsRepository;
        this.categoryRepository = categoryRepository;
    }

    public Optional<Collection<BillsEntity>> getBills() {
        return Optional.ofNullable(billsRepository.findAll()
                .stream()
                .filter(b -> !b.getCategory().getName().equals(CategoryEnum.TRASH.get()))
                .sorted(Comparator.comparing(BillsEntity::getPrice))
                .collect(Collectors.toList()));
    }

    public Optional<BillsEntity> getOneBill(Integer id) {
        return billsRepository.findById(id);
    }

    public Optional<Collection<BillsEntity>> getDeletedBills() {
        return billsRepository.findAllByCategoryName(CategoryEnum.TRASH.get());
    }

    public void removeBill(Integer id) {
        Optional<BillsEntity> bill = billsRepository.findById(id);
        if (bill.isPresent()) {
            CategoryEntity category = categoryRepository.findByName(CategoryEnum.TRASH.get());
            if (category != null) {
                BillsEntity foundBill = bill.get();
                foundBill.setCategory(category);
                billsRepository.save(foundBill);
            }
        }
    }

    public void removeAllBills() {
        getBills().ifPresent(billsEntities -> billsEntities.forEach(bill -> removeBill(bill.getId())));
    }

    public void undoBill(Integer id) {
        billsRepository.findById(id).ifPresent(bill ->
                Optional.ofNullable(categoryRepository.findAllByName(CategoryEnum.MAIN.get())).ifPresent(category -> {
                    bill.setCategory((CategoryEntity) category);
                    billsRepository.save(bill);
                })
        );
    }

    public void deleteBill(Integer id) {
        if (billsRepository.findById(id).isPresent()) {
            billsRepository.delete(id);
        }
    }

    public Optional<Collection<BillsEntity>> search(String searchValue) {
        Optional<Collection<BillsEntity>> billsEntities = billsRepository.findAllByTitle(searchValue);
        if (!billsEntities.isPresent()) {
            billsEntities = billsRepository.findAllByStatusName(searchValue);
            if (!billsEntities.isPresent()) {
                billsEntities = billsRepository.findAllByLoanHolderName(searchValue);
            }
        }
        return billsEntities;
    }

    public void addBill(BillsEntity billsEntity) {
        billsRepository.save(billsEntity);
    }
}

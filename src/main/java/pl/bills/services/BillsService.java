package pl.bills.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bills.entities.BillsEntity;
import pl.bills.entities.CategoryEntity;
import pl.bills.enums.CategoryEnum;
import pl.bills.other.AuthenticationFacade;
import pl.bills.repository.BillsRepository;
import pl.bills.repository.CategoryRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class BillsService {

    private final BillsRepository billsRepository;
    private final CategoryRepository categoryRepository;
    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public BillsService(BillsRepository billsRepository, CategoryRepository categoryRepository, AuthenticationFacade authenticationFacade) {
        this.billsRepository = billsRepository;
        this.categoryRepository = categoryRepository;
        this.authenticationFacade = authenticationFacade;
    }

    public Optional<Collection<BillsEntity>> getBills() {
        return billsRepository
                .findAllByCategoryNameAndUserId(CategoryEnum.MAIN.get(), authenticationFacade.getCurrentUser().getId());
    }

    public Optional<BillsEntity> getOneBill(Integer id) {
        return billsRepository.findByIdAndUserId(id, authenticationFacade.getCurrentUser().getId());
    }

    public Optional<Collection<BillsEntity>> getDeletedBills() {
        return billsRepository
                .findAllByCategoryNameAndUserId(CategoryEnum.TRASH.get(), authenticationFacade.getCurrentUser().getId());
    }

    public void removeBill(Integer id) {
        Optional<BillsEntity> bill = billsRepository.findById(id);
        if (bill.isPresent()) {
            Optional<CategoryEntity> category = categoryRepository.findByName(CategoryEnum.TRASH.get());
            if (category.isPresent()) {
                BillsEntity foundBill = bill.get();
                foundBill.setCategory(category.get());
                billsRepository.save(foundBill);
            }
        }
    }

    @Transactional
    public void removeAllBills() {
        getBills().ifPresent(billsEntities -> billsEntities.forEach(bill -> removeBill(bill.getId())));
    }

    public void undoBill(Integer id) {
        billsRepository.findByIdAndUserId(id, authenticationFacade.getCurrentUser().getId())
                .ifPresent(bill ->
                        categoryRepository.findByName(CategoryEnum.MAIN.get())
                                .ifPresent(category -> {
                                    bill.setCategory(category);
                                    billsRepository.save(bill);
                                })
                );
    }

    public void deleteBill(Integer id) {
        billsRepository.findByIdAndUserId(id, authenticationFacade.getCurrentUser().getId())
                .ifPresent(billsRepository::delete);
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

    @Transactional
    public void addBill(BillsEntity billsEntity) {
        billsRepository.save(billsEntity);
    }

}

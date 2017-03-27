package pl.bills.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bills.entities.BillDTO;
import pl.bills.entities.BillsEntity;
import pl.bills.entities.CategoryEntity;
import pl.bills.enums.CategoryEnum;
import pl.bills.repository.BillsRepository;
import pl.bills.repository.CategoryRepository;
import pl.bills.repository.LoanHolderRepository;
import pl.bills.repository.StatusRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
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

    @Autowired
    LoanHolderRepository loanHolderRepository;

    @Autowired
    CountingServices countingServices;

    public Collection<BillDTO> getBills() {
        return billsRepository.findAll()
                .stream()
                .filter(b -> !b.getCategory().getName().equals(CategoryEnum.TRASH.get()))
                .sorted(Comparator.comparing(BillsEntity::getPrice))
                .map(this::getBillDTO)
                .collect(Collectors.toList());
    }

    private BillDTO getBillDTO(BillsEntity billsEntity) {
        BillDTO billDTO = new BillDTO();
        billDTO.setId(billsEntity.getId());
        billDTO.setTitle(billsEntity.getTitle());
        billDTO.setCategory(categoryRepository.findOne(billsEntity.getCategory().getCategoryId()));
        billDTO.setComment(billsEntity.getComment());
        billDTO.setDate(billsEntity.getDate());
        billDTO.setLoanHolder(loanHolderRepository.findOne(billsEntity.getLoanHolder().getLoanHolderId()));
        billDTO.setPrice(billsEntity.getPrice());
        billDTO.setStatus(statusRepository.getOne(billsEntity.getStatus().getId()));
        return billDTO;
    }

    public BillsEntity getOneBill(Integer id) {
        return billsRepository.findById(id);
    }

    public Collection<BillsEntity> getDeletedBills() {
        return billsRepository.findAllByCategoryName(CategoryEnum.TRASH.get());
    }

    public boolean removeBill(Integer id) {
        BillsEntity bill = billsRepository.findById(id);

        if (bill != null) {
            CategoryEntity category = categoryRepository.findByName(CategoryEnum.TRASH.get());
            if (category != null) {
                bill.setCategory(category);
                billsRepository.save(bill);
            }
            return true;
        }
        else return false;
    }

    public void removeAllBills() {
        getBills().forEach(b -> removeBill(b.getId()));
    }

    public void undoBill(Integer id) {
        Optional.ofNullable(billsRepository.findById(id)).ifPresent(bill ->
        Optional.ofNullable(categoryRepository.findAllByName(CategoryEnum.MAIN.get())).ifPresent(category -> {
                bill.setCategory((CategoryEntity) category);
                billsRepository.save(bill);
            })
        );
    }

    public void deleteBill(Integer id) {
        if (billsRepository.findById(id) != null) {
            billsRepository.delete(id);
        }
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

    public void addBill(BillsEntity billsEntity) {
        billsRepository.save(billsEntity);
    }

}

package pl.bills.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bills.entities.BillsEntity;
import pl.bills.entities.CategoryEntity;
import pl.bills.entities.StatusEntity;
import pl.bills.enums.CategoryEnum;
import pl.bills.forms.RecordForm;
import pl.bills.repository.BillsRepository;
import pl.bills.repository.CategoryRepository;
import pl.bills.repository.StatusRepository;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Locale;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

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

    public Collection<RecordForm> getBills() {
        return convertEntityListToRecords(billsRepository.findAllByCategoryName(CategoryEnum.MAIN.get()));
    }

    public RecordForm getOneBill(Integer id) {
        return convertEntityToRecord(billsRepository.findById(id));
    }

    public Collection<RecordForm> getDeletedBills() {
        return convertEntityListToRecords(billsRepository.findAllByCategoryName(CategoryEnum.TRASH.get()));
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
        } else return false;
    }

    public void undoBill(Integer id) {
        BillsEntity bill = billsRepository.findById(id);
        if (bill != null) {
            CategoryEntity category = categoryRepository.findByName(CategoryEnum.MAIN.get());
            if (category != null) {
                bill.setCategory(category);
                billsRepository.save(bill);
            }
        }
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

    public void addBillFromForm(RecordForm form) {
        billsRepository.save(createBillFromForm(form));
    }

    private Collection<RecordForm> convertEntityListToRecords(Collection<BillsEntity> billsEntities) {
        Collection<RecordForm> collection = new ArrayList<>();
        for (BillsEntity bill : billsEntities) {
            collection.add(convertEntityToRecord(bill));
        }
        return collection;
    }

    private BillsEntity createBillFromForm(RecordForm form) {
        BillsEntity bills = new BillsEntity();

        bills.setTitle(form.getTitle());
        bills.setComment(form.getComment());
        try {
            bills.setPrice(convertPrice(form.getPrice()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        CategoryEntity category = categoryRepository.findByName(CategoryEnum.MAIN.get());
        if (category == null) {
            category.setName(CategoryEnum.MAIN.get());
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

        if (form.getId() != null) {
            bills.setId(form.getId());
        }
        return bills;
    }

    private RecordForm convertEntityToRecord(BillsEntity bill) {
        RecordForm form = new RecordForm();
        form.setTitle(bill.getTitle());
        form.setStatus(bill.getStatus().getName());
        form.setStatusColour(bill.getStatus().getStatusColour());
        form.setComment(bill.getComment());
        form.setPrice(convertPrice(bill.getPrice()));
        form.setId(bill.getId());
        return form;
    }

    private String convertPrice(BigDecimal value) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        return formatter.format(value);
    }

    private BigDecimal convertPrice(String value) throws ParseException {
            DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance();
            df.setParseBigDecimal(true);
            BigDecimal bd = (BigDecimal) df.parseObject(value);
            return bd;
    }

    public String totalBillsPrice() {
        return billsRepository.findAllByCategoryName(CategoryEnum.MAIN.get())
                .stream()
                .map(x -> (x.getPrice() == null ? BigDecimal.ZERO : x.getPrice()))
                .reduce((x, y) -> x.add(y))
                .get().toString() + " PLN";
    }

    public String biggestBillPrice() {
        BillsEntity be = billsRepository.findAllByCategoryName(CategoryEnum.MAIN.get()).stream()
                .max(Comparator.comparing(i -> (i.getPrice() == null ? BigDecimal.ZERO : i.getPrice())))
                .get();
        return be.toString();
    }

    public String mostFrequentBill() {
        String counter = billsRepository.findAllByCategoryName(CategoryEnum.MAIN.get())
                .stream()
                .collect(groupingBy(p -> p.getTitle(), counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(i -> i.getValue()))
                .get().getKey();
        return counter;
    }

}

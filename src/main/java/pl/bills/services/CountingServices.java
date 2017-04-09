package pl.bills.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bills.dto.CountingDataDTO;
import pl.bills.entities.BillsEntity;
import pl.bills.enums.CategoryEnum;
import pl.bills.repository.BillsRepository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Created by trot on 28.01.17.
 */
@Service
public class CountingServices {

    private BillsRepository billsRepository;

    @Autowired
    public CountingServices(BillsRepository billsRepository) {
        this.billsRepository = billsRepository;
    }

    private BigDecimal totalBillsPrice() {
        Optional<Collection<BillsEntity>> bills = billsRepository.findAllByCategoryName(CategoryEnum.MAIN.get());
        if (bills.isPresent()) {
            return bills.map(billsEntities -> billsEntities.stream()
                    .map(BillsEntity::getPrice)
                    .reduce(BigDecimal::add)
                    .get())
                    .orElse(BigDecimal.ZERO);
        }
        return BigDecimal.ZERO;
    }

    private BigDecimal biggestBillPrice() {
        Optional<Collection<BillsEntity>> bills = billsRepository.findAllByCategoryName(CategoryEnum.MAIN.get());
        if (bills.isPresent()) {
            return bills.map(billsEntities -> billsEntities.stream()
                    .max(Comparator.comparing(BillsEntity::getPrice))
                    .get()
                    .getPrice())
                    .orElse(BigDecimal.ZERO);
        }
        return BigDecimal.ZERO;
    }

    private String mostFrequentBill() {
        Optional<Collection<BillsEntity>> bills = billsRepository.findAllByCategoryName(CategoryEnum.MAIN.get());
        if (bills.isPresent()) {
            return bills.map(billsEntities -> billsEntities
                    .stream()
                    .collect(groupingBy(BillsEntity::getTitle, counting()))
                    .entrySet()
                    .stream()
                    .max(Comparator.comparing(Map.Entry::getValue))
                    .get().getKey())
                    .orElse("");
        }
        return "";
    }

    public CountingDataDTO getAllTotals() {
        BigDecimal totalCost = totalBillsPrice();
        BigDecimal biggestBillCost = biggestBillPrice();
        String mostFrequentBill = mostFrequentBill();
        CountingDataDTO dataDTO = new CountingDataDTO(biggestBillCost, totalCost, mostFrequentBill);
        return dataDTO;
    }
}

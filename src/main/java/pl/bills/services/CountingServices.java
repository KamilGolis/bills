package pl.bills.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bills.entities.BillsEntity;
import pl.bills.enums.CategoryEnum;
import pl.bills.repository.BillsRepository;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Locale;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Created by trot on 28.01.17.
 */
@Service
public class CountingServices {

    @Autowired
    BillsRepository billsRepository;

    public BigDecimal totalBillsPrice() {
        return billsRepository.findAllByCategoryName(CategoryEnum.MAIN.get())
                .stream()
                .map(x -> (x.getPrice() == null ? BigDecimal.ZERO : x.getPrice()))
                .reduce((x, y) -> x.add(y))
                .orElse(BigDecimal.ZERO);
    }

    public BigDecimal biggestBillPrice() {
        if (billsRepository.findAllByCategoryName(CategoryEnum.MAIN.get()).isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return billsRepository.findAllByCategoryName(CategoryEnum.MAIN.get()).stream()
                    .max(Comparator.comparing(BillsEntity::getPrice))
                    .get()
                    .getPrice();
        }
    }

    public String mostFrequentBill() {
        if (billsRepository.findAllByCategoryName(CategoryEnum.MAIN.get()).isEmpty()) {
            return "Brak wyników";
        } else {
            return billsRepository.findAllByCategoryName(CategoryEnum.MAIN.get())
                    .stream()
                    .collect(groupingBy(p -> p.getTitle(), counting()))
                    .entrySet()
                    .stream()
                    .max(Comparator.comparing(i -> i.getValue()))
                    .get().getKey();
        }
    }

}

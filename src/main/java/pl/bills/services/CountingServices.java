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

    public String totalBillsPrice() {
        return billsRepository.findAllByCategoryName(CategoryEnum.MAIN.get())
                .stream()
                .map(x -> (x.getPrice() == null ? BigDecimal.ZERO : x.getPrice()))
                .reduce((x, y) -> x.add(y))
                .map(b -> convertPrice(b))
                .get();
    }

    public String biggestBillPrice() {
        BillsEntity be = billsRepository.findAllByCategoryName(CategoryEnum.MAIN.get()).stream()
                .max(Comparator.comparing(i -> (i.getPrice() == null ? BigDecimal.ZERO : i.getPrice())))
                .get();
        return convertPrice(be.getPrice());
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

    public String convertPrice(BigDecimal value) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        return formatter.format(value);
    }

    public BigDecimal convertPrice(String value) throws ParseException {
        DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance();
        df.setParseBigDecimal(true);
        BigDecimal bd = (BigDecimal) df.parseObject(value);
        return bd;
    }
}

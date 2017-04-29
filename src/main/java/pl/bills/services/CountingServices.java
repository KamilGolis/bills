package pl.bills.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bills.entities.BillsEntity;
import pl.bills.enums.CategoryEnum;
import pl.bills.other.AuthenticationFacade;
import pl.bills.repository.BillsRepository;

import java.math.BigDecimal;
import java.util.*;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Service
public class CountingServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountingServices.class);

    private final BillsRepository billsRepository;
    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public CountingServices(BillsRepository billsRepository, AuthenticationFacade authenticationFacade) {
        this.billsRepository = billsRepository;
        this.authenticationFacade = authenticationFacade;
    }

    public BigDecimal totalBillsPrice() {
        Optional<Collection<BillsEntity>> bills = getAllBillsByCategoryAndUser();
        if (bills.isPresent() && !bills.get().isEmpty()) {
            BigDecimal result = bills.map(billsEntities -> billsEntities.stream()
                    .map(BillsEntity::getPrice)
                    .reduce(BigDecimal::add)
                    .get())
                    .orElse(BigDecimal.ZERO);
            LOGGER.info("Total bills cost is {}", result);
            return result;
        }
        LOGGER.info("No bills, no cost.");
        return BigDecimal.ZERO;
    }

    public BigDecimal biggestBillPrice() {
        Optional<Collection<BillsEntity>> bills = getAllBillsByCategoryAndUser();
        if (bills.isPresent() && !bills.get().isEmpty()) {
            BigDecimal result = bills.map(billsEntities -> billsEntities.stream()
                    .max(Comparator.comparing(BillsEntity::getPrice))
                    .get()
                    .getPrice())
                    .orElse(BigDecimal.ZERO);
            LOGGER.info("Biggest bills cost is {}", result);
            return result;
        }
        LOGGER.info("No bills, no biggest cost.");
        return BigDecimal.ZERO;
    }

    public String mostFrequentBill() {
        Optional<Collection<BillsEntity>> bills = getAllBillsByCategoryAndUser();
        if (bills.isPresent() && !bills.get().isEmpty()) {
            String result = bills.map(billsEntities -> billsEntities
                    .stream()
                    .collect(groupingBy(BillsEntity::getTitle, counting()))
                    .entrySet()
                    .stream()
                    .max(Comparator.comparing(Map.Entry::getValue))
                    .get().getKey())
                    .orElse("");
            LOGGER.info("Most frequent bill is {}", result);
            return result;
        }
        LOGGER.info("No frequent bill.");
        return "";
    }

    private Optional<Collection<BillsEntity>> getAllBillsByCategoryAndUser() {
        LOGGER.info("Getting bills for counting.");
        return billsRepository
                .findAllByCategoryNameAndUserId(CategoryEnum.MAIN.get(), authenticationFacade.getCurrentUser().getId());
    }
}

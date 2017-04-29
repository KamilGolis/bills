package pl.bills.converters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import pl.bills.entities.StatusEntity;
import pl.bills.services.StatusService;

import java.text.ParseException;
import java.util.Locale;
import java.util.NoSuchElementException;

@Component
public class StatusFormatter implements Formatter<StatusEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusFormatter.class);

    @Autowired
    private StatusService statusService;

    @Override
    public StatusEntity parse(String statusName, Locale locale) throws ParseException {
        LOGGER.info("Converting status={} to status entity", statusName);
        return statusService.getStatus(statusName)
                .orElseThrow(() -> new NoSuchElementException(String.format("Status=%s was not found", statusName)));
    }

    @Override
    public String print(StatusEntity status, Locale locale) {
        LOGGER.info("Converting status entity to string");
        return status.getName();
    }
}

package pl.bills.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import pl.bills.entities.StatusEntity;
import pl.bills.services.StatusService;

import java.text.ParseException;
import java.util.Locale;

/**
 * Created by trot on 04.02.17.
 */
@Component
public class StatusFormatter implements Formatter<StatusEntity> {

    @Autowired
    StatusService statusService;

    @Override
    public StatusEntity parse(String statusName, Locale locale) throws ParseException {
        return statusService.getStatus(statusName);
    }

    @Override
    public String print(StatusEntity status, Locale locale) {
        return status.getName();
    }
}

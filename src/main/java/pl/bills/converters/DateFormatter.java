package pl.bills.converters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by trot on 10.02.17.
 */
@Component
public class DateFormatter implements Formatter<Date> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateFormatter.class);

    public DateFormatter() {
        super();
    }

    @Override
    public Date parse(final String text, final Locale locale) throws ParseException {
        LOGGER.info("Converting date to lacal date");
        return createDateFormat(locale).parse(text);
    }

    @Override
    public String print(final Date object, final Locale locale) {
        return createDateFormat(locale).format(object);
    }

    private SimpleDateFormat createDateFormat(final Locale locale) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);
        return dateFormat;
    }

}

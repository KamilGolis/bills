package pl.bills.converters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by trot on 10.02.17.
 */
@Component
public class PriceFormatter implements Formatter<BigDecimal> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PriceFormatter.class);

    public PriceFormatter() {
        super();
    }

    @Override
    public BigDecimal parse(String text, Locale locale) throws ParseException {
        LOGGER.info("Converting price=%s to big decimal", text);
        text = text.replaceAll(" ", "");
        text = text.replaceAll(",", ".");

        DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance();
        df.setParseBigDecimal(true);
        BigDecimal bd = BigDecimal.ZERO;
        try {
            return (BigDecimal) df.parseObject(text);
        } catch (ParseException e) {
            LOGGER.info("Price converter -> converting null to BigDecimal.ZERO.");
        }
        return bd == null ? BigDecimal.ZERO : bd;

    }

    @Override
    public String print(BigDecimal object, Locale locale) {
        return NumberFormat.getCurrencyInstance().format(object);
    }
}

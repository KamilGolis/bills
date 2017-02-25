package pl.bills.converters;

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

    public PriceFormatter() {
        super();
    }

    @Override
    public BigDecimal parse(String text, Locale locale) throws ParseException {
        text = text.replaceAll(" ", "");
        DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance();
        df.setParseBigDecimal(true);
        BigDecimal bd = BigDecimal.ZERO;
        try {
            bd = (BigDecimal) df.parseObject(text);
        } catch (ParseException e) {
            System.err.println("Price converter -> converting null to BigDecimal.ZERO.");
            ;
        } finally {
            return bd == null ? BigDecimal.ZERO : bd;
        }
    }

    @Override
    public String print(BigDecimal object, Locale locale) {
//        Currency currency = Currency.getInstance("PL");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        return numberFormat.format(object);
    }
}

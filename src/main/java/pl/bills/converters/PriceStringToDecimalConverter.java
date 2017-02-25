//package pl.bills.converters;
//
//import org.springframework.core.convert.converter.Converter;
//
//import java.math.BigDecimal;
//import java.text.DecimalFormat;
//import java.text.NumberFormat;
//import java.text.ParseException;
//
///**
// * Created by trot on 03.02.17.
// */
//public class PriceStringToDecimalConverter implements Converter<String, BigDecimal> {
//
//    @Override
//    public BigDecimal convert(String source) {
//        DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance();
//        df.setParseBigDecimal(true);
//        BigDecimal bd = null;
//        try {
//            bd = (BigDecimal) df.parseObject(source);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return bd;
//    }
//}

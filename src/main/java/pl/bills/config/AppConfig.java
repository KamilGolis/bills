package pl.bills.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import pl.bills.converters.LocalDateConverter;
import pl.bills.converters.PriceConverter;
import pl.bills.converters.StatusConverter;

/**
 * Created by trot on 03.02.17.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class AppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new LocalDateConverter("dd.MM.yyyy"));
        registry.addConverter(new StatusConverter());
        registry.addConverter(new PriceConverter());
    }
}

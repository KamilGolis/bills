package pl.bills.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import pl.bills.converters.*;

import java.util.Arrays;

/**
 * Created by trot on 03.02.17.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class AppConfig extends WebMvcConfigurerAdapter {

    private final Formatter[] formatters = {
            new PriceFormatter(),
            new DateFormatter(),
            new StatusFormatter(),
            new CategoryFormatter(),
            new LoanHolderFormatter()
    };

    @Override
    public void addFormatters(FormatterRegistry registry) {
        Arrays.stream(formatters).forEach(registry::addFormatter);
    }

}

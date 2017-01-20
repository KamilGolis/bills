package pl.bills.config;

import com.github.dandelion.core.web.DandelionFilter;
import com.github.dandelion.core.web.DandelionServlet;
import com.github.dandelion.datatables.thymeleaf.dialect.DataTablesDialect;
import com.github.dandelion.thymeleaf.dialect.DandelionDialect;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by trot on 18.01.17.
 */
@Configuration
public class DandelionConfig {
    @Bean
    public DandelionDialect dandelionDialect() {
        return new DandelionDialect();
    }

    @Bean
    public DataTablesDialect dataTablesDialect(){
        return new DataTablesDialect();
    }

    @Bean
    public DandelionFilter dandelionFilter() {
        return new DandelionFilter();
    }

    @Bean
    public ServletRegistrationBean dandelionServletRegistrationBean() {
        return new ServletRegistrationBean(new DandelionServlet(), "/dandelion-assets/*");
    }
}

package pl.bills;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Locale;

@EnableJpaRepositories(basePackages = {"pl.bills.repository"})
@SpringBootApplication(scanBasePackages = {"pl.bills.repository", "pl.bills.entities",
        "pl.bills.controllers", "pl.bills.services", "pl.bills.converters", "pl.bills.config",
        "pl.bills.dto"})
@EnableTransactionManagement
@EnableSwagger2
@EntityScan(basePackages = {"pl.bills.entities"})
public class BillsApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(BillsApplication.class, args);
//        SpringApplication.run(BillsApplication.class, args);

    }

    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }

    @Bean
    ObjectMapper customizeJacksonConfiguration() {
        ObjectMapper mapper = new ObjectMapper();
        Hibernate5Module hibernate5Module = new Hibernate5Module();
        hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);
        mapper.registerModule(hibernate5Module);
        return mapper;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(DataSource ds, EntityManagerFactory emf) {

        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setDataSource(ds);
        jpaTransactionManager.setEntityManagerFactory(emf);
        return jpaTransactionManager;
    }


    @Bean
    public Docket api(@Value("${info.name}") String projectName, @Value("${info.version}") String projectVersion) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("bills")
                .apiInfo(apiInfo(projectName, projectVersion))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(String projectName, String projectVersion) {
        return new ApiInfoBuilder()
                .title(projectName + ": SpringBoot REST API with Swagger")
                .version(projectVersion)
                .build();
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.ENGLISH);
        return slr;
    }

    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
        messageBundle.setBasename("classpath:messages/messages");
        messageBundle.setDefaultEncoding("UTF-8");
        return messageBundle;
    }
}
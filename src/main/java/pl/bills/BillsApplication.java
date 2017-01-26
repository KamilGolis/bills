package pl.bills;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BillsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillsApplication.class, args);

//        ApplicationContext ctx = SpringApplication.run(BillsApplication.class, args);
//        BillService billsService = ctx.getBean("billService", BillService.class);
//
//        String argument = "Zakupy";
//        System.out.println("Query : title="+argument);
//        billsService.getBillsByTitle(argument).stream().forEach(System.out::println);
//        argument = "Do zapłaty";
//        System.out.println("Query : status="+argument);
//        billsService.getBillsByStatus(argument).stream().forEach(System.out::println);
    }
}

/*

INSERT INTO bills (id, comment, price, title, category_id, loan_holder_id, status_id) VALUES (1, 'Kupno zmywarki', 2000, 'Zakupy', 1, 2, 4);
INSERT INTO bills (id, comment, price, title, category_id, loan_holder_id, status_id) VALUES (2, 'Tankowanie samochodu', 400, 'Paliwo', 1, 2, 4);
INSERT INTO bills (id, comment, price, title, category_id, loan_holder_id, status_id) VALUES (3, 'Czynsz - styczeń 2017', 450.43, 'Czynsz', 1, 1, 2);
INSERT INTO bills (id, comment, price, title, category_id, loan_holder_id, status_id) VALUES (4, 'Energia elektryczna - styczeń 2017', 110.32, 'Paliwo', 1, 2, 1);
INSERT INTO bills (id, comment, price, title, category_id, loan_holder_id, status_id) VALUES (5, 'Gaz - styczeń 2017', 23.55, 'Gaz', 1, 2, 1);
INSERT INTO bills (id, comment, price, title, category_id, loan_holder_id, status_id) VALUES (6, 'Kredyt - styczeń 2017', 987.26, 'Kredyt hipoteczny', 1, 2, 2);
INSERT INTO category (category_id, name) VALUES (1, 'main');
INSERT INTO category (category_id, name) VALUES (2, 'trash');
INSERT INTO loan_holders (loan_holder_id, address, bank_account_number, description, name) VALUES (1, 'ul. Felińskiego 8, 93-259 Łódź', '12111122223333444455556666', 'Administracja Aktus', 'Aktus');
INSERT INTO loan_holders (loan_holder_id, address, bank_account_number, description, name) VALUES (2, '-', '12666655554444333322221111', '-', 'Puste');
INSERT INTO status (status_id, name) VALUES (1, 'Zapłacone');
INSERT INTO status (status_id, name) VALUES (2, 'Do zapłaty');
INSERT INTO status (status_id, name) VALUES (3, 'Do wyjaśnienia');
INSERT INTO status (status_id, name) VALUES (4, 'Inne');

 */
INSERT INTO bills.category (category_id, name, icon) VALUES (1, 'Główna', 'glyphicon-star');
INSERT INTO bills.category (category_id, name, icon) VALUES (2, 'Kosz', 'glyphicon-trash');

INSERT INTO bills.status (status_id, name, status_colour) VALUES (1, 'Zapłacone', 'success');
INSERT INTO bills.status (status_id, name, status_colour) VALUES (2, 'Do zapłaty', 'danger');
INSERT INTO bills.status (status_id, name, status_colour) VALUES (3, 'Do wyjaśnienia', 'warning');
INSERT INTO bills.status (status_id, name, status_colour) VALUES (4, 'Inne', 'warning');

INSERT INTO bills.loan_holders (loan_holder_id, address, bank_account_number, description, name) VALUES (1, 'ul. Felińskiego 8, 93-259 Łódź', '12111122223333444455556666', 'Administracja Aktus', 'Aktus');
INSERT INTO bills.loan_holders (loan_holder_id, address, bank_account_number, description, name) VALUES (2, '-', '12666655554444333322221111', '-', 'Puste');

INSERT INTO bills.bills (id, title, comment, price, category_id, loan_holder_id, status_id) VALUES (1, 'Test title 01', 'Test comment 01', 100, 1, 1, 1);
INSERT INTO bills.bills (id, title, comment, price, category_id, loan_holder_id, status_id) VALUES (2, 'Test title 02', 'Test comment 02', 200, 1, 1, 1);
INSERT INTO bills.bills (id, title, comment, price, category_id, loan_holder_id, status_id) VALUES (3, 'Test title 03', 'Test comment 03', 300, 1, 1, 2);
INSERT INTO bills.bills (id, title, comment, price, category_id, loan_holder_id, status_id) VALUES (4, 'Test title 04', 'Test comment 04', 400, 1, 2, 3);
INSERT INTO bills.bills (id, title, comment, price, category_id, loan_holder_id, status_id) VALUES (5, 'Test title 05', 'Test comment 05', 500, 1, 2, 4);
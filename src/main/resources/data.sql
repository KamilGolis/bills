INSERT INTO app.category (category_id, name, icon) VALUES (1, 'Główna', 'glyphicon-star');
INSERT INTO app.category (category_id, name, icon) VALUES (2, 'Kosz', 'glyphicon-trash');

INSERT INTO app.status (status_id, name, status_colour) VALUES (10, 'Zapłacone', 'success');
INSERT INTO app.status (status_id, name, status_colour) VALUES (20, 'Do zapłaty', 'danger');
INSERT INTO app.status (status_id, name, status_colour) VALUES (30, 'Do wyjaśnienia', 'warning');
INSERT INTO app.status (status_id, name, status_colour) VALUES (40, 'Inne', 'warning');

INSERT INTO app.loan_holders (loan_holder_id, address, bank_account_number, description, name) VALUES (100, 'ul. Felińskiego 8, 93-259 Łódź', '12111122223333444455556666', 'Administracja Aktus', 'Aktus');
INSERT INTO app.loan_holders (loan_holder_id, address, bank_account_number, description, name) VALUES (200, '-', '12666655554444333322221111', '-', 'Puste');

INSERT INTO app.bills (id, title, comment, price, category_id, loan_holder_id, status_id) VALUES (1001, 'Test title 01', 'Test comment 01', 100, 1, 100, 10);
INSERT INTO app.bills (id, title, comment, price, category_id, loan_holder_id, status_id) VALUES (1002, 'Test title 02', 'Test comment 02', 200, 1, 100, 10);
INSERT INTO app.bills (id, title, comment, price, category_id, loan_holder_id, status_id) VALUES (1003, 'Test title 03', 'Test comment 03', 300, 1, 100, 20);
INSERT INTO app.bills (id, title, comment, price, category_id, loan_holder_id, status_id) VALUES (1004, 'Test title 04', 'Test comment 04', 400, 1, 200, 30);
INSERT INTO app.bills (id, title, comment, price, category_id, loan_holder_id, status_id) VALUES (1005, 'Test title 05', 'Test comment 05', 500, 1, 200, 40);
INSERT INTO sqlite_sequence (name, seq) VALUES ('bills', 1);

INSERT INTO category (name, icon) VALUES ('Główna', 'glyphicon-star');
INSERT INTO category (name, icon) VALUES ('Kosz', 'glyphicon-trash');

INSERT INTO status (name, status_colour) VALUES ('Zapłacone', 'success');
INSERT INTO status (name, status_colour) VALUES ('Do zapłaty', 'danger');
INSERT INTO status (name, status_colour) VALUES ('Do wyjaśnienia', 'warning');
INSERT INTO status (name, status_colour) VALUES ('Inne', 'warning');

INSERT INTO loan_holders (address, bank_account_number, description, name) VALUES ('ul. Felińskiego 8, 93-259 Łódź', '12111122223333444455556666', 'Administracja Aktus', 'Aktus');
INSERT INTO loan_holders (address, bank_account_number, description, name) VALUES ('-', '12666655554444333322221111', '-', 'Puste');

INSERT INTO bills (title, comment, price, category_id, loan_holder_id, status_id) VALUES ('Test title 01', 'Test comment 01', 100, 1, 1, 1);
INSERT INTO bills (title, comment, price, category_id, loan_holder_id, status_id) VALUES ('Test title 02', 'Test comment 02', 200, 1, 1, 1);
INSERT INTO bills (title, comment, price, category_id, loan_holder_id, status_id) VALUES ('Test title 03', 'Test comment 03', 300, 1, 1, 2);
INSERT INTO bills (title, comment, price, category_id, loan_holder_id, status_id) VALUES ('Test title 04', 'Test comment 04', 400, 1, 2, 3);
INSERT INTO bills (title, comment, price, category_id, loan_holder_id, status_id) VALUES ('Test title 05', 'Test comment 05', 500, 1, 2, 4);
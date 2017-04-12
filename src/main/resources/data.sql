INSERT INTO sqlite_sequence (name, seq) VALUES ('bills', 1);

INSERT INTO category (name, icon) VALUES ('Główna', 'glyphicon-star');
INSERT INTO category (name, icon) VALUES ('Kosz', 'glyphicon-trash');

INSERT INTO status (name, status_colour) VALUES ('Zapłacone', 'success');
INSERT INTO status (name, status_colour) VALUES ('Do zapłaty', 'danger');
INSERT INTO status (name, status_colour) VALUES ('Do wyjaśnienia', 'warning');
INSERT INTO status (name, status_colour) VALUES ('Inne', 'warning');

INSERT INTO loan_holders (address, bank_account_number, description, name) VALUES ('ul. Felińskiego 8, 93-259 Łódź', '12111122223333444455556666', 'Administracja Aktus', 'Aktus');
INSERT INTO loan_holders (address, bank_account_number, description, name) VALUES ('-', '12666655554444333322221111', '-', 'Puste');

INSERT INTO bills (id, comment, price, title, category_id, loan_holder_id, status_id) VALUES (1, 'Kupno zmywarki', 2000, 'Zakupy', 1, 2, 4);
INSERT INTO bills (id, comment, price, title, category_id, loan_holder_id, status_id) VALUES (2, 'Tankowanie samochodu', 400, 'Paliwo', 1, 2, 4);
INSERT INTO bills (id, comment, price, title, category_id, loan_holder_id, status_id) VALUES (3, 'Czynsz - styczeń 2017', 450.43, 'Czynsz', 1, 1, 2);
INSERT INTO bills (id, comment, price, title, category_id, loan_holder_id, status_id) VALUES (4, 'Energia elektryczna - styczeń 2017', 110.32, 'Paliwo', 1, 2, 1);
INSERT INTO bills (id, comment, price, title, category_id, loan_holder_id, status_id) VALUES (5, 'Gaz - styczeń 2017', 23.55, 'Gaz', 1, 2, 1);
INSERT INTO bills (id, comment, price, title, category_id, loan_holder_id, status_id) VALUES (6, 'Kredyt - styczeń 2017', 987.26, 'Kredyt hipoteczny', 1, 2, 2);

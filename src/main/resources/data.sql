INSERT INTO sqlite_sequence (name, seq) VALUES ('bills', 1);

INSERT INTO category (name, icon) VALUES ('Główna', 'glyphicon-star');
INSERT INTO category (name, icon) VALUES ('Kosz', 'glyphicon-trash');

INSERT INTO status (name, status_colour) VALUES ('Zapłacone', 'success');
INSERT INTO status (name, status_colour) VALUES ('Do zapłaty', 'danger');
INSERT INTO status (name, status_colour) VALUES ('Do wyjaśnienia', 'warning');
INSERT INTO status (name, status_colour) VALUES ('Inne', 'warning');

INSERT INTO loan_holders (address, bank_account_number, description, name) VALUES ('ul. Felińskiego 8, 93-259 Łódź', '12111122223333444455556666', 'Administracja Aktus', 'Aktus');
INSERT INTO loan_holders (address, bank_account_number, description, name) VALUES ('-', '12666655554444333322221111', '-', 'Puste');

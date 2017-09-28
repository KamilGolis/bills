INSERT INTO category (name, icon) VALUES ('Główna', 'glyphicon-star');
INSERT INTO category (name, icon) VALUES ('Kosz', 'glyphicon-trash');

INSERT INTO status (name, status_colour) VALUES ('Zapłacone', 'success');
INSERT INTO status (name, status_colour) VALUES ('Do zapłaty', 'danger');
INSERT INTO status (name, status_colour) VALUES ('Do wyjaśnienia', 'warning');
INSERT INTO status (name, status_colour) VALUES ('Inne', 'warning');

INSERT INTO loan_holders (address, bank_account_number, description, name) VALUES ('ul. Felińskiego 8, 93-259 Łódź', '12111122223333444455556666', 'Administracja Aktus', 'Aktus');
INSERT INTO loan_holders (address, bank_account_number, description, name) VALUES ('-', '12666655554444333322221111', '-', 'Puste');

INSERT INTO user (email, password_hash, role) VALUES ('demo@gmail.com', '$2a$10$ebyC4Z5WtCXXc.HGDc1Yoe6CLFzcntFmfse6/pTj7CeDY5I05w16C', 'ADMIN');
INSERT INTO user (email, password_hash, role) VALUES ('admin@admin.com', '$2a$10$hZ6zN/33EqBmV7APXgABYOVqEvCKZwMHH4qovDDHjtDuQ59HR8QtS', 'ADMIN');
INSERT INTO user (email, password_hash, role) VALUES ('test@test.com', '$2a$10$wQvQgpvWgCe.LE4yATZrZOy1lEhD3kBZ2Ie9DS5piqs7twHHWJakS', 'USER');
INSERT INTO user (email, password_hash, role) VALUES ('sda@sda.pl', '$2a$10$phykRx2lkDRzqkdeh.AXsuSV.tNQOBJTzwLJjxECjjjgFjY50A0qa', 'USER');
INSERT INTO user (email, password_hash, role) VALUES ('asdf@gmail.com', '$2a$10$Mq3BbxsV.cyroUxg7bKgbO.Gh9mJR1QhhfofVhzS.bYseBuJ/WLIS', 'USER');

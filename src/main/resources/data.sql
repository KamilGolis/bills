INSERT INTO category (category_id, name, icon) VALUES (1, 'Główna', 'glyphicon-star');
INSERT INTO category (category_id, name, icon) VALUES (2, 'Kosz', 'glyphicon-trash');

INSERT INTO status (status_id, name, status_colour) VALUES (1, 'Zapłacone', 'success');
INSERT INTO status (status_id, name, status_colour) VALUES (2, 'Do zapłaty', 'danger');
INSERT INTO status (status_id, name, status_colour) VALUES (3, 'Do wyjaśnienia', 'warning');
INSERT INTO status (status_id, name, status_colour) VALUES (4, 'Inne', 'warning');

INSERT INTO loan_holders (loan_holder_id, address, bank_account_number, description, name) VALUES (1, 'ul. Felińskiego 8, 93-259 Łódź', '12111122223333444455556666', 'Administracja Aktus', 'Aktus');
INSERT INTO loan_holders (loan_holder_id, address, bank_account_number, description, name) VALUES (2, '-', '12666655554444333322221111', '-', 'Puste');

INSERT INTO user (user_id, email, password_hash, role) VALUES (1, 'demo@gmail.com', '$2a$10$ebyC4Z5WtCXXc.HGDc1Yoe6CLFzcntFmfse6/pTj7CeDY5I05w16C', 'ADMIN');
INSERT INTO user (user_id, email, password_hash, role) VALUES (2, 'admin@admin.com', '$2a$10$hZ6zN/33EqBmV7APXgABYOVqEvCKZwMHH4qovDDHjtDuQ59HR8QtS', 'ADMIN');
INSERT INTO user (user_id, email, password_hash, role) VALUES (3, 'test@test.com', '$2a$10$wQvQgpvWgCe.LE4yATZrZOy1lEhD3kBZ2Ie9DS5piqs7twHHWJakS', 'USER');
INSERT INTO user (user_id, email, password_hash, role) VALUES (4, 'sda@sda.pl', '$2a$10$phykRx2lkDRzqkdeh.AXsuSV.tNQOBJTzwLJjxECjjjgFjY50A0qa', 'USER');
INSERT INTO user (user_id, email, password_hash, role) VALUES (5, 'asdf@gmail.com', '$2a$10$Mq3BbxsV.cyroUxg7bKgbO.Gh9mJR1QhhfofVhzS.bYseBuJ/WLIS', 'USER');

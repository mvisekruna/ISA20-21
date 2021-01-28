-- Lozinke su hesovane pomocu BCrypt algoritma https://www.dailycred.com/article/bcrypt-calculator
-- Lozinka za oba user-a je 123
INSERT INTO AUTHORITY (name) VALUES ('ROLE_PATIENT');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_PHARMACIST');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_DERMATOLOGIST');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_PHARMACY_ADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_SUPPLIER');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_SYSTEM_ADMIN');

INSERT INTO USERS (password, first_name, last_name, email, home_address, city, state, phone_number,  enabled, last_password_reset_date, authority_id) VALUES ('$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Milica', 'Visekruna', 'user@example.com', 'Gavrila Principa 6', 'Backa Palanka', 'Srbija', '0649477321', true, '2020-12-17', 1);
INSERT INTO USERS (password, first_name, last_name, email, home_address, city, state, phone_number,  enabled, last_password_reset_date, authority_id) VALUES ('$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Tijana', 'Mitric', 'admin@example.com', 'Znam Da Je Neki Blok', 'Sremska Mitrovica', 'Srbija', '064123456', true, '2020-12-17', 6);
INSERT INTO USERS (password, first_name, last_name, email, home_address, city, state, phone_number,  enabled, last_password_reset_date, authority_id) VALUES ('$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Tijana', 'Mitric', 'pacijent@example.com', 'Znam Da Je Neki Blok', 'Sremska Mitrovica', 'Srbija', '064123456', true, '2020-12-17', 1);
INSERT INTO USERS (password, first_name, last_name, email, home_address, city, state, phone_number,  enabled, last_password_reset_date, authority_id) VALUES ('$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Milica', 'Visekruna', 'dermatolog@example.com', 'Gavrila Principa 6', 'Backa Palanka', 'Srbija', '0649477321', true, '2020-12-17', 3);
INSERT INTO USERS (password, first_name, last_name, email, home_address, city, state, phone_number,  enabled, last_password_reset_date, authority_id) VALUES ('$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Milica', 'Visekruna', 'adminapoteke@example.com', 'Gavrila Principa 6', 'Backa Palanka', 'Srbija', '0649477321', true, '2020-12-17', 4);
INSERT INTO USERS (password, first_name, last_name, email, home_address, city, state, phone_number,  enabled, last_password_reset_date, authority_id) VALUES ('$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Admin', 'Apoteke', 'pharmacyAdmin1@example.com', 'Ulica 1', 'Grad', 'Srbija', '064123456', true, '2020-12-17', 4);
INSERT INTO USERS (password, first_name, last_name, email, home_address, city, state, phone_number,  enabled, last_password_reset_date, authority_id) VALUES ('$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Admin', 'Apoteke', 'farmaceut@example.com', 'Ulica 1', 'Grad', 'Srbija', '064123456', true, '2020-12-17', 2);
INSERT INTO USERS (password, first_name, last_name, email, home_address, city, state, phone_number,  enabled, last_password_reset_date, authority_id) VALUES ('$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Ime', 'Prezime', 'farmaceut2@example.com', 'Ulica 2', 'Grad', 'Srbija', '064123456', true, '2020-12-17', 2);




INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (1, 1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (2, 6);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (3, 1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (4, 3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (5, 4);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (6, 4);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (7, 2);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (8, 2);

INSERT INTO PHARMACY_PHARMACISTS (pharmacy_id, pharmacists_id) VALUES (1,7);
INSERT INTO PHARMACY_PHARMACISTS (pharmacy_id, pharmacists_id) VALUES (1,8);
INSERT INTO EXAMINATION (dermatologist_id, patient_id, pharmacy_id) VALUES (4, 3, 1);
INSERT INTO MEDICINE_RESERVATION (medicine_id, pharmacy_id, patient_id) VALUES (1, 1, 3);




INSERT INTO MEDICINE (name) VALUES ('Bensedin');
INSERT INTO MEDICINE (name) VALUES ('Febricet');
INSERT INTO MEDICINE (name) VALUES ('Aspirin');
INSERT INTO MEDICINE (name) VALUES ('Ferveks');

INSERT INTO PHARMACY (name, admin_apoteke_id) VALUES ('Apoteka1', 5);
INSERT INTO PHARMACY (name, admin_apoteke_id) VALUES ('Apoteka2', 6);

INSERT INTO PHARMACY_MEDICINE (pharmacy_id, medicine_id) VALUES (1, 1);
INSERT INTO PHARMACY_MEDICINE (pharmacy_id, medicine_id) VALUES (1, 2);
INSERT INTO PHARMACY_MEDICINE (pharmacy_id, medicine_id) VALUES (1, 3);


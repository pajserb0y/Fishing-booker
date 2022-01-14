SET datestyle = dmy;
insert into role (name) values ('ROLE_CUSTOMER');
insert into role (name) values ('ROLE_ADMIN');
insert into role (name) values ('ROLE_BOAT_OWNER');
insert into role (name) values ('ROLE_INSTRUCTOR');
insert into role (name) values ('ROLE_WEEKEND_HOUSE_OWNER');

insert into customer (address, city, country, email, first_name, is_activated, is_deleted, last_name, password, phone, username, want_deleting, hash_code, penals, role_id)
    values ('Presernova 3', 'Bled', 'Slovenia', 'health.care.clinic.psw+milica@gmail.com', 'Milica', true, false, 'Antic', '$2a$10$hd.PcSgRocME1rIrpLhXWOO/uKacPl4oyjf3k5DGHaBhesm6wC3SK', '0651234432', 'milica', false, '', 2, 1);
insert into customer (address, city, country, email, first_name, is_activated, is_deleted, last_name, password, phone, username, want_deleting, hash_code, penals, role_id)
    values ('Masarikova 23', 'Novi Sad', 'Serbia', 'health.care.clinic.psw+predrag@gmail.com', 'Predrag', true, false, 'Miric', '$2a$10$l.fpbmzdA7ooTKv5nb5vheG7fX5lN2jxVQMUdsANPpVpGR4q7fDvy', '0656544432', 'predrag', false, '', 4, 1);


insert into weekend_house_owner (address, city, country, email, first_name, is_activated, is_deleted, last_name, password, phone, username, want_deleting, motive, role_id)
    values ('Makarska 42', 'Beograd', 'Serbia', 'health.care.clinic.psw+antonije@gmail.com', 'Antonije', true, false, 'Pusic', '$2a$10$tfmCyLyvVUpTAqmApmZwUej3aT3WiUVOQu8sx1/dk0AE3LHrqJVnC', '0654677567', 'antonije', false, '', 5);


insert into weekend_house (address, bed_number, description, image_path, name, price, rules, weekend_house_owner_id)
    values ('Lovcenska 23', 5, 'Mnogo lepa vikendica', '123edas123', 'Heaven house', 1235, 'Be good', 1);

insert into weekend_house (address, bed_number, description, image_path, name, price, rules, weekend_house_owner_id)
    values ('Partizanska 44', 15, 'Izuzetno lepa vikendica', '123edas123', 'Hell house', 12135, 'Be bad', 1);

insert into weekend_house (address, bed_number, description, image_path, name, price, rules, weekend_house_owner_id)
    values ('Rumenacka 2', 7, 'Fantastic', '123edas123', 'Paradise island', 6000, 'Be good', 1);

insert into weekend_house (address, bed_number, description, image_path, name, price, rules, weekend_house_owner_id)
    values ('Novosadska 144', 4, 'Nice view', '123edas123', 'Sunny valley', 4300, 'Be bad', 1);

insert into weekend_house (address, bed_number, description, image_path, name, price, rules, weekend_house_owner_id)
    values ('Grmuska 2', 5, 'Bas je lijepa', '123edas123', 'Orthodox', 6900, 'Be good', 1);

insert into weekend_house (address, bed_number, description, image_path, name, price, rules, weekend_house_owner_id)
    values ('Cirpanova 4', 9, 'Vrh tebra', '123edas123', 'Great Serbia', 10000, 'Be bad', 1);

insert into weekend_house (address, bed_number, description, image_path, name, price, rules, weekend_house_owner_id)
    values ('Hadzi Ruvimova 14', 4, 'Nice view', '123edas123', 'Sunny mount', 4000, 'Be bad', 1);

insert into weekend_house (address, bed_number, description, image_path, name, price, rules, weekend_house_owner_id)
    values ('Presernova 2', 5, 'Bas je lijepa', '123edas123', 'House', 6000, 'Be good', 1);

insert into weekend_house (address, bed_number, description, image_path, name, price, rules, weekend_house_owner_id)
    values ('Filipa Filipovica 4', 2, 'Vrh tebra', '123edas123', 'Cheese', 1000, 'Be bad', 1);


insert into weekend_house_reservation(end_date_time, end_special_offer, people_number, price, start_date_time, start_special_offer, customer_id, weekend_house_id, is_cancelled)
    values ('27.02.2022 14:00:00', null, 4, 23000, '23.02.2022 14:00:00', '21.02.2022 14:00:00', 1, 1, false);

insert into weekend_house_reservation(end_date_time, end_special_offer, people_number, price, start_date_time, start_special_offer, customer_id, weekend_house_id, is_cancelled)
    values ('07.02.2022 14:00:00', null, 7, 16000, '03.02.2022 14:00:00', '21.02.2022 14:00:00', 2, 2, false);

insert into weekend_house_reservation(end_date_time, end_special_offer, people_number, price, start_date_time, start_special_offer, customer_id, weekend_house_id, is_cancelled)
    values ('28.02.2022 14:00:00', null, 7, 16000, '23.02.2022 14:00:00', '21.02.2022 14:00:00', 1, 2, true);

insert into weekend_house_reservation(end_date_time, end_special_offer, people_number, price, start_date_time, start_special_offer, customer_id, weekend_house_id, is_cancelled)
    values ('08.01.2022 14:00:00', null, 7, 16000, '03.01.2022 14:00:00', '21.12.2021 14:00:00', 1, 5, false);

insert into weekend_house_reservation(end_date_time, end_special_offer, people_number, price, start_date_time, start_special_offer, customer_id, weekend_house_id, is_cancelled)
    values ('18.01.2021 14:00:00', null, 7, 16000, '03.01.2021 14:00:00', '21.12.2021 14:00:00', 1, 7, false);



INSERT INTO additional_service(name, price) VALUES ('WiFi', 200);
INSERT INTO additional_service(name, price) VALUES ('Klima', 1000);
INSERT INTO additional_service(name, price) VALUES ('Mini bar', 2000);
INSERT INTO additional_service(name, price) VALUES ('Jakuzi', 5000);
INSERT INTO additional_service(name, price) VALUES ('Sporet', 300);

INSERT INTO weekend_house_services(weekend_house_id, service_id) VALUES (1, 1);
INSERT INTO weekend_house_services(weekend_house_id, service_id) VALUES (1, 2);
INSERT INTO weekend_house_services(weekend_house_id, service_id) VALUES (2, 2);
INSERT INTO weekend_house_services(weekend_house_id, service_id) VALUES (3, 2);
INSERT INTO weekend_house_services(weekend_house_id, service_id) VALUES (3, 3);
INSERT INTO weekend_house_services(weekend_house_id, service_id) VALUES (4, 4);
INSERT INTO weekend_house_services(weekend_house_id, service_id) VALUES (5, 5);
INSERT INTO weekend_house_services(weekend_house_id, service_id) VALUES (6, 5);
INSERT INTO weekend_house_services(weekend_house_id, service_id) VALUES (7, 4);
INSERT INTO weekend_house_services(weekend_house_id, service_id) VALUES (7, 5);
INSERT INTO weekend_house_services(weekend_house_id, service_id) VALUES (8, 5);
INSERT INTO weekend_house_services(weekend_house_id, service_id) VALUES (9, 2);

INSERT INTO term(end_date_time, start_date_time, weekend_house_id) VALUES ('18.01.2022 14:00:00', '10.01.2022 14:00:00', 1);
INSERT INTO term(end_date_time, start_date_time, weekend_house_id) VALUES ('04.02.2022 14:00:00', '20.01.2022 14:00:00', 1);
INSERT INTO term(end_date_time, start_date_time, weekend_house_id) VALUES ('18.02.2022 14:00:00', '10.02.2022 14:00:00', 2);
INSERT INTO term(end_date_time, start_date_time, weekend_house_id) VALUES ('04.02.2022 14:00:00', '30.01.2022 14:00:00', 3);

INSERT INTO weekend_house_feedbacks(grade_house, grade_owner, is_approved, note_house, note_owner, weekend_house_reservation_id)
            VALUES (6, 7, true, 'I have expected better..', 'Okey', 1);
INSERT INTO weekend_house_feedbacks(grade_house, grade_owner, is_approved, note_house, note_owner, weekend_house_reservation_id)
            VALUES (9, 7, true, 'Nice', 'Okey', 1);
INSERT INTO weekend_house_feedbacks(grade_house, grade_owner, is_approved, note_house, note_owner, weekend_house_reservation_id)
            VALUES (6, 7, true, 'Not bad', 'Hmm', 4);
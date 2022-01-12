SET datestyle = dmy;
insert into role (name) values ('ROLE_CUSTOMER');
insert into role (name) values ('ROLE_ADMIN');
insert into role (name) values ('ROLE_BOAT_OWNER');
insert into role (name) values ('ROLE_INSTRUCTOR');
insert into role (name) values ('ROLE_WEEKEND_HOUSE_OWNER');

insert into customer (address, city, country, email, first_name, is_activated, is_deleted, last_name, password, phone, username, want_deleting, hash_code, role_id)
    values ('Presernova 3', 'Bled', 'Slovenia', 'health.care.clinic.psw+milica@gmail.com', 'Milica', true, false, 'Antic', '$2a$10$hd.PcSgRocME1rIrpLhXWOO/uKacPl4oyjf3k5DGHaBhesm6wC3SK', '0651234432', 'milica', false, '', 1);


insert into weekend_house_owner (address, city, country, email, first_name, is_activated, is_deleted, last_name, password, phone, username, want_deleting, motive, role_id)
    values ('Makarska 42', 'Beograd', 'Serbia', 'health.care.clinic.psw+antonije@gmail.com', 'Antonije', true, false, 'Pusic', '$2a$10$tfmCyLyvVUpTAqmApmZwUej3aT3WiUVOQu8sx1/dk0AE3LHrqJVnC', '0654677567', 'antonije', false, '', 5);


insert into weekend_house (address, bed_number, description, image_path, name, price, rules, weekend_house_owner_id)
    values ('Lovcenska 23', 5, 'Mnogo lepa vikendica', '123edas123', 'Heaven house', 1235, 'Be good', 1);

insert into weekend_house (address, bed_number, description, image_path, name, price, rules, weekend_house_owner_id)
    values ('Partizanska 44', 15, 'Izuzetno lepa vikendica', '123edas123', 'Hell house', 12135, 'Be bad', 1);


insert into weekend_house_reservation(end_date_time, end_special_offer, people_number, price, start_date_time, start_special_offer, customer_id, weekend_house_id)
    values ('27.02.2022 14:00:00', null, 4, 23000, '23.02.2022 14:00:00', '21.02.2022 14:00:00', 1, 1);


insert into station (city) values ('Fairfield');
insert into station (city) values ('San Francisco');
insert into station (city) values ('Chicago');
insert into station (city) values ('New York');

insert into car (registration_nr, construction_year, mileage, model, station_id)
values ('8HDP879', 2001, 16000, 'BMW', 1);

insert into car (registration_nr, construction_year, mileage, model, station_id)
values ('AC122K', 2017, 160000, 'Toyota', 1);

insert into car (registration_nr, construction_year, mileage, model, station_id)
values ('7DSR790', 2016, 181200, 'Audi', 1);

insert into car (registration_nr, construction_year, mileage, model, station_id)
values ('22H5948', 2002, 97212, 'Mazda', 1);

insert into car (registration_nr, construction_year, mileage, model, station_id)
values ('8ALB689', 2003, 200, 'Mercedes', 1);

insert into car (registration_nr, construction_year, mileage, model, station_id)
values ('ETS293', 2001, 16000, 'BMW', 2);

insert into car (registration_nr, construction_year, mileage, model, station_id)
values ('7HKS209', 2017, 160000, 'Toyota', 2);

insert into car (registration_nr, construction_year, mileage, model, station_id)
values ('JFP882', 2016, 181200, 'Audi', 2);

insert into car (registration_nr, construction_year, mileage, model, station_id)
values ('7SWL806', 2002, 97212, 'Mazda', 2);

insert into car (registration_nr, construction_year, mileage, model, station_id)
values ('109 LZT', 2003, 200, 'Mercedes', 2);

insert into car (registration_nr, construction_year, mileage, model, station_id)
values ('CEM7073', 2001, 16000, 'BMW', 3);

insert into car (registration_nr, construction_year, mileage, model, station_id)
values ('16D931', 2017, 160000, 'Toyota', 3);

insert into car (registration_nr, construction_year, mileage, model, station_id)
values ('5YFS305', 2016, 181200, 'Audi', 3);

insert into car (registration_nr, construction_year, mileage, model, station_id)
values ('62T270', 2003, 200, 'Mercedes', 4);

insert into car (registration_nr, construction_year, mileage, model, station_id)
values ('8JRZ509', 2001, 16000, 'BMW', 4);

insert into car (registration_nr, construction_year, mileage, model, station_id)
values ('5ETT641', 2017, 160000, 'Toyota', 4);

insert into car (registration_nr, construction_year, mileage, model, station_id)
values ('33190F2', 2016, 181200, 'Audi', 4);

insert into customer (customer_number, first_name, last_name) values (111111, 'Siam', 'Biswas');
insert into customer (customer_number, first_name, last_name) values (222222, 'Tanvir', 'Zobair');
insert into customer (customer_number, first_name, last_name) values (333333, 'Atikur', 'Rahman');
insert into customer (customer_number, first_name, last_name) values (444444, 'Shahab', 'Uddin');

insert into rental (
    rental_date,
    car_registration_nr,
    driver_customer_number,
    rental_station_id,
    return_date,
    return_station_id,
    km
) values (
    CURRENT_DATE(),
    '8HDP879',
    111111,
    1,
    CURRENT_DATE(),
    2,
    500
);

insert into rental (
    rental_date,
    car_registration_nr,
    driver_customer_number,
    rental_station_id,
    return_date,
    return_station_id,
    km
) values (
    CURRENT_DATE(),
    'AC122K',
    222222,
    3,
    CURRENT_DATE(),
    2,
    10000
);

insert into rental (
    rental_date,
    car_registration_nr,
    driver_customer_number,
    rental_station_id,
    return_date,
    return_station_id,
    km
) values (
    CURRENT_DATE(),
    '7DSR790',
    333333,
    3,
    CURRENT_DATE(),
    2,
    500
);

insert into rental (
    rental_date,
    car_registration_nr,
    driver_customer_number,
    rental_station_id,
    return_date,
    return_station_id,
    km
) values (
    CURRENT_DATE(),
    '22H5948',
    111111,
    1,
    CURRENT_DATE(),
    2,
    500
);

insert into rental (
    rental_date,
    car_registration_nr,
    driver_customer_number,
    rental_station_id,
    return_date,
    return_station_id,
    km
) values (
    CURRENT_DATE(),
    '8ALB689',
    111111,
    1,
    null,
    null,
    null
);

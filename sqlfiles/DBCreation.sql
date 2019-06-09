create schema if not exists acmedelivery;

create table if not exists acmedelivery.Business(
	address varchar(40),
    deliveryBlockRate float,
    deliveryBase float,
    bonusRate float,
    bonusGracePeriod int
);

create table if not exists acmedelivery.Courier(
	courier_id int auto_increment,
    courier_name varchar(40),
    isActive tinyint,
    primary key(courier_id)
);

create table if not exists acmedelivery.Customer(
	customer_id int auto_increment,
    customer_name varchar(40),
    customer_address varchar(40) NOT NULL,
    isActive tinyint,
    PRIMARY KEY(customer_id)
);

create table if not exists acmedelivery.CustomerOrder(
	order_id int auto_increment,
    package_id int,
    pickup_customer_id int,
    delivery_customer_id int,
    order_taker_id int,
    courier_id int,
    bill_to_delivery tinyint,
    special_instructions varchar(300),
    pick_up_time datetime,
    estimated_delivery datetime,
    estimated_departure datetime,
    distance int,
    price float,
    departure datetime,
    pickup datetime,
    delivery datetime,
    route varchar(300),
    order_status int,
    primary key(order_id)
);

create table if not exists acmedelivery.OrderTaker(
	orderTaker_id int auto_increment,
    orderTaker_name varchar(40),
    isActive tinyint,
    primary key(orderTaker_id)
);

create table if not exists acmedelivery.UserCredentials(
	user_id int auto_increment,
    username varchar(256),
    pass varchar(256),
    permissions int,
    primary key(user_id)
);

insert into acmedelivery.Customer (customer_name, customer_address, isActive) Values  
("Bickers & Bickers", "1st Ave and D Street", 1),
("Slaughter & Slaughter", "2nd Ave and B Street", 1),
("Payne & Fears", "2nd Ave and F Street", 1),
("Dewey, Cheatum & Howe", "3rd Ave and C Street", 1);

insert into acmedelivery.Business (address, deliveryBlockRate, deliveryBase, bonusRate, bonusGracePeriod) Values  
("4th Ave and D Street", 2, 10, 2, 5);

insert into acmedelivery.Courier (courier_name, isActive) Values  
("Jack Sprat", 1),
("Peter Pumkin", 1),
("Jack Candle", 1);

insert into acmedelivery.OrderTaker (orderTaker_name, isActive) Values  
("Larry Ron", 1),
("Andy Harbert", 1),
("Tony Pizza", 1);

insert into acmedelivery.UserCredentials (user_id, username, pass, permissions) Values  
(0, "admin", "password", 1);

insert into acmedelivery.UserCredentials (username, pass, permissions) Values  
("larry@gmail.com", "pass1234", 0),
("andy.harbert@oc.edu", "roadrunners<3", 0),
("tizza@tonyspizzapalace.net", "pizza", 0);

insert into acmedelivery.CustomerOrder(
	package_id, pickup_customer_id, delivery_customer_id, order_taker_id, 
	courier_id, bill_to_delivery, special_instructions, pick_up_time, estimated_delivery, 
	estimated_departure, distance, price, departure, pickup, delivery, route, order_status
    )Values  
(1, 1, 2, 1, null, 0, "Leave on front porch", "2020-06-03 12:00", null, null, null, null, null, null, null, null, 0);

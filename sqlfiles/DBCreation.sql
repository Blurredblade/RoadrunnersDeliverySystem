create schema if not exists acmedelivery;

create table if not exists acmedelivery.Business(
	address varchar(40),
    deliveryBlockRate float,
    deliveryBase float,
    bonusRate float,
    bonusGracePeriod int,
    courierSpeed int
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

create table if not exists acmedelivery.Intersection(
    intersection_id int auto_increment,
    intName varchar(20),
    northInt_id int,
    eastInt_id int,
    southInt_id int,
    westInt_id int,
    north tinyint,
    east tinyint,
    south tinyint,
    west tinyint,
    closed tinyint,
    primary key(intersection_id)
);

create table if not exists acmedelivery.UserCredentials(
	user_id int auto_increment,
    username varchar(256),
    pass varchar(256),
    permissions int,
    ordertaker_id int(11),
    primary key(user_id)
);

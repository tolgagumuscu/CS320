CREATE TABLE USERS(
userID int(5) auto_increment primary key,
userType int(1) not null,
userName varchar(50) not null,
userPassword varchar(50) not null
);

Create table Fastfoods(
foodID int(2) auto_increment primary key,
foodName varchar(30) not null,
foodPrice float(3) not null,
foodImage varchar(100) default null,
foodStock int(4) default 100
);

create table beverages(
beverageID int(2) auto_increment primary key,
beverageName varchar(20) not null,
beveragePrice float(3) not null,
beverageImage varchar(100) default null,
beverageStock int(4) default 100
);

create table Orders(
orderID int(5) auto_increment,
orderedPersonID int(5),
orderedFoodID int(2) default null,
orderedBeverageID int(2) default null,
orderTime timestamp default NOW(),
orderPayment float(5) default null,
paid boolean default false,
primary key (orderID),
foreign key (orderedFoodID) references Fastfoods(foodID),
foreign key (orderedBeverageID) references beverages(beverageID),
foreign key (orderedPersonID) references users(userID)
);

create table computers(
computerID int(5) primary key auto_increment,
computerName varchar(20) not null,
isEmpty boolean default 0,
startTime timestamp default NOW(),
finishTime timestamp default NOW(),
payment double default 0
);

create table payment(
paymentID int(5) primary key auto_increment,
userID int(5) not null,
computerID int(5) not null,
orderID int(5) default null,
beginTime timestamp default now(),
totalPay float(5) default 5,
foreign key (userID) references users(userID),
foreign key (computerID) references computers(computerID),
foreign key (orderID) references orders(orderID)
);
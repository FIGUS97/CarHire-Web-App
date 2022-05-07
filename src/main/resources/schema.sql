--CREATE TABLE City (
--                      ID_City integer PRIMARY KEY,
--                      Name varchar(255)
--);
--
--CREATE TABLE APP_USER (
--                          ID_User integer PRIMARY KEY,
--                          Name_Surname varchar(255),
--                          ID_City integer,
--                          Responsibility varchar(255),
--                          Age integer,
--                          Status varchar(255),
--                          ID_Role integer,
--                          username varchar(255),
--                          password varchar(255)
--);
--
--CREATE TABLE Car (
--                     ID_Car integer PRIMARY KEY,
--                     Brand varchar(255),
--                     Model varchar(255),
--                     City varchar(255),
--                     Price_Per_Day float,
--                     Status varchar(255)
--);
--
--CREATE TABLE Car_Hire (
--                          ID_Hire integer PRIMARY KEY,
--                          Customer integer,
--                          Car integer,
--                          status varchar(255),
--                          Number_days integer,
--                          Amount float
--);
--
--CREATE TABLE Role (
--                    ID_Role integer PRIMARY KEY,
--                    Name varchar(255)
--);
--

INSERT INTO City (ID_City, Name)
VALUES(1,  'Warszawa');
INSERT INTO City (ID_City, Name)
VALUES(2,  'Krakow');

INSERT INTO Role (ID_Role, Name)
VALUES(1, 'Employee');
INSERT INTO Role (ID_Role, Name)
VALUES(2, 'Customer');

INSERT INTO USER (ID_User, Name_Surname, ID_City, Responsibility, Age, Status, ID_Role, username, password )
VALUES(101,  'James Walin', 1,'Renting cars', 32, 'ACTIVE', 1, 'james', 'james');
INSERT INTO USER (ID_User, Name_Surname, ID_City, Responsibility, Age, Status, ID_Role, username, password )
VALUES(102,  'Matt Hacketton', 2,'Renting cars', 32, 'ACTIVE', 1, 'matt', 'matt');

INSERT INTO Car (ID_Car, Brand, Model, City, Price_Per_Day, Status )
VALUES(120002,  'Opel', 'Adam',1, 90, 'Free');
INSERT INTO Car (ID_Car, Brand, Model, City, Price_Per_Day, Status )
VALUES(120003,  'Opel', 'Zafira',2, 120, 'Booked');

INSERT INTO USER (ID_User, Name_Surname, ID_City, Responsibility, Age, Status, ID_Role, username, password )
VALUES(103,  'Anna Walin', 1,'', 32, 'ACTIVE', 2, 'anna', 'anna');
INSERT INTO USER (ID_User, Name_Surname, ID_City, Responsibility, Age, Status, ID_Role, username, password )
VALUES(104,  'Roman Walin', 2,'', 32, 'ACTIVE', 2, 'roman', 'roman');
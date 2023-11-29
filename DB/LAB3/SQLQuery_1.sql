CREATE TABLE Client (
    SSN INT PRIMARY KEY NOT NULL,
    City VARCHAR (50),
    Street VARCHAR (50),
    PhoneNumber VARCHAR (10) UNIQUE NOT NULL,
    CType VARCHAR (10)
);

GO

CREATE TABLE MobileOperator(
    OperatorID INT PRIMARY KEY  NOT NULL,
    OperatorName VARCHAR (20) NOT NULL CHECK (OperatorName IN ('Vip.One', 'T-Mobile')),
    ODescription VARCHAR (255)
);

GO

CREATE TABLE TelephoneNumber(
    PhoneNumber VARCHAR (10) PRIMARY KEY NOT NULL CHECK (PhoneNumber LIKE '0%'),
    ClientSSN INT NOT NULL,
    FOREIGN KEY (ClientSSN) REFERENCES Client (SSN) ON DELETE CASCADE,
);

GO

CREATE TABLE Agreement(
    Date DATE,
    OperatorID INT NOT NULL,
    PhoneNumber VARCHAR (10) NOT NULL,
    ClientSSN INT NOT NULL,
    FOREIGN KEY (OperatorID) REFERENCES MobileOperator (OperatorID) ON DELETE CASCADE,
    FOREIGN KEY (PhoneNumber) REFERENCES TelephoneNumber (PhoneNumber) ON DELETE CASCADE,
    FOREIGN KEY (ClientSSN) REFERENCES Client (SSN)   ,
);

GO

CREATE TABLE NumberPlan(
    PlanID INT PRIMARY KEY NOT NULL,
    Name VARCHAR(50),
    Description VARCHAR(255),
    Price INT NOT NULL CHECK (Price > 0)
);

GO

INSERT INTO Client VALUES (123456789, 'Skopje', 'Partizanska', '070123456', 'Individual');
INSERT INTO Client VALUES (987654321, 'Skopje', 'Partizanska', '070654321', 'Individual');
INSERT INTO Client VALUES (123123123, 'Skopje', 'Partizanska', '070123123', 'Individual');
INSERT INTO Client VALUES (321321321, 'Skopje', 'Partizanska', '070321321', 'Individual');

GO

INSERT INTO MobileOperator VALUES (1, 'Vip.One', 'Vip Operator');
INSERT INTO MobileOperator VALUES (2, 'T-Mobile', 'T-Mobile Operator');

GO 

INSERT INTO TelephoneNumber VALUES ('070123456', 123456789);
INSERT INTO TelephoneNumber VALUES ('070654321', 987654321);
INSERT INTO TelephoneNumber VALUES ('070123123', 123123123);
INSERT INTO TelephoneNumber VALUES ('070321321', 321321321);

GO 

INSERT INTO Agreement VALUES ('2015-01-01', 1, '070123456', 123456789);
INSERT INTO Agreement VALUES ('2015-01-01', 1, '070654321', 987654321);
INSERT INTO Agreement VALUES ('2015-01-01', 1, '070123123', 123123123);
INSERT INTO Agreement VALUES ('2015-01-01', 1, '070321321', 321321321);

GO 

INSERT INTO NumberPlan VALUES (1, 'Plan 1', 'Plan 1', 100);
INSERT INTO NumberPlan VALUES (2, 'Plan 2', 'Plan 2', 200);
INSERT INTO NumberPlan VALUES (3, 'Plan 3', 'Plan 3', 300);
INSERT INTO NumberPlan VALUES (4, 'Plan 4', 'Plan 4', 400);

GO

SELECT * FROM Client;
SELECT * FROM MobileOperator;
SELECT * FROM TelephoneNumber;
SELECT * FROM Agreement;
SELECT * FROM NumberPlan;
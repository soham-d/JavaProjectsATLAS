create table Users(
	id INT IDENTITY(1,1),
	name NVARCHAR(50) not null,
	phone NVARCHAR(20),
	email NVARCHAR(30) UNIQUE,
	password NVARCHAR(100),
	address NVARCHAR(100),
	department NVARCHAR(30),
	type INT,
	createdOn DATETIME DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(id));


create table Routes(
	routeID INT IDENTITY(1,1),
	title NVARCHAR(50) not null,
	description NVARCHAR(100),
	adminID INT foreign key references Users(id),
	createdOn DATETIME DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(routeID));


create table Stops(
	stopID INT IDENTITY(1,1),
	address NVARCHAR(50) NOT NULL,
	sequenceOrder INT NOT NULL,
	routeID INT foreign key references Routes(routeID),
	adminID INT foreign key references Users(id),
	createdOn DATETIME DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(stopID));


create table Vehicles(
    id INT IDENTITY(1,1),
    regNo NVARCHAR (20) NOT NULL,
    type int NOT NULL,
    filledSeats INT,
    totalSeats INT NOT NULL,
    startPickUpTime NVARCHAR (50),
    startDropOffTime NVARCHAR (50),
    vehicleAvailability bit NOT NULL,
    driverID INT NOT NULL,
    routeID INT  foreign key references Routes(routeID),
    adminID INT  foreign key references Users(id),
    createdOn DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id));


create table BusPass(
	id INT IDENTITY(1,1),
	requestedOn DATETIME DEFAULT CURRENT_TIMESTAMP,
	approvedRejectedOn DATETIME,
	validTill DATETIME,
	status INT DEFAULT 1,
	"uid" INT foreign key references Users(id),
	routeID INT foreign key references Routes(routeID),
	createdOn DATETIME DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(id));


create table Feedbacks(
	feedbackID INT IDENTITY(1,1),
	userID INT foreign key references Users(id),
	raisedBy NVARCHAR(256),
	type INT,
	title NVARCHAR(256),
	description NVARCHAR(2048),
	createdOn DATETIME DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(feedbackID));

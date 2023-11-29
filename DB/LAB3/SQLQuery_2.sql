/*CREATE TABLE [dbo].[Client] (
    [ClientID]  INT          IDENTITY (1, 1) NOT NULL,
    [FirstName] VARCHAR (50) NOT NULL,
    [LastName]  VARCHAR (50) NOT NULL,
    [Address]   TEXT         NULL,
    [Country]   VARCHAR (50) NOT NULL,
    CONSTRAINT [PK_Client] PRIMARY KEY CLUSTERED ([ClientID] ASC)
);


CREATE TABLE [dbo].[Hotel] (
    [HotelID] INT          IDENTITY (1, 1) NOT NULL,
    [Name]    VARCHAR (50) NOT NULL,
    [Address] TEXT         NOT NULL,
    [Rating]  INT          NULL,
    [City]    VARCHAR (50) NOT NULL,
    [Country] VARCHAR (50) NOT NULL,
    CONSTRAINT [PK_Hotel] PRIMARY KEY CLUSTERED ([HotelID] ASC)
);

CREATE TABLE [dbo].[Reservation] (
    [ReservationID]   INT  IDENTITY (1, 1) NOT NULL,
    [ClientID]        INT  NOT NULL,
    [HotelID]         INT  NOT NULL,
    [RoomTypeID]      INT  NOT NULL,
    [ReservationDate] DATE NULL,
    [ArrivalDate]     DATE NULL,
    [DepartureDate]   DATE NULL,
    CONSTRAINT [PK_Reservation] PRIMARY KEY CLUSTERED ([ReservationID] ASC),
    CONSTRAINT [FK_HOTEL] FOREIGN KEY ([HotelID]) REFERENCES [dbo].[Hotel] ([HotelID]),
    CONSTRAINT [FK_Reservation_Client] FOREIGN KEY ([ClientID]) REFERENCES [dbo].[Client] ([ClientID]),
    CONSTRAINT [FK_ROOMTYPE] FOREIGN KEY ([RoomTypeID]) REFERENCES [dbo].[RoomType] ([TypeID])
);

CREATE TABLE [dbo].[Room] (
    [RoomID]  INT          IDENTITY (1, 1) NOT NULL,
    [HotelID] INT          NOT NULL,
    [TypeID]  INT          NOT NULL,
    [Floor]   VARCHAR (50) NOT NULL,
    CONSTRAINT [PK_Room] PRIMARY KEY CLUSTERED ([RoomID] ASC),
    CONSTRAINT [FK_Room_Hotel] FOREIGN KEY ([HotelID]) REFERENCES [dbo].[Hotel] ([HotelID]),
    CONSTRAINT [FK_Room_RoomType] FOREIGN KEY ([TypeID]) REFERENCES [dbo].[RoomType] ([TypeID])
);

CREATE TABLE [dbo].[RoomType] (
    [TypeID]     INT          IDENTITY (1, 1) NOT NULL,
    [Name]       VARCHAR (50) NOT NULL,
    [NumBeds]    INT          NOT NULL,
    [NumPersons] INT          NOT NULL,
    CONSTRAINT [PK_RoomType] PRIMARY KEY CLUSTERED ([TypeID] ASC)
);
*/

SELECT * FROM Client;
SELECT * FROM Hotel;
SELECT * FROM RoomType;
SELECT * FROM Room;
SELECT * FROM Reservation;


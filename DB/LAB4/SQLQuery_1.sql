-- IMPORTING DATA FROM TXT File

-- 1. Create a table to store the data
BULK INSERT dbo.PostalCode
FROM  'C:\Users\berat\Downloads\Homework 4 Oriflame DB\oriflameDB\PostalCode.txt'
WITH
(
    FIELDTERMINATOR = ' ',
    FIRSTROW = 2
)
GO

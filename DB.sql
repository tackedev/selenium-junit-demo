CREATE DATABASE SeleniumDemo
GO

USE SeleniumDemo
GO

CREATE TABLE  [user] (
    username            VARCHAR(15)         PRIMARY KEY,
    password            CHAR(64)            NOT NULL,
    fullname            NVARCHAR(50)        NOT NULL,
)
GO

USE master;
GO

CREATE LOGIN userlogin WITH PASSWORD = '';
GO

CREATE USER userlogin FOR LOGIN userlogin;
GO

ALTER SERVER ROLE sysadmin ADD MEMBER userlogin;
GO
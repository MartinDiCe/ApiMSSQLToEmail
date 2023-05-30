USE master;
GO

CREATE LOGIN apiexport WITH PASSWORD = 'Osm3d1c4';
GO

CREATE USER apiexport FOR LOGIN apiexport;
GO

ALTER SERVER ROLE sysadmin ADD MEMBER apiexport;
GO
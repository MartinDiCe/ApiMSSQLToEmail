#!/bin/bash

# Espera hasta que el servicio de base de datos esté en funcionamiento
until /opt/mssql-tools/bin/sqlcmd -S <hostname> -U <username> -P <password> -Q "SELECT 1"; do
    sleep 1
done

# Realiza la exportación de la base de datos productiva a un archivo de respaldo BACPAC
/opt/mssql-tools/bin/sqlpackage /Action:Export /SourceServerName:<hostname> /SourceDatabaseName:<database_name> /TargetFile:/tmp/backup.bacpac /TargetPassword:<backup_password>

# Importa el archivo de respaldo BACPAC en la base de datos del contenedor
/opt/mssql-tools/bin/sqlpackage /Action:Import /SourceFile:/tmp/backup.bacpac /TargetServerName:localhost /TargetDatabaseName:<database_name> /TargetPassword:<restore_password>

# Elimina el archivo de respaldo
rm /tmp/backup.bacpac
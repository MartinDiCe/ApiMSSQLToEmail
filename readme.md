Comandos para dockerizar, el backup de la base de pruebas esta en la raiz del proyecto

--Crear JAR
mvn clean package -Dskipstests
--Crear Imagen api
docker build -t apiexport .

--Crear contenedor MSSQL y copiar backup de algún lado, teniendo en cuenta que se tiene una imagen sqlserver 2019 en docker
docker run -d -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=Osm3d1c418.23' -p 1433:1433 --name sqlserver --ip 172.17.0.19 -v data-mssql:/var/opt/mssql --restart=always --network diceprojects mdiceprojects/sqlserver

--Crear contenedor.
docker run -d -p 8008:8008 --rm --name apiexport --network diceprojects apiexport

--Añadir contenedor a mi network
docker network connect diceprojects apiexport
--Si no existe network
docker network create diceprojects
# Establece la imagen base de Java
FROM openjdk:21-jdk

ENV LANG=C.UTF-8

# Expone el puerto en el que se ejecuta tu aplicación (si es necesario)
EXPOSE 8008

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /var/app

# Copia el archivo JAR de tu aplicación al contenedor
COPY target/ApiExportExcelToEmail-0.0.1-SNAPSHOT.jar app.jar

# Define el comando para ejecutar tu aplicación
ENTRYPOINT ["java", "-Dserver.port=8008", "-jar", "app.jar"]



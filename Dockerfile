# Establece la imagen base de Java
FROM openjdk:11-jdk

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /apiExport

# Copia el archivo JAR de tu aplicación al contenedor
COPY target/apiExport.jar apiExport.jar

# Expone el puerto en el que se ejecuta tu aplicación (si es necesario)
EXPOSE 8008

# Define el comando para ejecutar tu aplicación
CMD ["java", "-jar", "apiExport.jar"]

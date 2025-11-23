UCC - Plataforma SOA (SOAP + REST)
==================================

Este proyecto contiene un ejemplo sencillo de la plataforma unificada
que pide la actividad:

- Base de datos MySQL (db.sql)
- API REST en Java (Spring Boot) para Alumnos y Calificaciones
- API SOAP en Java (JAX-WS sencillo) para consultar Alumnos

Estructura:

- db.sql
- rest-api/
- soap-api/

Requisitos generales:
- JDK 17 (o 11)
- Maven instalado
- MySQL instalado (usar MySQL Workbench para crear la BD)

Pasos rápidos:

1. Crear la BD:
   - Abrir MySQL Workbench
   - Ejecutar el script db.sql

2. Configurar credenciales en:
   - rest-api/src/main/resources/application.properties
   - soap-api/src/main/java/com/ucc/soa/soap/DBUtil.java

3. Levantar API REST:
   - cd rest-api
   - mvn spring-boot:run

4. Levantar API SOAP:
   - cd soap-api
   - mvn compile exec:java

Revisa el archivo README-DETALLADO.txt (que puedes crear tú mismo con tus apuntes)
para documentar tus pruebas y arquitectura.

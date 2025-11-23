-- Script de ejemplo para la base de datos de SOA
CREATE DATABASE IF NOT EXISTS soa_universidad;
USE soa_universidad;

-- Se asume que la tabla `alumnos` ya existe (la misma del API REST).

CREATE TABLE IF NOT EXISTS matriculas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    alumno_id BIGINT NOT NULL,
    periodo VARCHAR(50) NOT NULL,
    estatus VARCHAR(20) NOT NULL,
    CONSTRAINT fk_matriculas_alumno
        FOREIGN KEY (alumno_id) REFERENCES alumnos(id)
);

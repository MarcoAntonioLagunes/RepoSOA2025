-- Script para crear la tabla 'matriculas' en la BD soa_universidad

USE soa_universidad;

CREATE TABLE IF NOT EXISTS matriculas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    alumno_id INT NOT NULL,
    periodo VARCHAR(100) NOT NULL,
    estatus VARCHAR(50) NOT NULL,
    CONSTRAINT fk_matricula_alumno
        FOREIGN KEY (alumno_id) REFERENCES alumnos(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

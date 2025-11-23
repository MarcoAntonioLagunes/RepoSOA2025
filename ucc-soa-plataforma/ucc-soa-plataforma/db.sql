CREATE DATABASE IF NOT EXISTS soa_universidad;
USE soa_universidad;

CREATE TABLE IF NOT EXISTS alumnos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  email VARCHAR(120) NOT NULL,
  carrera VARCHAR(120) NOT NULL
);

CREATE TABLE IF NOT EXISTS calificaciones (
  id INT AUTO_INCREMENT PRIMARY KEY,
  alumno_id INT NOT NULL,
  materia VARCHAR(120) NOT NULL,
  calificacion DECIMAL(4,2) NOT NULL,
  fecha DATE NOT NULL,
  CONSTRAINT fk_calif_alumno FOREIGN KEY (alumno_id)
    REFERENCES alumnos(id)
);

INSERT INTO alumnos (nombre, email, carrera) VALUES
('Juan Pérez', 'juan.perez@uav.mx', 'Ingeniería de Software'),
('Ana López', 'ana.lopez@uav.mx', 'Redes y Telecomunicaciones');

INSERT INTO calificaciones (alumno_id, materia, calificacion, fecha) VALUES
(1, 'Arquitectura Orientada a Servicios', 9.5, '2025-11-15'),
(2, 'Seguridad en Redes', 8.7, '2025-11-16');

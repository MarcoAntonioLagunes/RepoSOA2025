package com.ucc.soa.rest.controller;

import com.ucc.soa.rest.entity.Alumno;
import com.ucc.soa.rest.repository.AlumnoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoRepository alumnoRepository;

    // ====================================
    // GET - Listar todos los alumnos
    // ====================================
    @GetMapping
    public List<Alumno> obtenerAlumnos() {
        return alumnoRepository.findAll();
    }

    // ====================================
    // GET por ID
    // ====================================
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerAlumnoPorId(@PathVariable Long id) {
        Optional<Alumno> alumno = alumnoRepository.findById(id);

        if (!alumno.isPresent()) {
            return ResponseEntity.status(404).body("Alumno no encontrado");
        }

        return ResponseEntity.ok(alumno.get());
    }

    // ====================================
    // POST - Crear alumno
    // ====================================
    @PostMapping
    public Alumno crearAlumno(@RequestBody Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    // ====================================
    // PUT - Actualizar alumno
    // ====================================
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAlumno(
            @PathVariable Long id,
            @RequestBody Alumno nuevosDatos) {

        Optional<Alumno> alumnoOptional = alumnoRepository.findById(id);

        if (!alumnoOptional.isPresent()) {
            return ResponseEntity.status(404).body("Alumno no encontrado");
        }

        Alumno alumnoExistente = alumnoOptional.get();

        alumnoExistente.setNombre(nuevosDatos.getNombre());
        alumnoExistente.setEmail(nuevosDatos.getEmail());
        alumnoExistente.setCarrera(nuevosDatos.getCarrera());

        alumnoRepository.save(alumnoExistente);

        return ResponseEntity.ok(alumnoExistente);
    }

    // ====================================
    // DELETE - Eliminar alumno
    // ====================================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarAlumno(@PathVariable Long id) {

        if (!alumnoRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Alumno no encontrado");
        }

        alumnoRepository.deleteById(id);
        return ResponseEntity.ok("Alumno eliminado correctamente");
    }
}

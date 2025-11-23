package com.ucc.soa.rest.controller;

import com.ucc.soa.rest.entity.Calificacion;
import com.ucc.soa.rest.repository.CalificacionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/calificaciones")
public class CalificacionController {

    @Autowired
    private CalificacionRepository calificacionRepository;

    // ================================
    // GET - Listar todas
    // ================================
    @GetMapping
    public List<Calificacion> obtenerCalificaciones() {
        return calificacionRepository.findAll();
    }

    // ================================
    // POST - Crear nueva
    // ================================
    @PostMapping
    public Calificacion crearCalificacion(@RequestBody Calificacion calificacion) {
        return calificacionRepository.save(calificacion);
    }

    // ================================
    // PUT - Actualizar una existente
    // ================================
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCalificacion(
            @PathVariable Long id,
            @RequestBody Calificacion nuevaCalificacion) {

        Optional<Calificacion> calificacionOptional = calificacionRepository.findById(id);

        if (!calificacionOptional.isPresent()) {
            return ResponseEntity.status(404).body("Calificación no encontrada");
        }

        Calificacion calificacionExistente = calificacionOptional.get();

        // Actualizar campos
        calificacionExistente.setMateria(nuevaCalificacion.getMateria());
        calificacionExistente.setCalificacion(nuevaCalificacion.getCalificacion());
        calificacionExistente.setFecha(nuevaCalificacion.getFecha());
        calificacionExistente.setAlumno(nuevaCalificacion.getAlumno());

        // Guardar cambios
        calificacionRepository.save(calificacionExistente);

        return ResponseEntity.ok(calificacionExistente);
    }

    // ================================
    // DELETE - Eliminar por ID
    // ================================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCalificacion(@PathVariable Long id) {

        if (!calificacionRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Calificación no encontrada");
        }

        calificacionRepository.deleteById(id);

        return ResponseEntity.ok("Calificación eliminada correctamente");
    }
}

package com.ucc.soa.soap;

import java.io.Serializable;

public class AlumnoSOAP implements Serializable {

    private Long id;
    private String nombre;
    private String email;
    private String carrera;

    public AlumnoSOAP() {}

    public AlumnoSOAP(Long id, String nombre, String email, String carrera) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.carrera = carrera;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
}

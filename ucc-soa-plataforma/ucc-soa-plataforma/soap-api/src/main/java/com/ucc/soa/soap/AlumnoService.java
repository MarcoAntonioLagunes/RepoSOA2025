package com.ucc.soa.soap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebService(serviceName = "AlumnoService")
public class AlumnoService {

    @WebMethod
    public AlumnoSOAP obtenerAlumnoPorId(long id) {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT id, nombre, email, carrera FROM alumnos WHERE id = ?")
        ) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new AlumnoSOAP(
                        rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("carrera")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @WebMethod
    public List<AlumnoSOAP> listarAlumnos() {
        List<AlumnoSOAP> lista = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT id, nombre, email, carrera FROM alumnos")
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new AlumnoSOAP(
                        rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("carrera")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}

package com.ucc.soa.soap;

import com.sun.xml.ws.transport.http.server.EndpointImpl;

import javax.xml.ws.Endpoint;

public class SoapServer {

    public static void main(String[] args) {
        String url = "http://0.0.0.0:8081/ws/alumnos";
        System.out.println("Publicando servicio SOAP en " + url);
        Endpoint endpoint = Endpoint.create(new AlumnoService());
        endpoint.publish(url);
        System.out.println("Servicio SOAP listo. Ctrl+C para detener.");
    }
}

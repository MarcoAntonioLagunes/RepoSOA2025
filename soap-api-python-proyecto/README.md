# SOAP API Python - Matrículas (SOA Universidad)

Este proyecto implementa un servicio SOAP en Python usando **Spyne**,
conectado a la misma base de datos MySQL `soa_universidad` que usas en el API REST.

## Requisitos

- Python 3.10+
- MySQL en localhost con usuario `root` y contraseña `root1234`
  (puedes cambiar esto en `app.py`, diccionario `DB_CONFIG`).
- Base de datos `soa_universidad` con tabla `alumnos` creada.
- Tabla `matriculas` (puedes crearla ejecutando `db.sql` en MySQL Workbench).

## Instalación

```bash
cd soap-api-python-proyecto
pip install -r requirements.txt
```

## Ejecutar el servicio

```bash
python app.py
```

Verás en consola:

- `Iniciando servicio SOAP en http://localhost:8000 ...`
- `WSDL disponible en http://localhost:8000/?wsdl`

## Pruebas en Postman

### 1. getAllMatriculas

- Método: POST
- URL: `http://localhost:8000/`
- Headers:
  - `Content-Type: text/xml`
  - `SOAPAction: ""` (vacío)
- Body → raw → XML:

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:mat="soa.universidad.matriculas">
    <soapenv:Header/>
    <soapenv:Body>
        <mat:getAllMatriculas/>
    </soapenv:Body>
</soapenv:Envelope>
```

### 2. getMatricula

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:mat="soa.universidad.matriculas">
    <soapenv:Header/>
    <soapenv:Body>
        <mat:getMatricula>
            <mat:matricula_id>1</mat:matricula_id>
        </mat:getMatricula>
    </soapenv:Body>
</soapenv:Envelope>
```

### 3. createMatricula

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:mat="soa.universidad.matriculas">
    <soapenv:Header/>
    <soapenv:Body>
        <mat:createMatricula>
            <mat:alumno_id>5</mat:alumno_id>
            <mat:periodo>2025-1</mat:periodo>
            <mat:estatus>Activo</mat:estatus>
        </mat:createMatricula>
    </soapenv:Body>
</soapenv:Envelope>
```

Con esto tu profe puede ver claramente el servicio SOAP funcionando sobre la misma
BD que el API REST.

# API SOAP en Python - Matriculas (SOA Universidad)

Este proyecto implementa **la parte SOAP** de la actividad:
una API SOAP en **Python** que gestiona la entidad `Matricula`
conectada a la misma base de datos MySQL `soa_universidad`.

## Requisitos

- Python 3.10+
- MySQL 8 (ya debes tener creada la base `soa_universidad`)
- Tener la tabla `alumnos` creada (desde la API REST de Java)
- Puesto que usa la misma BD que la API REST, comparte datos.

## Instalación

1. Crea y activa un entorno virtual (opcional pero recomendado):

```bash
python -m venv venv
venv\Scripts\activate  # En Windows
```

2. Instala dependencias:

```bash
pip install -r requirements.txt
```

3. Crea la tabla `matriculas` en MySQL:

- Abre MySQL Workbench
- Ejecuta el script:

```sql
db-matriculas.sql
```

o copia el contenido del archivo y ejecútalo en el esquema `soa_universidad`.

## Configuración de conexión a MySQL

En `app.py` se encuentra:

```python
DB_CONFIG = {
    "host": "localhost",
    "user": "root",
    "password": "root1234",
    "database": "soa_universidad",
}
```

Si tus credenciales son distintas, cámbialas.

## Ejecutar el servicio SOAP

Desde la carpeta `soap-api-python`:

```bash
python app.py
```

Verás algo como:

```
Iniciando servicio SOAP en http://localhost:8000 ...
WSDL disponible en http://localhost:8000/?wsdl
```

## Probar el servicio SOAP

Puedes usar **SoapUI** o **Postman (modo raw XML)**.

### 1. Crear una matrícula

Endpoint:

- URL: `http://localhost:8000/`
- Método: `POST`
- Header: `Content-Type: text/xml`

Body:

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:mat="soa.universidad.matriculas">
   <soapenv:Header/>
   <soapenv:Body>
      <mat:createMatricula>
         <alumno_id>1</alumno_id>
         <periodo>Enero-Abril 2025</periodo>
         <estatus>Activo</estatus>
      </mat:createMatricula>
   </soapenv:Body>
</soapenv:Envelope>
```

### 2. Obtener una matrícula por ID

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:mat="soa.universidad.matriculas">
   <soapenv:Header/>
   <soapenv:Body>
      <mat:getMatricula>
         <matricula_id>1</matricula_id>
      </mat:getMatricula>
   </soapenv:Body>
</soapenv:Envelope>
```

### 3. Obtener todas las matrículas

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:mat="soa.universidad.matriculas">
   <soapenv:Header/>
   <soapenv:Body>
      <mat:getAllMatriculas/>
   </soapenv:Body>
</soapenv:Envelope>
```

## Entrega para la actividad

Con este proyecto + tu API REST en Java:

- Cumples el punto de **API REST con Java + MySQL**.
- Cumples el punto de **API SOAP con Python + MySQL**.
- Ambas usan la misma BD `soa_universidad`.

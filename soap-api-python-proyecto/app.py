from spyne import Application, rpc, ServiceBase, Integer, Unicode, Iterable
from spyne.protocol.soap import Soap11
from spyne.server.wsgi import WsgiApplication
from wsgiref.simple_server import make_server
import mysql.connector

# ================================
# Configuración de conexión a MySQL
# ================================
DB_CONFIG = {
    "host": "localhost",
    "user": "root",
    "password": "root1234",
    "database": "soa_universidad",
}

def db_connection():
    return mysql.connector.connect(**DB_CONFIG)

class MatriculaService(ServiceBase):
    """Servicio SOAP para gestionar matrículas.

    WSDL:  http://localhost:8000/?wsdl

    Operaciones:
    - getMatricula(matricula_id)
    - getAllMatriculas()
    - createMatricula(alumno_id, periodo, estatus)
    """        

    @rpc(Integer, _returns=Iterable(Unicode))
    def getMatricula(ctx, matricula_id):
        """Obtiene una matrícula por ID."""
        conn = db_connection()
        cursor = conn.cursor()
        cursor.execute(
            "SELECT id, alumno_id, periodo, estatus FROM matriculas WHERE id = %s",
            (matricula_id,),
        )
        result = cursor.fetchone()
        conn.close()

        if result:
            yield f"id={result[0]}"
            yield f"alumno_id={result[1]}"
            yield f"periodo={result[2]}"
            yield f"estatus={result[3]}"
        else:
            yield "Matricula no encontrada"

    @rpc(Integer, Unicode, Unicode, _returns=Unicode)
    def createMatricula(ctx, alumno_id, periodo, estatus):
        """Crea una nueva matrícula vinculada a un alumno existente."""
        conn = db_connection()
        cursor = conn.cursor()
        cursor.execute(
            "INSERT INTO matriculas (alumno_id, periodo, estatus) VALUES (%s, %s, %s)",
            (alumno_id, periodo, estatus),
        )
        conn.commit()
        conn.close()
        return "Matricula creada correctamente"

    @rpc(_returns=Iterable(Unicode))
    def getAllMatriculas(ctx):
        """Devuelve todas las matrículas registradas."""
        conn = db_connection()
        cursor = conn.cursor()
        cursor.execute("SELECT id, alumno_id, periodo, estatus FROM matriculas")
        results = cursor.fetchall()
        conn.close()

        for row in results:
            yield f"{row[0]}|{row[1]}|{row[2]}|{row[3]}"

# ================================
# Configuración de la aplicación SOAP
# ================================
soap_app = Application(
    [MatriculaService],
    tns="soa.universidad.matriculas",
    in_protocol=Soap11(validator="lxml"),
    out_protocol=Soap11(),
)

wsgi_app = WsgiApplication(soap_app)

if __name__ == "__main__":
    print("Iniciando servicio SOAP en http://localhost:8000 ...")
    print("WSDL disponible en http://localhost:8000/?wsdl")
    server = make_server("0.0.0.0", 8000, wsgi_app)
    server.serve_forever()

package app.com.gtservice;

/**
 * Created by Ivan on 11/26/2015.
 */
public class Candidato {
    // Labels table name
    public static final String TABLE = "Candidato";

    // Labels Table Columns names
    public static final String KEY_id = "idCandidato";
    public static final String KEY_Folio = "Folio";
    public static final String KEY_CURP = "CURP";
    public static final String KEY_Nombres = "Nombres";
    public static final String KEY_ApellidoPaterno = "ApellidoPaterno";
    public static final String KEY_ApellidoMaterno = "ApellidoMaterno";
    public static final String KEY_Sexo = "Sexo";
    public static final String KEY_FechaNacimiento = "FechaNacimiento";
    public static final String KEY_Domicilio = "Domicilio";
    public static final String KEY_CodigoPostal = "CodigoPostal";
    public static final String KEY_Colonia = "Colonia";
    public static final String KEY_Tel = "Tel";
    public static final String KEY_TelAlterno = "TelAlterno";
    public static final String KEY_NombreEmergencia = "NombreEmergencia";
    public static final String KEY_TelEmergencia = "TelEmergencia";
    public static final String KEY_Parentesco = "Parentesco";
    public static final String KEY_FechaIngreso = "FechaIngreso";
    public static final String KEY_Contacto = "Contacto";
    public static final String KEY_idReclutador = "idReclutador";
    public static final String KEY_Empresa = "Empresa";

    // property help us to keep data
    public int id_Candidato;
    public String curp;
    public String folio;
    public String nombres;
    public String apellidopaterno;
    public String apellidomaterno;
    public String sexo;
    public String fechanacimiento;
    public String domicilio;
    public String codigopostal;
    public String colonia;
    public String tel;
    public String telalterno;
    public String nombreemergencia;
    public String telemergencia;
    public String parentesco;
    public String fechaingreso;
    public String contacto;
    public String idreclutador;
    public String empresa;
}
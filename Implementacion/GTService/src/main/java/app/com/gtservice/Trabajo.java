package app.com.gtservice;

/**
 * Created by Ivan on 12/2/2015.
 */
public class Trabajo {
    // Labels table name
    public static final String TABLE = "Trabajo";

    // Labels Table Columns names
    public static final String KEY_id = "idTrabajo";
    public static final String KEY_idCandidato = "idCandidato";
    public static final String KEY_NombreEmpresa = "NombreEmpresa";
    public static final String KEY_Puesto = "Puesto";
    public static final String KEY_Duracion = "Duracion";

    // property help us to keep data
    public int id_Trabajo;
    public int idcandidato;
    public String nombreempresa;
    public String puesto;
    public String duracion;
}

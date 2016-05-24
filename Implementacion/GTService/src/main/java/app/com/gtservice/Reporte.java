package app.com.gtservice;

/**
 * Created by Ivan on 11/26/2015.
 */
public class Reporte {
    // Labels table name
    public static final String TABLE = "Reporte";

    // Labels Table Columns names
    public static final String KEY_ID = "idReporte";
    public static final String KEY_IdReclutador = "idReclutador";
    public static final String KEY_Fecha = "Fecha";
    public static final String KEY_Fecha2 = "Fecha2";
    public static final String KEY_Empresa = "Empresa";
    public static final String KEY_CantidadCandidatos = "CantidadCandidatos";

    // property help us to keep data
    public int id_Reporte;
    public String id_Reclutador;
    public String fecha;
    public String fecha2;
    public String empresa;
    public String cantidadcandidatos;

}
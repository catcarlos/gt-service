package app.com.gtservice; /**
 * Created by Ivan on 11/26/2015.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 5;

    // Database Name
    private static final String DATABASE_NAME = "gtservice.db";

    public DBHelper(Context context ) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here

        db.execSQL("DROP TABLE IF EXISTS " + Candidato.TABLE);

        String CREATE_TABLE_CANDIDATOS = "CREATE TABLE " + Candidato.TABLE  + "("
                + Candidato.KEY_id  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Candidato.KEY_CURP + " TEXT, "
                + Candidato.KEY_Folio + " TEXT, "
                + Candidato.KEY_Nombres + " TEXT, "
                + Candidato.KEY_ApellidoPaterno + " TEXT, "
                + Candidato.KEY_ApellidoMaterno + " TEXT, "
                + Candidato.KEY_FechaNacimiento + " TEXT, "
                + Candidato.KEY_Sexo + " TEXT, "
                + Candidato.KEY_Domicilio + " TEXT, "
                + Candidato.KEY_CodigoPostal + " TEXT, "
                + Candidato.KEY_Colonia + " TEXT, "
                + Candidato.KEY_Tel + " TEXT, "
                + Candidato.KEY_TelAlterno + " TEXT, "
                + Candidato.KEY_NombreEmergencia + " TEXT, "
                + Candidato.KEY_TelEmergencia + " TEXT, "
                + Candidato.KEY_Parentesco + " TEXT,"
                + Candidato.KEY_FechaIngreso + " TEXT,"
                + Candidato.KEY_Contacto + " TEXT, "
                + Candidato.KEY_idReclutador + " TEXT, "
                + Candidato.KEY_Empresa +" TEXT )";

        db.execSQL(CREATE_TABLE_CANDIDATOS);

        String CREATE_TABLE_ESTUDIOS = "CREATE TABLE " + Estudios.TABLE  + "("
                + Estudios.KEY_id  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Estudios.KEY_idCandidato + " INTEGER, "
                + Estudios.KEY_NombreEscuela + " TEXT, "
                + Estudios.KEY_Grado + " TEXT, "
                + Estudios.KEY_Titulo + " TEXT )";

        db.execSQL(CREATE_TABLE_ESTUDIOS);

        String CREATE_TABLE_TRABAJO = "CREATE TABLE " + Trabajo.TABLE  + "("
                + Trabajo.KEY_id  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Trabajo.KEY_idCandidato + " TEXT, "
                + Trabajo.KEY_NombreEmpresa + " TEXT, "
                + Trabajo.KEY_Puesto + " TEXT, "
                + Trabajo.KEY_Duracion + " TEXT )";

        db.execSQL(CREATE_TABLE_TRABAJO);

        String CREATA_TABLE_REPORTES = "CREATE TABLE " + Reporte.TABLE  + "("
                + Reporte.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Reporte.KEY_IdReclutador + " TEXT, "
                + Reporte.KEY_Fecha + " TEXT, "
                + Reporte.KEY_Fecha2 + " TEXT, "
                + Reporte.KEY_CantidadCandidatos + " TEXT )";

        db.execSQL(CREATA_TABLE_REPORTES);

        String CREATE_TABLE_RECLUTADOR = "CREATE TABLE " + Reclutador.TABLE  + "("
                + Reclutador.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Reclutador.KEY_NombreReclutador + " TEXT, "
                + Reclutador.KEY_Usuario + " TEXT, "
                + Reclutador.KEY_Password + " TEXT )";

        db.execSQL(CREATE_TABLE_RECLUTADOR);

        String CREATE_TABLE_EMPRESAS = "CREATE TABLE " + Empresas.TABLE  + "("
                + Empresas.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Empresas.KEY_NombreEmpresa + " TEXT, "
                + Empresas.KEY_CantidadVacantes + " TEXT, "
                + Empresas.KEY_Regla1+ " TEXT, "
                + Empresas.KEY_Regla2 + " TEXT )";

        db.execSQL(CREATE_TABLE_EMPRESAS);

        String INSERT = "INSERT INTO Reclutador (NombreReclutador,Usuario,Password) VALUES ('Admin','Admin','admin')";
        db.execSQL(INSERT);
        INSERT = "INSERT INTO Empresas (NombreEmpresa,CantidadVacantes) VALUES ('BOSCH','52')";
        db.execSQL(INSERT);
        INSERT = "INSERT INTO Empresas (NombreEmpresa,CantidadVacantes) VALUES ('HONEYWELL','37')";
        db.execSQL(INSERT);
        INSERT = "INSERT INTO Empresas (NombreEmpresa,CantidadVacantes) VALUES ('UTC','14')";
        db.execSQL(INSERT);
        INSERT = "INSERT INTO Empresas (NombreEmpresa,CantidadVacantes) VALUES ('SKYWORKS','68')";
        db.execSQL(INSERT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Candidato.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Estudios.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Trabajo.TABLE);;
        db.execSQL("DROP TABLE IF EXISTS " + Reporte.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Reclutador.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Empresas.TABLE);

        // Create tables again
        onCreate(db);

    }


}
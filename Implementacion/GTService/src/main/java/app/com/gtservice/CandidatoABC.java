package app.com.gtservice; /**
 * Created by Ivan on 11/26/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class CandidatoABC {
    private DBHelper dbHelper;

    public CandidatoABC(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Candidato candidato) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Candidato.KEY_CURP, candidato.curp);
        values.put(Candidato.KEY_Folio, candidato.folio);
        values.put(Candidato.KEY_Nombres ,candidato.nombres);
        values.put(Candidato.KEY_ApellidoPaterno , candidato.apellidopaterno);
        values.put(Candidato.KEY_ApellidoMaterno ,candidato.apellidomaterno);
        values.put(Candidato.KEY_FechaNacimiento, candidato.fechanacimiento);
        values.put(Candidato.KEY_Sexo , candidato.sexo);
        values.put(Candidato.KEY_Domicilio ,candidato.domicilio);
        values.put(Candidato.KEY_CodigoPostal , candidato.codigopostal);
        values.put(Candidato.KEY_Colonia ,candidato.colonia);
        values.put(Candidato.KEY_Tel , candidato.tel);
        values.put(Candidato.KEY_TelAlterno ,candidato.telalterno);
        values.put(Candidato.KEY_NombreEmergencia , candidato.nombreemergencia);
        values.put(Candidato.KEY_TelEmergencia ,candidato.telemergencia);
        values.put(Candidato.KEY_Parentesco , candidato.parentesco);
        values.put(Candidato.KEY_FechaIngreso , candidato.fechaingreso);
        values.put(Candidato.KEY_Contacto, candidato.contacto);
        values.put(Candidato.KEY_idReclutador , candidato.idreclutador);
        values.put(Candidato.KEY_Empresa, candidato.empresa);

        // Inserting Row
        long id_candidato = db.insert(Candidato.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) id_candidato;
    }

    public void delete(int id_candidato) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Candidato.TABLE, Candidato.KEY_id + "= ?", new String[] { String.valueOf(id_candidato) });
        db.close(); // Closing database connection
    }

    public void update(Candidato candidato) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Candidato.KEY_CURP, candidato.curp);
        values.put(Candidato.KEY_Folio, candidato.folio);
        values.put(Candidato.KEY_Nombres ,candidato.nombres);
        values.put(Candidato.KEY_ApellidoPaterno , candidato.apellidopaterno);
        values.put(Candidato.KEY_ApellidoMaterno ,candidato.apellidomaterno);
        values.put(Candidato.KEY_FechaNacimiento, candidato.fechanacimiento);
        values.put(Candidato.KEY_Sexo , candidato.sexo);
        values.put(Candidato.KEY_Domicilio ,candidato.domicilio);
        values.put(Candidato.KEY_CodigoPostal , candidato.codigopostal);
        values.put(Candidato.KEY_Colonia ,candidato.colonia);
        values.put(Candidato.KEY_Tel , candidato.tel);
        values.put(Candidato.KEY_TelAlterno ,candidato.telalterno);
        values.put(Candidato.KEY_NombreEmergencia , candidato.nombreemergencia);
        values.put(Candidato.KEY_TelEmergencia ,candidato.telemergencia);
        values.put(Candidato.KEY_Parentesco , candidato.parentesco);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Candidato.TABLE, values, Candidato.KEY_id + "= ?", new String[]{String.valueOf(candidato.id_Candidato)});
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>>  getCandidatoList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Candidato.KEY_id + ","
                + Candidato.KEY_CURP + " , "
                + Candidato.KEY_Folio + " , "
                + Candidato.KEY_Nombres + " , "
                + Candidato.KEY_ApellidoPaterno + " , "
                + Candidato.KEY_ApellidoMaterno + " , "
                + Candidato.KEY_FechaNacimiento + " , "
                + Candidato.KEY_Sexo + " , "
                + Candidato.KEY_Domicilio + " , "
                + Candidato.KEY_CodigoPostal + " , "
                + Candidato.KEY_Colonia + " , "
                + Candidato.KEY_Tel + " , "
                + Candidato.KEY_TelAlterno + " , "
                + Candidato.KEY_NombreEmergencia + " , "
                + Candidato.KEY_TelEmergencia + " , "
                + Candidato.KEY_Parentesco +
                " FROM " + Candidato.TABLE
                + " ORDER BY " + Candidato.KEY_id + " DESC" ;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> candidatoList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> candidato = new HashMap<String, String>();
                candidato.put("idCandidato", cursor.getString(cursor.getColumnIndex(Candidato.KEY_id)));
                candidato.put("CURP", cursor.getString(cursor.getColumnIndex(Candidato.KEY_CURP )));
                candidato.put("Folio", cursor.getString(cursor.getColumnIndex(Candidato.KEY_Folio  )));
                candidato.put("Nombres", cursor.getString(cursor.getColumnIndex(Candidato.KEY_Nombres)));
                candidato.put("ApellidoPaterno", cursor.getString(cursor.getColumnIndex(Candidato.KEY_ApellidoPaterno )));
                candidato.put("ApellidoMaterno", cursor.getString(cursor.getColumnIndex(Candidato.KEY_ApellidoMaterno )));
                candidato.put("FechaNacimiento", cursor.getString(cursor.getColumnIndex(Candidato.KEY_FechaNacimiento )));
                candidato.put("Sexo", cursor.getString(cursor.getColumnIndex(Candidato.KEY_Sexo )));
                candidato.put("Domicilio", cursor.getString(cursor.getColumnIndex(Candidato.KEY_Domicilio )));
                candidato.put("CodigoPostal", cursor.getString(cursor.getColumnIndex(Candidato.KEY_CodigoPostal  )));
                candidato.put("Colonia", cursor.getString(cursor.getColumnIndex(Candidato.KEY_Colonia  )));
                candidato.put("Tel", cursor.getString(cursor.getColumnIndex(Candidato.KEY_Tel  )));
                candidato.put("TelAlterno", cursor.getString(cursor.getColumnIndex(Candidato.KEY_TelAlterno  )));
                candidato.put("NombreEmergencia", cursor.getString(cursor.getColumnIndex(Candidato.KEY_NombreEmergencia  )));
                candidato.put("TelEmergencia", cursor.getString(cursor.getColumnIndex(Candidato.KEY_TelEmergencia  )));
                candidato.put("Parentesco", cursor.getString(cursor.getColumnIndex(Candidato.KEY_Parentesco  )));

                candidatoList.add(candidato);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return candidatoList;

    }

    public Candidato getCandidatoById(int ID){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Candidato.KEY_id + ","
                + Candidato.KEY_CURP + " , "
                + Candidato.KEY_Folio + " , "
                + Candidato.KEY_Nombres + " , "
                + Candidato.KEY_ApellidoPaterno + " , "
                + Candidato.KEY_ApellidoMaterno + " , "
                + Candidato.KEY_FechaNacimiento + " , "
                + Candidato.KEY_Sexo + " , "
                + Candidato.KEY_Domicilio + " , "
                + Candidato.KEY_CodigoPostal + " , "
                + Candidato.KEY_Colonia + " , "
                + Candidato.KEY_Tel + " , "
                + Candidato.KEY_TelAlterno + " , "
                + Candidato.KEY_NombreEmergencia + " , "
                + Candidato.KEY_TelEmergencia + " , "
                + Candidato.KEY_Parentesco +
                " FROM " + Candidato.TABLE
                + " WHERE " +
                Candidato.KEY_id + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        Candidato candidato = new Candidato();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(ID) } );

        if (cursor.moveToFirst()) {
            do {
                candidato.id_Candidato =cursor.getInt(cursor.getColumnIndex(Candidato.KEY_id));
                candidato.curp  =cursor.getString(cursor.getColumnIndex(Candidato.KEY_CURP));
                candidato.folio  =cursor.getString(cursor.getColumnIndex(Candidato.KEY_Folio ));
                candidato.nombres= cursor.getString(cursor.getColumnIndex(Candidato.KEY_Nombres));
                candidato.apellidopaterno= cursor.getString(cursor.getColumnIndex(Candidato.KEY_ApellidoPaterno ));
                candidato.apellidomaterno=cursor.getString(cursor.getColumnIndex(Candidato.KEY_ApellidoMaterno ));
                candidato.fechanacimiento  =cursor.getString(cursor.getColumnIndex(Candidato.KEY_FechaNacimiento ));
                candidato.sexo= cursor.getString(cursor.getColumnIndex(Candidato.KEY_Sexo ));
                candidato.domicilio= cursor.getString(cursor.getColumnIndex(Candidato.KEY_Domicilio ));
                candidato.codigopostal= cursor.getString(cursor.getColumnIndex(Candidato.KEY_CodigoPostal  ));
                candidato.colonia= cursor.getString(cursor.getColumnIndex(Candidato.KEY_Colonia  ));
                candidato.tel=cursor.getString(cursor.getColumnIndex(Candidato.KEY_Tel  ));
                candidato.telalterno= cursor.getString(cursor.getColumnIndex(Candidato.KEY_TelAlterno  ));
                candidato.nombreemergencia= cursor.getString(cursor.getColumnIndex(Candidato.KEY_NombreEmergencia  ));
                candidato.telemergencia= cursor.getString(cursor.getColumnIndex(Candidato.KEY_TelEmergencia  ));
                candidato.parentesco= cursor.getString(cursor.getColumnIndex(Candidato.KEY_Parentesco  ));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return candidato;
    }

}

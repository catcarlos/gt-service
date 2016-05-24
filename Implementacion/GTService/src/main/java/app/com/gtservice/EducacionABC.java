package app.com.gtservice; /**
 * Created by Ivan on 11/26/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class EducacionABC {
    private DBHelper dbHelper;

    public EducacionABC(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Estudios estudios) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Estudios.KEY_idCandidato, estudios.idcandidato);
        values.put(Estudios.KEY_NombreEscuela ,estudios.nombreescuela);
        values.put(Estudios.KEY_Grado , estudios.grado);
        values.put(Estudios.KEY_Titulo ,estudios.titulo);

        // Inserting Row
        long id_estudio = db.insert(Estudios.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) id_estudio;
    }

    public void delete(int id_estudio) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Estudios.TABLE, Estudios.KEY_id + "= ?", new String[] { String.valueOf(id_estudio) });
        db.close(); // Closing database connection
    }

    public void update(Estudios estudios) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Estudios.KEY_id, estudios.id_Estudio);
        values.put(Estudios.KEY_idCandidato, estudios.idcandidato);
        values.put(Estudios.KEY_NombreEscuela ,estudios.nombreescuela);
        values.put(Estudios.KEY_Grado , estudios.grado);
        values.put(Estudios.KEY_Titulo ,estudios.titulo);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Estudios.TABLE, values, Estudios.KEY_id + "= ?", new String[]{String.valueOf(estudios.id_Estudio)});
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>>  getEstudiosList(int idCandidato) {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Estudios.KEY_id + ","
                + Estudios.KEY_idCandidato + " , "
                + Estudios.KEY_NombreEscuela + " , "
                + Estudios.KEY_Grado + " , "
                + Estudios.KEY_Titulo + "  "+
                " FROM " + Estudios.TABLE
                + " WHERE " +
                Estudios.KEY_idCandidato + "=?" ;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> estudiosList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(idCandidato) } );
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> estudios = new HashMap<String, String>();
                estudios.put("idEstudio", cursor.getString(cursor.getColumnIndex(Estudios.KEY_id)));
                estudios.put("idCandidato", cursor.getString(cursor.getColumnIndex(Estudios.KEY_idCandidato )));
                estudios.put("NombreEscuela", cursor.getString(cursor.getColumnIndex(Estudios.KEY_NombreEscuela  )));
                estudios.put("Grado", cursor.getString(cursor.getColumnIndex(Estudios.KEY_Grado)));
                estudios.put("Titulo", cursor.getString(cursor.getColumnIndex(Estudios.KEY_Titulo )));

                estudiosList.add(estudios);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return estudiosList;

    }

    public Estudios getEstudiosById(int ID){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Estudios.KEY_id + ","
                + Estudios.KEY_idCandidato + " , "
                + Estudios.KEY_NombreEscuela + " , "
                + Estudios.KEY_Grado + " , "
                + Estudios.KEY_Titulo + "  "+
                " FROM " + Estudios.TABLE
                + " WHERE " +
                Estudios.KEY_id + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        Estudios estudios = new Estudios();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(ID) } );

        if (cursor.moveToFirst()) {
            do {
                estudios.id_Estudio =cursor.getInt(cursor.getColumnIndex(Estudios.KEY_id));
                estudios.idcandidato  = Integer.valueOf(cursor.getString(cursor.getColumnIndex(Estudios.KEY_idCandidato)));
                estudios.nombreescuela  =cursor.getString(cursor.getColumnIndex(Estudios.KEY_NombreEscuela ));
                estudios.grado= cursor.getString(cursor.getColumnIndex(Estudios.KEY_Grado));
                estudios.titulo= cursor.getString(cursor.getColumnIndex(Estudios.KEY_Titulo ));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return estudios;
    }

}

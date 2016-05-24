package app.com.gtservice;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Anacaren on 05/12/2015.
 */
public class InicioDeSesionA {
    private DBHelper dbHelper;

    public InicioDeSesionA(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<HashMap<String, String>> getReclutadorList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Reclutador.KEY_ID + ","
                + Reclutador.KEY_NombreReclutador + " , "
                + Reclutador.KEY_Usuario + " , "

                + Reclutador.KEY_Password +
                " FROM " +Reclutador.TABLE;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> reclutadorList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> reclutador = new HashMap<String, String>();
                reclutador.put("idreclutador", cursor.getString(cursor.getColumnIndex(Reclutador.KEY_ID)));
                reclutador.put("NombreReclutador", cursor.getString(cursor.getColumnIndex(Reclutador.KEY_NombreReclutador )));
                reclutador.put("Usuario", cursor.getString(cursor.getColumnIndex(Reclutador.KEY_Usuario)));
                reclutador.put("Password", cursor.getString(cursor.getColumnIndex(Reclutador.KEY_Password)));

                reclutadorList.add(reclutador);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return reclutadorList;

    }

    public Reclutador getReclutadorById(int ID){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Reclutador.KEY_ID + ","
                + Reclutador.KEY_NombreReclutador+ " , "
                + Reclutador.KEY_Usuario + " , "

                + Reclutador.KEY_Password +
                " FROM " + Reclutador.TABLE
                + " WHERE " +
                Reclutador.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        Reclutador reclutador = new Reclutador();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(ID) } );

        if (cursor.moveToFirst()) {
            do {
                reclutador.id_Reclutador =cursor.getInt(cursor.getColumnIndex(Reclutador.KEY_ID));
                reclutador.nombrereclutador  =cursor.getString(cursor.getColumnIndex(Reclutador.KEY_NombreReclutador ));
                reclutador.usuario= cursor.getString(cursor.getColumnIndex(Reclutador.KEY_Usuario));
                reclutador.password= cursor.getString(cursor.getColumnIndex(Reclutador.KEY_Password ));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return reclutador;
    }

}




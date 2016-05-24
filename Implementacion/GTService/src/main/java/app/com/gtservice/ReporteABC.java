package app.com.gtservice; 

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class ReporteABC {
    private DBHelper dbHelper;

    public ReporteABC(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Reporte reporte) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Reporte.KEY_ID, reporte.id_Reporte);
        values.put(Reporte.KEY_IdReclutador ,reporte.id_Reclutador);
        values.put(Reporte.KEY_Fecha , reporte.fecha);
        values.put(Reporte.KEY_Fecha2 , reporte.fecha2);
        values.put(Reporte.KEY_CantidadCandidatos ,reporte.cantidadcandidatos);


        // Inserting Row
        long id_reporte = db.insert(Reporte.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) id_reporte;
    }

    public void delete(int id_reporte) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Reporte.TABLE, Reporte.KEY_ID + "= ?", new String[] { String.valueOf(id_reporte) });
        db.close(); // Closing database connection
    }

    public void update(Reporte reporte) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Reporte.KEY_ID, reporte.id_Reporte);
        values.put(Reporte.KEY_IdReclutador ,reporte.id_Reclutador);
        values.put(Reporte.KEY_Fecha , reporte.fecha);
        values.put(Reporte.KEY_Fecha2 , reporte.fecha2);
        values.put(Reporte.KEY_CantidadCandidatos ,reporte.cantidadcandidatos);


        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Reporte.TABLE, values, Reporte.KEY_ID + "= ?", new String[]{String.valueOf(reporte.id_Reporte)});
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>>  getReporteList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT TOP 10 " +
                Reporte.KEY_ID + ","
                + Reporte.KEY_IdReclutador + " , "
                + Reporte.KEY_Fecha + " , "
                + Reporte.KEY_Fecha2 + " , "
                + Reporte.KEY_CantidadCandidatos +
                " FROM " + Reporte.TABLE;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> reporteList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> reporte = new HashMap<String, String>();
                reporte.put("idReporte", cursor.getString(cursor.getColumnIndex(Reporte.KEY_ID)));
                reporte.put("idReclutador", cursor.getString(cursor.getColumnIndex(Reporte.KEY_IdReclutador )));
                reporte.put("Fecha", cursor.getString(cursor.getColumnIndex(Reporte.KEY_Fecha)));
                reporte.put("Fecha2", cursor.getString(cursor.getColumnIndex(Reporte.KEY_Fecha2)));
                reporte.put("CantidadCandidatos", cursor.getString(cursor.getColumnIndex(Reporte.KEY_CantidadCandidatos )));
                reporteList.add(reporte);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return reporteList;

    }

    public Reporte getReporteById(int ID){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Reporte.KEY_ID + ","
                + Reporte.KEY_IdReclutador + " , "
                + Reporte.KEY_Fecha + " , "
                + Reporte.KEY_Fecha2 + " , "
                + Reporte.KEY_CantidadCandidatos +
                " FROM " + Reporte.TABLE
                + " WHERE " +
                Reporte.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        Reporte reporte = new Reporte();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(ID) } );

        if (cursor.moveToFirst()) {
            do {
                reporte.id_Reporte =cursor.getInt(cursor.getColumnIndex(Reporte.KEY_ID));
                reporte.id_Reclutador  =cursor.getString(cursor.getColumnIndex(Reporte.KEY_IdReclutador));
                reporte.fecha= cursor.getString(cursor.getColumnIndex(Reporte.KEY_Fecha));
                reporte.fecha2= cursor.getString(cursor.getColumnIndex(Reporte.KEY_Fecha2));
                reporte.cantidadcandidatos=cursor.getString(cursor.getColumnIndex(Reporte.KEY_CantidadCandidatos));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return reporte;
    }

}

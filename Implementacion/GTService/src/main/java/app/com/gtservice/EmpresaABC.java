package app.com.gtservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class EmpresaABC {
    private DBHelper dbHelper;

    public EmpresaABC(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Empresas Empresa) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Empresas.KEY_NombreEmpresa ,Empresa.NombreEmpresa);
        values.put(Empresas.KEY_CantidadVacantes ,Empresa.CantidadVacantes);
        values.put(Empresas.KEY_Regla1,Empresa.Regla1);
        values.put(Empresas.KEY_Regla2 , Empresa.Regla2);
        
        // Inserting Row
        long idEmpresa = db.insert(Empresas.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) idEmpresa;
    }

    public void delete(int idEmpresa) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Empresas.TABLE, Empresas.KEY_ID + "= ?", new String[] { String.valueOf(idEmpresa) });
        db.close(); // Closing database connection
    }

    public void update(Empresas Empresa) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Empresas.KEY_NombreEmpresa ,Empresa.NombreEmpresa);
        values.put(Empresas.KEY_CantidadVacantes , Empresa.CantidadVacantes);
        values.put(Empresas.KEY_Regla1,Empresa.Regla1);
        values.put(Empresas.KEY_Regla2 , Empresa.Regla2);


        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Empresas.TABLE, values, Empresas.KEY_ID + "= ?", new String[]{String.valueOf(Empresa.idEmpresa)});
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>>  getEmpresasList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Empresas.KEY_ID + ","
                + Empresas.KEY_NombreEmpresa + " , "
                + Empresas.KEY_CantidadVacantes + " , "
                + Empresas.KEY_Regla1 + " , "
                + Empresas.KEY_Regla2 + " "
                + " FROM " + Empresas.TABLE;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> EmpresaList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> Empresa = new HashMap<String, String>();
                Empresa.put("idEmpresa", cursor.getString(cursor.getColumnIndex(Empresas.KEY_ID)));
                Empresa.put("NombreEmpresa", cursor.getString(cursor.getColumnIndex(Empresas.KEY_NombreEmpresa )));
                Empresa.put("CantidadVacantes", cursor.getString(cursor.getColumnIndex(Empresas.KEY_CantidadVacantes)));
                Empresa.put("Regla1", cursor.getString(cursor.getColumnIndex(Empresas.KEY_Regla1 )));
                
                Empresa.put("Regla2", cursor.getString(cursor.getColumnIndex(Empresas.KEY_Regla2 )));
                EmpresaList.add(Empresa);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return EmpresaList;

    }

    public Empresas getEmpresasById(int ID){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Empresas.KEY_ID + ","
                + Empresas.KEY_NombreEmpresa + " , "
                + Empresas.KEY_CantidadVacantes + " , "
                + Empresas.KEY_Regla1 + " , "
                + Empresas.KEY_Regla2 + " "
                + " FROM " + Empresas.TABLE+ " WHERE " + Empresas.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        Empresas Empresa= new Empresas();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(ID) } );

        if (cursor.moveToFirst()) {
            do {
                Empresa.idEmpresa=cursor.getInt(cursor.getColumnIndex(Empresas.KEY_ID));
                Empresa.NombreEmpresa=cursor.getString(cursor.getColumnIndex(Empresas.KEY_NombreEmpresa ));
                Empresa.CantidadVacantes= cursor.getInt(cursor.getColumnIndex(Empresas.KEY_CantidadVacantes));
                Empresa.Regla1= cursor.getString(cursor.getColumnIndex(Empresas.KEY_Regla1));
                Empresa.Regla2= cursor.getString(cursor.getColumnIndex(Empresas.KEY_Regla2));
                            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return Empresa;
    }

}


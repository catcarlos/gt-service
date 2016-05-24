package app.com.gtservice;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityInicioSesion extends AppCompatActivity {


    EditText editUsuario;
    EditText editPassword;
    TextView txtError;
    private DBHelper dbHelper;
    String nombre = "";
    int id =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        dbHelper = new DBHelper(this);
        findViewById(R.id.btnIngresar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editUsuario = (EditText) findViewById(R.id.editUsuario);
                editPassword = (EditText) findViewById(R.id.editPassword);
                int check =0;
                check = getUser(editUsuario.getText().toString(), editPassword.getText().toString());
                if(check>=1){
                    Intent intent = new Intent(ActivityInicioSesion.this,Drawer.class);
                    intent.putExtra("id_reclutador",id);
                    intent.putExtra("nombre_reclutador",nombre);
                    startActivity(intent);
                }
                else{
                    txtError = (TextView) findViewById(R.id.txtError);
                    txtError.setText("Usuario o contraseña invalida");
                    txtError.setVisibility(View.VISIBLE);
                    txtError.setTextColor(Color.RED);
                }
            }
        });

    }

    public int getUser(String usuario, String password) {
        //Open connection to read only
        int count = 0;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT COUNT(NombreReclutador) FROM Reclutador WHERE Usuario='"
                + usuario + "' AND Password = '" + password + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            count= cursor.getInt(0);
        }

        selectQuery =  "SELECT idReclutador FROM Reclutador WHERE Usuario='"
                + usuario + "' AND Password = '" + password + "'";

        cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            id= cursor.getInt(0);
        }

        selectQuery =  "SELECT NombreReclutador FROM Reclutador WHERE Usuario='"
                + usuario + "' AND Password = '" + password + "'";

        cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            nombre= cursor.getString(0);
        }

        cursor.close();
        db.close();
        return count;

    }

}

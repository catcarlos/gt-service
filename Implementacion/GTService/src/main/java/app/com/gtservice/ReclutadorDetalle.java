package app.com.gtservice; /**
 * Created by Ivan on 11/26/2015.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReclutadorDetalle extends AppCompatActivity implements View.OnClickListener{

    Button btnSave ,  btnDelete;
    Button btnClose;
    EditText editNombreReclutador;
    EditText editUsuario;
    EditText editPassword;

    private int _id_Reclutador=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclutador_detalle);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);

        editNombreReclutador = (EditText) findViewById(R.id.editNombreReclutador);
        editUsuario= (EditText) findViewById(R.id.editUsuario);
        editPassword = (EditText) findViewById(R.id.editPassword);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);


        _id_Reclutador =0;
        Intent intent = getIntent();
        _id_Reclutador =intent.getIntExtra("id_reclutador", 0);

        ReclutadorABC repo = new ReclutadorABC(this);
        Reclutador reclutador= new Reclutador();
        reclutador = repo.getReclutadorById(_id_Reclutador);

        editNombreReclutador.setText(reclutador.nombrereclutador);
        editUsuario.setText(reclutador.usuario);
        editPassword.setText(reclutador.password);

}



    public void onClick(View view) {
        if (view == findViewById(R.id.btnSave)){
            ReclutadorABC repo = new ReclutadorABC(this);
            Reclutador reclutador = new Reclutador();
            reclutador.nombrereclutador= editNombreReclutador.getText().toString();
            reclutador.usuario=editUsuario.getText().toString();
            reclutador.password=editPassword.getText().toString();

            reclutador.id_Reclutador=_id_Reclutador;

            if (_id_Reclutador==0){
                _id_Reclutador = repo.insert(reclutador);

                Toast.makeText(this,"activities.reclutador ingresado exitosamente",Toast.LENGTH_SHORT).show();
            }else{

                repo.update(reclutador);
                Toast.makeText(this,"Perfil de reclutador actualizado correctamente",Toast.LENGTH_SHORT).show();
            }
        }else if (view== findViewById(R.id.btnDelete)){
           ReclutadorABC repo = new ReclutadorABC(this);
            repo.delete(_id_Reclutador);
            Toast.makeText(this, "Perfil de reclutador eliminado", Toast.LENGTH_SHORT);
            finish();
        }else if (view== findViewById(R.id.btnClose)){
            finish();
        }


    }

}
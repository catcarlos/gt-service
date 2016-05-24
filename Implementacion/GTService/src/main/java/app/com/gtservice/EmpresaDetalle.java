package app.com.gtservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EmpresaDetalle extends AppCompatActivity implements View.OnClickListener{

    Button bAceptar ,  bBorrar;
    Button bClose;
    EditText editNombreEmpresa;
    EditText editCantidadVacantes;
    private int _idEmpresa=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_detalle);

        bAceptar = (Button) findViewById(R.id.btnSave);
        bBorrar= (Button) findViewById(R.id.btnDelete);
        bClose = (Button) findViewById(R.id.btnClose);

        editNombreEmpresa = (EditText) findViewById(R.id.editNombreEmpresa);
        editCantidadVacantes = (EditText) findViewById(R.id.editCantidadVacantes);
        
        bAceptar.setOnClickListener(this);
        bBorrar.setOnClickListener(this);
        bClose.setOnClickListener(this);


        _idEmpresa=0;
        Intent intent = getIntent();
        _idEmpresa =intent.getIntExtra("idEmpresa", 0);
        EmpresaABC repo = new EmpresaABC(this);
        Empresas Empresa= new Empresas();
        Empresa = repo.getEmpresasById(_idEmpresa);

        editNombreEmpresa.setText(Empresa.NombreEmpresa);
        editCantidadVacantes.setText(String.valueOf(Empresa.CantidadVacantes));
    }



    public void onClick(View view) {
        if (view == findViewById(R.id.btnSave)){
            EmpresaABC repo = new EmpresaABC(this);

            Empresas Empresa= new Empresas();
           
            Empresa.NombreEmpresa= editNombreEmpresa.getText().toString();

            Empresa.CantidadVacantes = Integer.valueOf(editCantidadVacantes.getText().toString());
            
            Empresa.idEmpresa=_idEmpresa;

            if (_idEmpresa==0){
                _idEmpresa = repo.insert(Empresa);

                Toast.makeText(this,"Empresas ingresado exitosamente",Toast.LENGTH_SHORT).show();
            }else{

                repo.update(Empresa);
                Toast.makeText(this,"Perfil de Empresas actualizado correctamente",Toast.LENGTH_SHORT).show();
            }
        }else if (view== findViewById(R.id.btnDelete)){
            EmpresaABC repo = new EmpresaABC(this);
            repo.delete(_idEmpresa);
            Toast.makeText(this, "Perfil de Empresas eliminado", Toast.LENGTH_SHORT);
            finish();
        }else if (view== findViewById(R.id.btnClose)){
            finish();
        }


    }

}

package app.com.gtservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ivan on 12/4/2015.
 */
public class EstudioDetalle extends AppCompatActivity implements View.OnClickListener {

    Button btnSave, btnDelete;
    Button btnClose;
    EditText editNombre;
    Spinner editGrado;
    EditText editTitulo;

    private int _id_estudios = 0;
    private int _id_candidato = 0;

    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date dateobj = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudios_detalle);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);

        editNombre = (EditText) findViewById(R.id.editNombre);
        editGrado = (Spinner) findViewById(R.id.editGrado);
        editTitulo = (EditText) findViewById(R.id.editTitulo);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);


        _id_estudios = 0;
        _id_candidato = 0;
        Intent intent = getIntent();
        _id_estudios = intent.getIntExtra("id_estudio", 0);
        _id_candidato = intent.getIntExtra("id_candidato", 0);

        EducacionABC repo = new EducacionABC(this);
        Estudios estudios = new Estudios();
        estudios = repo.getEstudiosById(_id_estudios);

        editNombre.setText(estudios.nombreescuela);
        String [] resourceArray = getResources().getStringArray(R.array.grado_estudio);
        editGrado.setSelection(getArrayIndex(resourceArray, estudios.grado));

        editTitulo.setText(estudios.titulo);
    }

    private int getArrayIndex(String[] array, String myString)
    {
        int index = 0;

        for (int i=0;i<array.length;i++){
            if (array[i].equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
    }


    public void onClick(View view) {
        if (view == findViewById(R.id.btnSave)) {
            EducacionABC repo = new EducacionABC(this);
            Estudios estudios = new Estudios();

            estudios.nombreescuela = editNombre.getText().toString();
            estudios.grado = String.valueOf(editGrado.getSelectedItem()).toUpperCase();
            estudios.titulo = editTitulo.getText().toString();
            estudios.id_Estudio = _id_estudios;

            if (_id_estudios == 0) {
                estudios.idcandidato = _id_candidato;
                _id_estudios = repo.insert(estudios);

                Toast.makeText(this, "Registro ingresado exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                repo.update(estudios);
                Toast.makeText(this, "Registro de educación actualizado", Toast.LENGTH_SHORT).show();
            }
        } else if (view == findViewById(R.id.btnDelete)) {
            EducacionABC repo = new EducacionABC(this);
            repo.delete(_id_estudios);
            Toast.makeText(this, "Perfil de candidato eliminado", Toast.LENGTH_SHORT);
            finish();
        } else if (view == findViewById(R.id.btnClose)) {
            finish();
        }
    }
}


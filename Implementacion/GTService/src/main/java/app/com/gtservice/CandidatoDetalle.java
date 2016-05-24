package app.com.gtservice; /**
 * Created by Ivan on 11/26/2015.
 */

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CandidatoDetalle extends AppCompatActivity implements View.OnClickListener{

    Button btnSave ,  btnDelete;
    Button btnClose, btnEstudios, btnTrabajos;
    EditText editCURP;
    EditText editNombres;
    EditText editApellidoPaterno;
    EditText editApellidoMaterno;
    Spinner editSexo;
    TextView editFechaNacimiento;
    EditText editDomicilio;
    EditText editCodigoPostal;
    EditText editColonia;
    EditText editTel;
    EditText editTelAlterno;
    EditText editNombreEmergencia;
    EditText editTelEmergencia;
    Spinner editParentesco;

    private int _id_Candidato=0;
    private int _id_Reclutador=0;

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Date dateobj = new Date();

    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidato_detalle);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);
        btnEstudios = (Button) findViewById(R.id.btnEstudios);
        btnTrabajos = (Button) findViewById(R.id.btnTrabajos);

        editCURP = (EditText) findViewById(R.id.editCURP);
        editNombres = (EditText) findViewById(R.id.editNombres);
        editApellidoPaterno = (EditText) findViewById(R.id.editApellidoPaterno);
        editApellidoMaterno = (EditText) findViewById(R.id.editApellidoMaterno);
        editFechaNacimiento = (TextView) findViewById(R.id.editFechaNacimiento);
        editSexo = (Spinner) findViewById(R.id.editSexo);
        editDomicilio = (EditText) findViewById(R.id.editDomicilio);
        editCodigoPostal = (EditText) findViewById(R.id.editCodigoPostal);
        editColonia = (EditText) findViewById(R.id.editColonia);
        editTel = (EditText) findViewById(R.id.editTel);
        editTelAlterno = (EditText) findViewById(R.id.editTelAlterno);
        editNombreEmergencia = (EditText) findViewById(R.id.editNombreEmergencia);
        editTelEmergencia = (EditText) findViewById(R.id.editTelEmergencia);
        editParentesco = (Spinner) findViewById(R.id.editParentesco);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        btnEstudios.setOnClickListener(this);
        editFechaNacimiento.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(CandidatoDetalle.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        _id_Candidato =0;
        Intent intent = getIntent();
        _id_Candidato =intent.getIntExtra("id_candidato", 0);
        _id_Reclutador=intent.getIntExtra("id_reclutador",0);

        CandidatoABC repo = new CandidatoABC(this);
        Candidato candidato = new Candidato();
        candidato = repo.getCandidatoById(_id_Candidato);

        editCURP.setText(candidato.curp);
        editNombres.setText(candidato.nombres);
        editApellidoPaterno.setText(candidato.apellidopaterno);
        editApellidoMaterno.setText(candidato.apellidomaterno);
        editFechaNacimiento.setText(candidato.fechanacimiento);

        String [] resourceArray = getResources().getStringArray(R.array.sexo_spinner);
        editSexo.setSelection(getArrayIndex(resourceArray, candidato.sexo));

        editDomicilio.setText(candidato.domicilio);
        editCodigoPostal.setText(candidato.codigopostal);
        editColonia.setText(candidato.colonia);
        editTel.setText(candidato.tel);
        editTelAlterno.setText(candidato.telalterno);
        editNombreEmergencia.setText(candidato.nombreemergencia);
        editTelEmergencia.setText(candidato.telemergencia);

        resourceArray = getResources().getStringArray(R.array.parentesco_spinner);
        editParentesco.setSelection(getArrayIndex(resourceArray,candidato.parentesco));
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
        if (view == findViewById(R.id.btnSave)){
            CandidatoABC repo = new CandidatoABC(this);
            Candidato candidato = new Candidato();
            candidato.curp= editCURP.getText().toString().toUpperCase();

            String folioAux = editCURP.getText().toString().toUpperCase();
            int i = folioAux.length();
            folioAux = String.valueOf(folioAux.charAt(0)) + String.valueOf(folioAux.charAt(1)) +
                    String.valueOf(folioAux.charAt(2)) + String.valueOf(folioAux.charAt(i - 1)) +
                    String.valueOf(folioAux.charAt((i-2)));

            candidato.folio= folioAux;
            candidato.nombres = editNombres.getText().toString().toUpperCase();
            candidato.nombres = candidato.nombres.toUpperCase();
            candidato.apellidopaterno = editApellidoPaterno.getText().toString();
            candidato.apellidopaterno = candidato.apellidopaterno.toUpperCase();
            candidato.apellidomaterno = editApellidoMaterno.getText().toString();
            candidato.apellidomaterno = candidato.apellidomaterno.toUpperCase();
            candidato.sexo =String.valueOf(editSexo.getSelectedItem()).toUpperCase();
            candidato.fechanacimiento = editFechaNacimiento.getText().toString().toUpperCase();
            candidato.domicilio = editDomicilio.getText().toString().toUpperCase();
            candidato.domicilio = candidato.domicilio.toUpperCase();
            candidato.codigopostal = editCodigoPostal.getText().toString().toUpperCase();
            candidato.tel = editTel.getText().toString().toUpperCase();
            candidato.telalterno = editTelAlterno.getText().toString().toUpperCase();
            candidato.nombreemergencia = editNombreEmergencia.getText().toString().toUpperCase();
            candidato.nombreemergencia = candidato.nombreemergencia.toUpperCase();
            candidato.telemergencia = editTelEmergencia.getText().toString().toUpperCase();
            candidato.parentesco = String.valueOf(editParentesco.getSelectedItem()).toUpperCase();
            candidato.id_Candidato =_id_Candidato;

            if (_id_Candidato==0){
                candidato.fechaingreso = df.format(dateobj).toString();
                candidato.contacto = String.valueOf(0);
                candidato.idreclutador = String.valueOf(_id_Reclutador);
                candidato.empresa = "BOSCH";
                _id_Candidato = repo.insert(candidato);

                Toast.makeText(this,"Candidato ingresado exitosamente",Toast.LENGTH_SHORT).show();
            }else{
                repo.update(candidato);
                Toast.makeText(this,"Perfil de candidato actualizado",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this,ActivityEducacion.class);
                intent.putExtra("id_candidato", 0);
                startActivity(intent);
            }
        }else if (view== findViewById(R.id.btnDelete)){
            CandidatoABC repo = new CandidatoABC(this);
            repo.delete(_id_Candidato);
            Toast.makeText(this, "Perfil de candidato eliminado", Toast.LENGTH_SHORT);
            finish();

        }else if (view== findViewById(R.id.btnClose)){
            finish();
        }

        else if (view == findViewById(R.id.btnEstudios)){
            Intent i = new Intent(Intent.ACTION_DIAL);
            String p = String.format("tel:" + (editTel.getText().toString()));
            i.setData(Uri.parse(p));
            startActivity(i);
        }

        else if (view == findViewById(R.id.btnTrabajos)){
        }
    }

    private void updateLabel() {

        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editFechaNacimiento.setText(sdf.format(myCalendar.getTime()));
    }

}
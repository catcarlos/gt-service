package app.com.gtservice; 

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ReporteDetalle extends AppCompatActivity implements View.OnClickListener {

    Button btnSave, btnDelete;
    Button btnClose;
    EditText editIdReclutador;
    TextView editFecha;
    TextView editFecha2;
    private int _id_Reporte = 0;

    Calendar myCalendar = Calendar.getInstance();
    Calendar myCalendar2 = Calendar.getInstance();

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

    DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar2.set(Calendar.YEAR, year);
            myCalendar2.set(Calendar.MONTH, monthOfYear);
            myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel2();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_detalle);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);

        editIdReclutador = (EditText) findViewById(R.id.editIdReclutador);
        editFecha = (TextView) findViewById(R.id.editFecha);
        editFecha2 = (TextView) findViewById(R.id.editFecha2);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);

        editFecha.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(ReporteDetalle.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        editFecha2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(ReporteDetalle.this, date2, myCalendar2
                        .get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH),
                        myCalendar2.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        _id_Reporte = 0;
        Intent intent = getIntent();
        _id_Reporte = intent.getIntExtra("id_reporte", 0);
        ReporteABC repo = new ReporteABC(this);
        Reporte reporte = new Reporte();
        reporte = repo.getReporteById(_id_Reporte);

        editIdReclutador.setText(reporte.id_Reclutador);
        editFecha.setText(reporte.fecha);
        editFecha2.setText(reporte.fecha2);
    }


    public void onClick(View view) {
        if (view == findViewById(R.id.btnSave)) {
            ReporteABC repo = new ReporteABC(this);
            Reporte reporte = new Reporte();
            reporte.id_Reclutador = editIdReclutador.getText().toString();
            reporte.fecha = editFecha.getText().toString();
            reporte.fecha = editFecha2.getText().toString();
            reporte.id_Reporte = _id_Reporte;

            if (_id_Reporte == 0) {
                Toast.makeText(this, "Reporte generado exitosamente", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, ActivityReporteG.class);
                intent.putExtra("id_reclutador", editIdReclutador.getText().toString());
                intent.putExtra("fecha", editFecha.getText().toString());
                intent.putExtra("fecha2", editFecha2.getText().toString());
                startActivity(intent);
            }
        }
        else if (view == findViewById(R.id.btnDelete)) {
            ReporteABC repo = new ReporteABC(this);
            Toast.makeText(this, "Reporte eliminado", Toast.LENGTH_SHORT);
            finish();
        } else if (view == findViewById(R.id.btnClose)) {
            finish();
        }
    }

    private void updateLabel() {

        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editFecha.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateLabel2() {

        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editFecha2.setText(sdf.format(myCalendar2.getTime()));
    }
}

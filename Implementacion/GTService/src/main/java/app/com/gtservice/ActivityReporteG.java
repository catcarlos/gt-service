package app.com.gtservice;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityReporteG extends AppCompatActivity {
    private String _id_reclutador = "0";
    TextView txtEmpresas;
    private DBHelper dbHelper;
    String fecha, fecha2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_reporte);

        dbHelper = new DBHelper(this);

        Intent intent = getIntent();
        _id_reclutador =intent.getStringExtra("id_reclutador");
        fecha = intent.getStringExtra("fecha");
        fecha2 = intent.getStringExtra("fecha2");

        GraphView graph = (GraphView) findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, getEmpresaCount("BOSCH", fecha, fecha2)),
                new DataPoint(1, getEmpresaCount("Chromalloy", fecha, fecha2)),
                new DataPoint(2, getEmpresaCount("UTC", fecha, fecha2))
        });
        graph.addSeries(series);

// styling
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
            }
        });

        series.setSpacing(10);
        series.setTitle("Reporte de Fecha #1 a Fecha #2");

// draw values on top
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);
//series.setValuesOnTopSize(50);
        txtEmpresas = (TextView) findViewById(R.id.txtEmpresas);
        txtEmpresas.setText("1.- BOSCH\n2.- Honeywell\n3.- UTC\n4.- Chromalloy\n5.- ManPower\n6.- Skyworks\n7.- Pilkington");
    }

    public int getEmpresaCount(String empresa, String fecha, String fecha2) {
        //Open connection to read only
        int count = 0;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT COUNT(Empresa) FROM Candidato WHERE idReclutador="
                + _id_reclutador + " AND Empresa = '" + empresa + "' AND FechaIngreso BETWEEN '"
                +  fecha + "' AND '" + fecha2 +"'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            count= cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return count;

    }

}


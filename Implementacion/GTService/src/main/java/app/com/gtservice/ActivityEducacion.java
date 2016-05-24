package app.com.gtservice;

/**
 * Created by Ivan on 12/2/2015.
 */
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityEducacion extends ListActivity  implements View.OnClickListener{

    Button btnGetAll;
    FloatingActionButton btnAdd;
    TextView id_Estudio;
    private int _id_candidato = 0;

    @Override
    public void onClick(View view) {
        _id_candidato = 0;
        Intent intent2 = getIntent();
        _id_candidato = intent2.getIntExtra("id_candidato", 0);

        if (view== findViewById(R.id.btnAdd)){

            Intent intent = new Intent(this,EstudioDetalle.class);
            intent.putExtra("id_estudio",0);
            intent.putExtra("id_candidato",0);
            startActivity(intent);

        }else {

            EducacionABC repo = new EducacionABC(this);

            ArrayList<HashMap<String, String>> estudiosList =  repo.getEstudiosList(_id_candidato);
            if(estudiosList.size()!=0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                        id_Estudio = (TextView) view.findViewById(R.id.id_estudio);
                        String idEstudio = id_Estudio.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(),EstudioDetalle.class);
                        objIndent.putExtra("id_estudio", Integer.parseInt( idEstudio));
                        startActivity(objIndent);
                    }
                });
                ListAdapter adapter = new SimpleAdapter( ActivityEducacion.this,estudiosList, R.layout.ver_estudios_entry, new String[] { "idEstudio","idCandidato", "NombreEscuela" ,"Grado", "Titulo"}, new int[] {R.id.id_estudio, R.id.id_candidato, R.id.nombreEscuela, R.id.grado, R.id.titulo});
                setListAdapter(adapter);
            }else{
                Toast.makeText(this,"Sin candidatos!",Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudios);

        btnAdd = (FloatingActionButton) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnGetAll = (Button) findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(this);

    }


}

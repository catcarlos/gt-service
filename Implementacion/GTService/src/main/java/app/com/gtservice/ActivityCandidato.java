package app.com.gtservice;

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

public class ActivityCandidato extends ListActivity  implements View.OnClickListener{

    Button btnGetAll;
    FloatingActionButton btnAdd;
    TextView id_Candidato;
    int _id_Reclutador;

    @Override
    public void onClick(View view) {
        if (view== findViewById(R.id.btnAdd)){

            Intent intent = new Intent(this,CandidatoDetalle.class);
            intent.putExtra("id_candidato",0);
            intent.putExtra("id_reclutador",0);
            startActivity(intent);

        }else {
            CandidatoABC repo = new CandidatoABC(this);

            ArrayList<HashMap<String, String>> candidatoList =  repo.getCandidatoList();
            if(candidatoList.size()!=0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                        id_Candidato = (TextView) view.findViewById(R.id.id_candidato);
                        String idCandidato = id_Candidato.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(),CandidatoDetalle.class);
                        objIndent.putExtra("id_candidato", Integer.parseInt( idCandidato));
                        startActivity(objIndent);
                    }
                });
                ListAdapter adapter = new SimpleAdapter( ActivityCandidato.this,candidatoList, R.layout.ver_candidato_entry, new String[] { "idCandidato","Folio","Nombres"}, new int[] {R.id.id_candidato, R.id.folio, R.id.nombre_candidato});
                setListAdapter(adapter);
                registerForContextMenu(lv);
            }else{
                Toast.makeText(this,"Sin candidatos!",Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (FloatingActionButton) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnGetAll = (Button) findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(this);

        Intent intent = getIntent();
        _id_Reclutador=intent.getIntExtra("id_reclutador",0);

    }


}

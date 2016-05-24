package app.com.gtservice;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
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

public class ActivityReclutador extends ListActivity  implements View.OnClickListener{

    Button btnAdd,btnGetAll;
    TextView id_Reclutador;

    @Override
    public void onClick(View view) {
        if (view== findViewById(R.id.btnAdd)){

            Intent intent = new Intent(this,ReclutadorDetalle.class);
            intent.putExtra("id_Reclutador",0);
            startActivity(intent);
        }else {

          ReclutadorABC repo = new ReclutadorABC(this);

            ArrayList<HashMap<String, String>> studentList =  repo.getReclutadorList();
            if(studentList.size()!=0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                        id_Reclutador = (TextView) view.findViewById(R.id.id_reclutador);
                        String idReclutador = id_Reclutador.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(),ReclutadorDetalle.class);
                        objIndent.putExtra("id_reclutador", Integer.parseInt( idReclutador));
                        startActivity(objIndent);
                    }
                });
                ListAdapter adapter = new SimpleAdapter( ActivityReclutador.this,studentList, R.layout.ver_reclutador_entry, new String[] { "idReclutador","NombreReclutador","Usuario"}, new int[] {R.id.id_reclutador, R.id.nombre_reclutador,R.id.usuario});
                setListAdapter(adapter);
            }else{
                Toast.makeText(this,"Sin Reclutadores!",Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclutador);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnGetAll = (Button) findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(this);

    }


}

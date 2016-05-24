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

public class ActivityEmpresa extends ListActivity  implements View.OnClickListener{

    Button btnAdd,btnGetAll;
    TextView id_Empresa;

    @Override
    public void onClick(View view) {
        if (view== findViewById(R.id.btnAdd)){

            Intent intent = new Intent(this,EmpresaDetalle.class);
            intent.putExtra("id_Empresa",0);
            startActivity(intent);

        }else {

            EmpresaABC repo = new EmpresaABC(this);

            ArrayList<HashMap<String, String>> EmpresaList =  repo.getEmpresasList();
            if(EmpresaList.size()!=0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                        id_Empresa = (TextView) view.findViewById(R.id.id_Empresa);
                        String idEmpresa= id_Empresa.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(),EmpresaDetalle.class);
                        objIndent.putExtra("id_Empresa", Integer.parseInt( idEmpresa));
                        startActivity(objIndent);
                    }
                });
                ListAdapter adapter = new SimpleAdapter( ActivityEmpresa.this,EmpresaList, R.layout.ver_empresa_entry, new String[] { "idEmpresa","NombreEmpresa","CantidadVacantes"}, new int[] {R.id.id_Empresa, R.id.NombreEmpresa, R.id.CantidadVacantes});
                setListAdapter(adapter);
            }else{
                Toast.makeText(this,"Sin Empresas!",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnGetAll = (Button) findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(this);

    }


}


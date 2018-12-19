package com.example.user.conefec20;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class resultados extends AppCompatActivity {

    String dni ;
    TextView inf;
    ArrayList <String> ids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        inf=(TextView)findViewById(R.id.lblDni);
        Bundle recibido = this.getIntent().getExtras();
        if(recibido != null){
            dni=getIntent().getExtras().getString("dni");
            inf.setText("Resultados de : "+dni);
            DBController dbc = new DBController(this);
            SQLiteDatabase db = dbc.getWritableDatabase();
            String query = "Select Id, codigo , nombre , prodprin from cuentas Where codigo like '%"+dni+"%'";
            Cursor cursor = db.rawQuery(query, null);
            ListView listado = (ListView)findViewById(R.id.lstResul);
            String cad = "";
            ArrayList<String> lista = new ArrayList<>();
            ids = new ArrayList<>();
            if(cursor.moveToFirst()){
                do{
                    int xid = cursor.getInt(0);
                    String xsid = String.valueOf(xid);
                    String xcodigo = cursor.getString(1);
                    String xnombre = cursor.getString(2);
                    String xproduc = cursor.getString(3);
                    cad = xcodigo+"\n"+xnombre +"\n"+xproduc;
                    lista.add(cad);
                    ids.add(xsid);

                } while (cursor.moveToNext());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, lista);
                listado.setAdapter(adapter);
                registerForContextMenu(listado);
                cursor.close();

            }
            listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String idsel = ids.get(position);
                    Intent intent = new Intent(resultados.this,detalle.class);
                    intent.putExtra("id",idsel);
                    startActivity(intent);

                }
            });


        }
    }
}

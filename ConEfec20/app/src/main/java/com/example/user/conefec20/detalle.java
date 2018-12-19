package com.example.user.conefec20;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class detalle extends AppCompatActivity {

    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Bundle recibido = this.getIntent().getExtras();
        if(recibido != null){
            id = getIntent().getExtras().getString("id");
            int idn = Integer.parseInt(id);
            TextView persona = (TextView)findViewById(R.id.lblNombre);
            ListView listado = (ListView)findViewById(R.id.lstDatos);
            DBController dbc = new DBController(this);
            SQLiteDatabase db = dbc.getWritableDatabase();
            String query = "Select * from cuentas Where Id = "+idn+"";
            Cursor cursor = db.rawQuery(query, null);
            cursor.moveToFirst();


            ArrayList<String> lista = new ArrayList<>();
            String Id = cursor.getString(0);
            String codigo = cursor.getString(1);
            String gestor = cursor.getString(2);
            String tramo = cursor.getString(3);
            String plaza = cursor.getString(4);
            String pagamay = cursor.getString(5);
            String microzona = cursor.getString(6);
            String codigor = cursor.getString(7);
            String nombre = cursor.getString(8);
            String direccion = cursor.getString(9);
            String telefono = cursor.getString(10);
            String prestamo = cursor.getString(11);
            String atraso = cursor.getString(12);
            String vencimiento = cursor.getString(13);
            String ultimopago = cursor.getString(14);
            String ultimopagom = cursor.getString(15);
            String penultimopago = cursor.getString(16);
            String cuota = cursor.getString(17);
            String cuotatotal = cursor.getString(18);
            String cuotapag = cursor.getString(19);
            String rsmncuota = cursor.getString(20);
            String deuda = cursor.getString(21);
            String prodprin = cursor.getString(22);
            String fecdes = cursor.getString(23);
            String saldoki = cursor.getString(24);
            String agencia = cursor.getString(25);
            String distrito = cursor.getString(26);
            String urbanizacion = cursor.getString(27);


            cursor.close();







            int dni = Integer.parseInt(codigo);
            String query2 = "Select count(*) from cuentas Where codigo = "+dni+"";
            Cursor cursor2 = db.rawQuery(query2, null);
            cursor2.moveToFirst();

            int nroc = cursor2.getInt(0);

            cursor2.close();




            String query3 = "Select prodprin from cuentas Where codigo = "+dni+"";
            ArrayList<String> listapro = new ArrayList<>();
            Cursor cursor3 = db.rawQuery(query3, null);
            cursor3.moveToFirst();
            do{

                String xproddd = cursor3.getString(0);

                listapro.add(xproddd);

            } while (cursor3.moveToNext());

            cursor3.close();



            String xdir= "DIRECCION :\n"+direccion;
            String xtel = "TELEFONO :\n"+telefono;
            String xprestamos = "PRESTAMOS : \n"+String.valueOf(nroc);
            String xret = "RETAIL : ";
            String xcuop = "CUOTAS PAGADAS : \n"+cuotapag;
            String venc = "FECHA VENCIMIENTO : \n" + vencimiento;
            String xdesem = "FECHA DESEMBOLOSO : \n"+ fecdes;
            String xtramo = "TRAMO : \n" + tramo;
            String xplazo = "PLAZO : \n "+ cuotapag+"/"+cuotatotal;
            String xgestor = "GESTOR : \n" + gestor;
            String xplaza = "PLAZA : \n"+ plaza;
            String xdistrito = "DISTRITO : \n"+distrito;
            String xurban = "URBANIZACION : \n"+urbanizacion;
            String xulpag = "ULTIMO PAGO : \n" +ultimopago;
            String xulpagm = "MONTO ULTIMO PAGO : \n" + ultimopagom;
            String xdeuc = "DEUDA CAPITAL : \n" + deuda;
            String xsaldoki = "CAPITAL + INTERESES : \n"+saldoki;
            String xcuota = "VALOR CUOTA : \n" + cuota;

            lista.add(xdir);
            lista.add(xtel);
            lista.add(xprestamos);
            lista.add(xret);
            lista.add(xcuop);
            lista.add(venc);
            lista.add(xdesem);
            lista.add(xtramo);
            lista.add(xplazo);
            lista.add(xgestor);
            lista.add(xplaza);
            lista.add(xdistrito);
            lista.add(xurban);
            lista.add(xulpag);
            lista.add(xulpagm);
            lista.add(xdeuc);
            lista.add(xsaldoki);
            lista.add(xcuota);
            //for(int i = 0 ; i<=listapro.size(); i++){
              //  lista.add("PRODUCTOS : \n" + listapro.get(i).toString());
            //}


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, lista);
            listado.setAdapter(adapter);
            registerForContextMenu(listado);
            persona.setText("Datos de: " + nombre+"\n"+codigo);









        }
    }
}

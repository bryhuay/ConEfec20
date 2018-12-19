package com.example.user.conefec20;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class importar extends ListActivity {

    TextView lbl;
    DBController controller = new DBController(this);
    Button btnimport;
    ListView lv;
    final Context context = this;
    ListAdapter adapter;
    ArrayList<HashMap<String, String>> myList;
    public static final int requestcode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_importar);

        lbl = (TextView) findViewById(R.id.txtresulttext);
        btnimport = (Button) findViewById(R.id.btnupload);
        lv = getListView();

        btnimport.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent fileintent = new Intent(Intent.ACTION_GET_CONTENT);
                fileintent.setType("gagt/sdf");
                try {
                    startActivityForResult(fileintent, requestcode);
                } catch (ActivityNotFoundException e) {
                    lbl.setText("No activity can handle picking a file. Showing alternatives.");
                }

            }
        });
        myList= controller.getAllCuentasD();
        if (myList.size() != 0) {
            ListView lv = getListView();
            ListAdapter adapter = new SimpleAdapter(importar.this, myList,
                    R.layout.v, new String[]{"codigo", "nombre", "tramo"}, new int[]{
                    R.id.txtcodigo, R.id.txtnombre, R.id.txttramo});
            setListAdapter(adapter);
            lbl.setText("");
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null)
            return;
        switch (requestCode) {
            case requestcode:
                String filepath = data.getData().getPath();
                controller = new DBController(getApplicationContext());
                SQLiteDatabase db = controller.getWritableDatabase();
                String tableName = "cuentas";
                db.execSQL("delete from " + tableName);
                try {
                    if (resultCode == RESULT_OK) {
                        try {

                            FileReader file = new FileReader(filepath);

                            BufferedReader buffer = new BufferedReader(file);
                            ContentValues contentValues = new ContentValues();
                            String line = "";
                            db.beginTransaction();

                            while ((line = buffer.readLine()) != null) {

                                String[] str = line.split(";", 27);  // defining 3 columns with null or blank field //values acceptance

                                String codigo = str[0].toString();
                                String gestor = str[1].toString();
                                String tramo = str[2].toString();
                                String plaza = str[3].toString();
                                String pagamay = str[4].toString();
                                String microzona = str[5].toString();
                                String codigor = str[6].toString();
                                String nombre = str[7].toString();
                                String direccion = str[8].toString();
                                String telefono = str[9].toString();
                                String prestamo = str[10].toString();
                                String atraso = str[11].toString();
                                String vencimiento = str[12].toString();
                                String ultimopago = str[13].toString();
                                String ultimopagom = str[14].toString();
                                String penultimopago = str[15].toString();
                                String cuota = str[16].toString();
                                String cuotatotal = str[17].toString();
                                String cuotapag = str[18].toString();
                                String rsmncuota = str[19].toString();
                                String deuda = str[20].toString();
                                String prodprin = str[21].toString();
                                String fecdes = str[22].toString();
                                String saldoki = str[23].toString();
                                String agencia = str[24].toString();
                                String distrito = str[25].toString();
                                String urbanizacion = str[26].toString();


                                contentValues.put("codigo", codigo);
                                contentValues.put("gestor", gestor);
                                contentValues.put("tramo", tramo);
                                contentValues.put("plaza", plaza);
                                contentValues.put("pagamay", pagamay);
                                contentValues.put("microzona", microzona);
                                contentValues.put("codigor", codigor);
                                contentValues.put("nombre", nombre);
                                contentValues.put("direccion", direccion);
                                contentValues.put("telefono", telefono);
                                contentValues.put("prestamo", prestamo);
                                contentValues.put("atraso", atraso);
                                contentValues.put("vencimiento", vencimiento);
                                contentValues.put("ultimopago", ultimopago);
                                contentValues.put("ultimopagom", ultimopagom);
                                contentValues.put("penultimopago", penultimopago);
                                contentValues.put("cuota", cuota);
                                contentValues.put("cuotatotal", cuotatotal);
                                contentValues.put("cuotapag", cuotapag);
                                contentValues.put("rsmncuota", rsmncuota);
                                contentValues.put("deuda", deuda);
                                contentValues.put("prodprin", prodprin);
                                contentValues.put("fecdes", fecdes);
                                contentValues.put("saldoki", saldoki);
                                contentValues.put("agencia", agencia);
                                contentValues.put("distrito", distrito);
                                contentValues.put("urbanizacion", urbanizacion);
                                db.insert(tableName, null, contentValues);
                                lbl.setText("Successfully Updated Database.");
                            }
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            //lbl.setText("Data importada");
                        } catch (IOException e) {
                            if (db.inTransaction())
                                db.endTransaction();
                            Dialog d = new Dialog(this);
                            d.setTitle(e.getMessage().toString() + "first");
                            d.show();
                            // db.endTransaction();
                        }
                    } else {
                        if (db.inTransaction())
                            db.endTransaction();
                        Dialog d = new Dialog(this);
                        d.setTitle("Only CSV files allowed");
                        d.show();
                    }
                } catch (Exception ex) {
                    if (db.inTransaction())
                        db.endTransaction();

                    Dialog d = new Dialog(this);
                    d.setTitle(ex.getMessage().toString() + "second");
                    //d.show();
                    lbl.setText(ex.getMessage().toString() + "second");
                    // db.endTransaction();
                }
        }
        myList= controller.getAllCuentasD();

        if (myList.size() != 0) {
            ListView lv = getListView();
            ListAdapter adapter = new SimpleAdapter(importar.this, myList,
                    R.layout.v, new String[]{"codigo", "nombre", "tramo"}, new int[]{
                    R.id.txtcodigo, R.id.txtnombre, R.id.txttramo});
            setListAdapter(adapter);
            lbl.setText("Data Imported");
        }
    }


}

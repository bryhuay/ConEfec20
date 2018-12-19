package com.example.user.conefec20;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;

public class DBController extends SQLiteOpenHelper {

    private static final String LOGCAT = null;



    public DBController(Context applicationcontext) {

        super(applicationcontext, "Data.db", null, 1);  // creating DATABASE

        Log.d(LOGCAT, "Created");

    }



    @Override

    public void onCreate(SQLiteDatabase database) {

        String query;

        query = "CREATE TABLE IF NOT EXISTS cuentas ( Id INTEGER PRIMARY KEY, codigo TEXT, gestor TEXT, tramo TEXT, plaza TEXT, pagamay TEXT, microzona TEXT, codigor TEXT, nombre TEXT, direccion TEXT, telefono TEXT, prestamo TEXT, atraso TEXT, vencimiento TEXT, ultimopago TEXT, ultimopagom TEXT, penultimopago TEXT, cuota TEXT, cuotatotal TEXT, cuotapag TEXT, rsmncuota TEXT, deuda TEXT, prodprin TEXT, fecdes TEXT, saldoki TEXT, agencia TEXT, distrito TEXT, urbanizacion TEXT)";

        database.execSQL(query);

    }





    @Override

    public void onUpgrade(SQLiteDatabase database, int version_old,

                          int current_version) {

        String query;

        query = "DROP TABLE IF EXISTS cuentas";

        database.execSQL(query);

        onCreate(database);

    }



    public ArrayList<HashMap<String, String>> getAllCuentas() {

        ArrayList<HashMap<String, String>> proList;

        proList = new ArrayList<HashMap<String, String>>();

        String selectQuery = "SELECT  * FROM cuentas";

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                //Id, Company,Name,Price

                HashMap<String, String> map = new HashMap<String, String>();

                map.put("Id", cursor.getString(0));

                map.put("codigo", cursor.getString(1));

                map.put("gestor", cursor.getString(2));

                map.put("tramo", cursor.getString(3));
                map.put("plaza", cursor.getString(4));
                map.put("pagamay", cursor.getString(5));
                map.put("microzona", cursor.getString(6));
                map.put("codigor", cursor.getString(7));
                map.put("nombre", cursor.getString(8));
                map.put("direccion", cursor.getString(9));
                map.put("telefono", cursor.getString(10));
                map.put("prestamo", cursor.getString(11));
                map.put("atraso", cursor.getString(12));
                map.put("vencimiento", cursor.getString(13));
                map.put("ultimopago", cursor.getString(14));
                map.put("ultimopagom", cursor.getString(15));
                map.put("penultimopago", cursor.getString(16));
                map.put("cuota", cursor.getString(17));
                map.put("cuotatotal", cursor.getString(18));
                map.put("cuotapag", cursor.getString(19));
                map.put("rsmncuota", cursor.getString(20));
                map.put("deuda", cursor.getString(21));
                map.put("prodprin", cursor.getString(22));
                map.put("fecdes", cursor.getString(23));
                map.put("saldoki", cursor.getString(24));
                map.put("agencia", cursor.getString(25));
                map.put("distrito", cursor.getString(26));
                map.put("urbanizacion", cursor.getString(27));

                proList.add(map);

            } while (cursor.moveToNext());

        }



        return proList;

    }
    public ArrayList<HashMap<String, String>> getAllCuentasD() {

        ArrayList<HashMap<String, String>> proList;

        proList = new ArrayList<HashMap<String, String>>();

        String selectQuery = "SELECT  * FROM cuentas";

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                //Id, Company,Name,Price

                HashMap<String, String> map = new HashMap<String, String>();

                map.put("codigo", cursor.getString(1));

                map.put("nombre", cursor.getString(9));

                map.put("tramo", cursor.getString(3));



                proList.add(map);

            } while (cursor.moveToNext());

        }



        return proList;

    }



}

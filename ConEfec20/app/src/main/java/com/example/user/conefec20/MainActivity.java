package com.example.user.conefec20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Importar (View v){
        Intent imp=new Intent(MainActivity.this,importar.class);
        startActivity(imp);
    }
    public void dni (View v){
        Intent dn=new Intent(MainActivity.this,dni.class);
        startActivity(dn);
    }
}

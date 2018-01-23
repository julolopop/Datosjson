package com.example.usuario.datosjson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button Eje1;
    Button Eje2;
    Button Eje3;
    Button Eje4;
    Button Eje5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.Eje1 = findViewById(R.id.Eje1);
        this.Eje2 = findViewById(R.id.Eje2);
        this.Eje3 = findViewById(R.id.Eje3);
        this.Eje4 = findViewById(R.id.Eje4);
        this.Eje5 = findViewById(R.id.Eje5);


        this.Eje1.setOnClickListener(this);
        this.Eje2.setOnClickListener(this);
        this.Eje3.setOnClickListener(this);
        this.Eje4.setOnClickListener(this);
        this.Eje5.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Eje1:
                startActivity(new Intent(MainActivity.this,Primitiva.class));
                break;
            case R.id.Eje2:
                startActivity(new Intent(MainActivity.this,ListaContactos.class));
                break;
            case R.id.Eje3:
                startActivity(new Intent(MainActivity.this,ListaContactosGSON.class));
                break;
            case R.id.Eje4:
                startActivity(new Intent(MainActivity.this,CrearJSON.class));
                break;
            case R.id.Eje5:
                startActivity(new Intent(MainActivity.this,Retrofit.class));
                break;
        }
    }
}

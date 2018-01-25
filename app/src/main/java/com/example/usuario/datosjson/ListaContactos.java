package com.example.usuario.datosjson;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.usuario.datosjson.pojo.Analisis;
import com.example.usuario.datosjson.pojo.Contacto;
import com.example.usuario.datosjson.pojo.RestClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by usuario on 23/01/18.
 */

public class ListaContactos  extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    //public static final String WEB = "http://192.168.1.20/acceso/contactos.json";
    public static final String WEB = "https://www.portadaalta.mobi/acceso/contactos.json";
    Button boton;
    ListView lista;
    ArrayList<Contacto> contactos;
    ArrayAdapter<Contacto> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listacontactos);
        boton = (Button) findViewById(R.id.button2);
        boton.setOnClickListener(this);
        lista = (ListView) findViewById(android.R.id.list);
        lista.setOnItemClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == boton)
            descarga(WEB);
    }

    //usar JsonHttpResponseHandler()
    private void descarga(String web) {
        final ProgressDialog progreso = new ProgressDialog(this);
        RestClient.get(web, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progreso.setMessage("Conectando . . .");
                progreso.setCancelable(true);
                progreso.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                progreso.dismiss();
                try {
                    contactos = Analisis.analizarContactos(response);
                    mostrar();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                progreso.dismiss();
                Toast.makeText(ListaContactos.this, errorResponse.toString(),Toast.LENGTH_LONG).show();
            }
        } );
    }
    private void mostrar() {
        if (contactos != null)
            if (adapter == null) {
                adapter = new ArrayAdapter<Contacto>(this, android.R.layout.simple_list_item_1, contactos);
                lista.setAdapter(adapter);
            } else {
                adapter.clear();
                adapter.addAll(contactos);
            }
        else
            Toast.makeText(getApplicationContext(), "Error al crear la lista", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Móvil: " + contactos.get(position).getTelefono().getMovil(), Toast.LENGTH_SHORT).show();
    }

}

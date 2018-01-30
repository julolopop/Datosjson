package com.example.usuario.datosjson;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.usuario.datosjson.pojo.Analisis;
import com.example.usuario.datosjson.pojo.Contact;
import com.example.usuario.datosjson.pojo.Contacto;
import com.example.usuario.datosjson.pojo.Persona;
import com.example.usuario.datosjson.pojo.RestClient;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by usuario on 23/01/18.
 */

public class ListaContactosGSON extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    public static final String WEB = "http://192.168.1.20/acceso/contacts.json";
    //public static final String WEB = "https://www.portadaalta.mobi/acceso/contacts.json";
    Button boton;
    ListView lista;
    ArrayList<Contact> contacts;
    ArrayAdapter<Contact> adapter;
    Persona person;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listacontactosgson);
        boton = (Button) findViewById(R.id.button2);
        boton.setOnClickListener(this);
        lista = (ListView) findViewById(android.R.id.list);
        lista.setOnItemClickListener(this);
        contacts = new ArrayList<Contact>();
    }

    @Override
    public void onClick(View v) {
        if (v == boton)
            descarga(WEB);
    }

    private void descarga(String web) {
        final ProgressDialog progreso = new ProgressDialog(this);
        RestClient.get(WEB, new JsonHttpResponseHandler() {
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

                gson = new Gson();
                person = gson.fromJson(String.valueOf(response),Persona.class);
                mostrar();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                progreso.dismiss();
                Toast.makeText(ListaContactosGSON.this, errorResponse.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void mostrar() {
        if (person != null) {
            contacts.clear();
            contacts.addAll(person.getContacts());
            if (adapter == null) {
                adapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, contacts);
                lista.setAdapter(adapter);
            } else {
                adapter.clear();
                adapter.addAll(contacts);
            }
        } else
            Toast.makeText(getApplicationContext(), "Error al crear la lista", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Móvil: " + contacts.get(position).getPhone().getMobile(),
                Toast.LENGTH_SHORT).show();
    }
}

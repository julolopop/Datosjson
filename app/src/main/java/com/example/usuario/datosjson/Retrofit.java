package com.example.usuario.datosjson;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usuario.datosjson.pojo.Git;
import com.example.usuario.datosjson.pojo.ReposAdapter;
import com.example.usuario.datosjson.pojo.RestClient;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by usuario on 23/01/18.
 */

public class Retrofit extends AppCompatActivity implements View.OnClickListener, Callback<ArrayList<Git>> {

    EditText nombreUsuario;
    Button botonDescarga;
    RecyclerView rvRepos;
    private ReposAdapter adapter;
    private ArrayList<Git> git;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrofit);

        nombreUsuario = (EditText) findViewById(R.id.editText);
        botonDescarga = (Button) findViewById(R.id.button);
        botonDescarga.setOnClickListener(this);

        rvRepos = (RecyclerView) findViewById(R.id.recicler);
        adapter = new ReposAdapter();
        rvRepos.setAdapter(adapter);
        rvRepos.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    public void onClick(View v) {
        if (v == botonDescarga) {
            descargar();
        }
    }


    public void mostrar() {
        this.adapter.setGit(git);
    }

    private void descargar() {

        final ProgressDialog progreso = new ProgressDialog(this);
        RestClient.get("https://api.github.com/users/julolopop",new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                Gson gson = new Gson();

                git = gson.fromJson(String.valueOf(response),Git.class);

                mostrar();
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }

            public void onStart() {
                super.onStart();
                progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progreso.setMessage("Conectando . . .");
                progreso.setCancelable(true);
                progreso.show();


            }
        });
    }

    public void onItemClick(View view, int position) {
        Toast.makeText(getApplicationContext(), "position " + position + " was clicked!",
                Toast.LENGTH_SHORT).show();
        Uri uri = Uri.parse((String) git.get(position).getUrl());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
        else
            Toast.makeText(getApplicationContext(), "No hay un navegador",
                    Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(Call<ArrayList<Git>> call, Response<ArrayList<Git>> response) {

    }

    @Override
    public void onFailure(Call<ArrayList<Git>> call, Throwable t) {

    }
}

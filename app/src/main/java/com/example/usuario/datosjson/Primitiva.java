package com.example.usuario.datosjson;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by usuario on 23/01/18.
 */

public class Primitiva extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "MyTag";
    private String uri = "http://alumno.mobi/~alumno/superior/diaz/primitiva.json";

    Button mButton;
    TextView mTextView;
    RequestQueue mRequestQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.primitiva);

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mTextView = (TextView) findViewById(R.id.textView);
        mRequestQueue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
    }

    @Override
    public void onClick(View view) {
        if (view == mButton)
            descarga();
    }

    private void descarga() {

        // Set the tag on the request.
        jsObjRequest.setTag(TAG);
        // Set retry policy
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 1, 1));
        // Add the request to the RequestQueue.
        mRequestQueue.add(jsObjRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(TAG);
        }
    }
}




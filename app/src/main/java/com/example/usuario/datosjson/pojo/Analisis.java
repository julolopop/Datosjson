package com.example.usuario.datosjson.pojo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by usuario on 23/01/18.
 */

public class Analisis {

    public static String analizarPrimitiva(JSONObject texto) throws JSONException {
        JSONArray jsonContenido;
        JSONObject item;
        String tipo;
        StringBuilder cadena = new StringBuilder();
        tipo = texto.getString("info");
        jsonContenido = new JSONArray(texto.getString("sorteo"));
        cadena.append("Sorteos de la Primitiva:" + "\n");
        for (int i = 0; i < jsonContenido.length(); i++) {
            item = jsonContenido.getJSONObject(i);

            cadena.append(tipo + ":" + item.getString("fecha") + "\n");
            cadena.append(item.getInt("numero1") + "," + item.getInt("numero2") + "," + item.getInt("numero3") + "," +
                    item.getInt("numero4") + "," + item.getInt("numero5") + "\n");
        }
        return cadena.toString();
    }

    public static ArrayList<Contacto> analizarContactos(JSONObject respuesta) throws JSONException {
        JSONArray jAcontactos = new JSONArray(respuesta.getString("contactos"));
        JSONObject jOcontacto, jOtelefono;
        Contacto contacto;
        Telefono telefono;
        ArrayList<Contacto> personas = new ArrayList<>();
        // añadir contactos (en JSON) a personas

        for (int i = 0; i < jAcontactos.length(); i++) {
            jOcontacto = jAcontactos.getJSONObject(i);
            contacto = new Contacto();
            telefono = new Telefono();

            contacto.setNombre(jOcontacto.getString("nombre"));
            contacto.setEmail(jOcontacto.getString("email"));
            contacto.setDireccion(jOcontacto.getString("direccion"));

            jOtelefono = jOcontacto.getJSONObject("telefono");
            telefono.setCasa(jOtelefono.getString("casa"));
            telefono.setMovil(jOtelefono.getString("movil"));
            telefono.setTrabajo(jOtelefono.getString("trabajo"));

            contacto.setTelefono(telefono);

            personas.add(contacto);
        }


        return personas;
    }
}

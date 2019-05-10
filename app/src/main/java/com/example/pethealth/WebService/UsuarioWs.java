package com.example.pethealth.WebService;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pethealth.Activity.MainActivity;
import com.example.pethealth.Dao.UsuarioDAO;
import com.example.pethealth.Model.Usuario;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Map;

public class UsuarioWs {

    public static void logar(final Context contexto, String url, final Map<String, String> params) {
        RequestQueue queue = Volley.newRequestQueue(contexto);
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, Connection.getUrl() + url,
                new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                UsuarioDAO db = new UsuarioDAO(contexto);
                Usuario usuario = new Gson().fromJson(response.toString(), Usuario.class);

                if (usuario != null) {
                    db.deleTudo();
                    db.inserir(usuario);
                    Toast.makeText(contexto,"Cliente lista: "+ db.findAllUsuario().getNome(),Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(contexto,"erro",Toast.LENGTH_LONG).show();
                }


                try {
                    Intent intent = new Intent(contexto, MainActivity.class);
                    contexto.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(contexto);

                alerta.setTitle("Aviso!!");
                alerta.setMessage("Login ou Senha inválidos...");
                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alerta.show();
            }
        });
        postRequest.setRetryPolicy(new DefaultRetryPolicy(10000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);
    }

}

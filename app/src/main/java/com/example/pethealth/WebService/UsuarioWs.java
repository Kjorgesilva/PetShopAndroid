package com.example.pethealth.WebService;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pethealth.Activity.MainActivity;
import org.json.JSONObject;

import java.util.Map;

public class UsuarioWs {

    public static void logar(final Context contexto,String url, final Map<String, String> params) {
        RequestQueue queue = Volley.newRequestQueue(contexto);
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, Connection.getUrl() + url ,
                new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Intent intent = new Intent(contexto, MainActivity.class);
                    contexto.startActivity(intent);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("Erro","Erro" + error.getMessage());

            }
        });
        postRequest.setRetryPolicy(new DefaultRetryPolicy(10000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);
    }

}
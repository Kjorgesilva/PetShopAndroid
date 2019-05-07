package com.example.pethealth.WebService;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.pethealth.Dao.RespostaRelatorioDAO;
import com.example.pethealth.Model.RespostaRelatorio;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RespostaRelatorioWs {


    public static void listarRespostaRelatorio(final Context contexto, String path) {

        RequestQueue queue = Volley.newRequestQueue(contexto);
        final JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, Connection.getUrl() + path, null, new Response.Listener<JSONArray>() {


            @Override
            public void onResponse(JSONArray response) {
                RespostaRelatorioDAO db = new RespostaRelatorioDAO(contexto);
                try {
                    List<RespostaRelatorio> list = new Gson().fromJson(response.toString(), new TypeToken<List<RespostaRelatorio>>() {
                    }.getType());

                    if (list.isEmpty()) {
                        Toast.makeText(contexto, "Lista vazia", Toast.LENGTH_LONG).show();
                    } else {

                        if (db.ListarBanco().size() > 0) {

                            List<RespostaRelatorio> listDataBase = db.ListarBanco();
                            int cont = 0;

                            for (int i = 0; i < list.size(); i++) {

                                for (int x = 0; x < listDataBase.size(); x++) {
                                    if (list.get(i).getId() != listDataBase.get(x).getId()) {
                                        cont = cont + 1;

                                    }
                                }
                                if (cont == listDataBase.size()) {
                                    db.inserir(list.get(i));
                                }
                                cont = 0;
                            }
                        } else {
                            for (int i = 0; i < list.size(); i++) {
                                db.inserir(list.get(i));
                            }
                        }
                    }
                } catch (IllegalStateException | JsonSyntaxException exception) {
                    exception.printStackTrace();
                    Log.e("Erro", "Erro passou aqui primeiro");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Erro", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<String, String>();
                header.put("Content-Type", "application/json; charset=UTF-8");
                return header;
            }
        };
        getRequest.setRetryPolicy(new

                DefaultRetryPolicy(15000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(getRequest);

    }

    }

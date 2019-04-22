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
import com.example.pethealth.Dao.VacinasDAO;
import com.example.pethealth.Model.Vacinas;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VacinasWs {

    private VacinasDAO db;

    public static void listarVacinas(final Context contexto, String path) {
        RequestQueue queue = Volley.newRequestQueue(contexto);
        final JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, Connection.getUrl() + path, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                VacinasDAO db = new VacinasDAO(contexto);

                try {
                    List<Vacinas> list = new Gson().fromJson(response.toString(), new TypeToken<List<Vacinas>>() {
                    }.getType());

                    if (list.isEmpty()) {
                        Toast.makeText(contexto, "Lista vazia, tomar vacina", Toast.LENGTH_LONG).show();
                    } else {

                        if (db.findAllVacinas().size() > 0) {

                            List<Vacinas> listDataBase = db.findAllVacinas();
                            int cont = 0;

                            for (int i = 0; i < list.size(); i++) {

                                for (int x = 0; x < listDataBase.size(); x++) {
                                    if (list.get(i).getId() != listDataBase.get(x).getId()) {
                                        cont = cont + 1;
                                        Log.e("teste", "Id serv: " + list.get(i).getId() +
                                                " id sqlite: " + listDataBase.get(x).getId());
                                    }
                                }
                                if (cont == listDataBase.size()) {
                                    db.inserir(list.get(i));
                                    Log.e("teste", "entrou");
                                }
                                cont = 0;
                            }
                        } else {
                            for (int i = 0; i < list.size(); i++) {
                                db.inserir(list.get(i));
                            }
                        }
                    }

//                            Toast.makeText(contexto,"id do animal: " + list.get(i).getId() +
//                                    "aviso " + list.get(i).getAviso() + " Data vacina: " + list.get(i).getDataVacina()+
//                                    " Data proxima: " + list.get(i).getDataDaProxima() + " Animal: " + list.get(i).getNomeAnimal() +
//                                    " Tipo vacina: "  + list.get(i).getNomeVacina(),Toast.LENGTH_LONG).show();
//
//                            Log.e("teste_list", list.get(i).getIdAnimal() + " / " +
//                            list.get(i).getNomeAnimal());


                } catch (IllegalStateException | JsonSyntaxException exception) {
                    Log.e("Erro", "Erro");
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

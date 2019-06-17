package com.example.pethealth.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.pethealth.Adapter.AdapterVacinas;
import com.example.pethealth.Dao.UsuarioDAO;
import com.example.pethealth.Dao.VacinasDAO;
import com.example.pethealth.Model.Vacinas;
import com.example.pethealth.R;
import com.example.pethealth.WebService.Connection;
import com.example.pethealth.WebService.VacinasWs;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class VacinasFragment extends Fragment {

    private Context contexto;
    private RecyclerView recyclerView;
    private List<Vacinas> listaVacina = new ArrayList<>();
    private UsuarioDAO dbUsuario;

    private VacinasDAO db;


    public VacinasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_vacinas, container, false);
        contexto = getContext();
        db = new VacinasDAO(contexto);


        recyclerView = view.findViewById(R.id.recyclerViewAnimal);
        recyclerView.setLayoutManager(new LinearLayoutManager(contexto));

        dbUsuario = new UsuarioDAO(contexto);
        listarVacinas(contexto, "vacinacao/" + dbUsuario.findAllUsuario().getIdCliente() + "/listaVacinas");

        return view;

    }


    private AdapterVacinas.VacinasOnclickListener clickListner() {

        return new AdapterVacinas.VacinasOnclickListener() {

            @Override
            public void vacinasOnclickListener(View view, final int index) {
                }
        };
    }

    public static VacinasFragment newInstance() {
        VacinasFragment fragment = new VacinasFragment();
        return fragment;
    }

    public  void listarVacinas(final Context contexto, String path) {

        RequestQueue queue = Volley.newRequestQueue(contexto);
        final JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, Connection.getUrl() + path, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                VacinasDAO db = new VacinasDAO(contexto);
                List<Vacinas> list = new Gson().fromJson(response.toString(), new TypeToken<List<Vacinas>>() {}.getType());

                try {
                    if (list.isEmpty()) {
                        Toast.makeText(contexto, "Lista vazia", Toast.LENGTH_LONG).show();
                    } else {
                        db.deleTudo();
                        for(int x = 0; x < list.size(); x++){
                            db.inserir(list.get(x));

                            Log.e("teste", "entrou");
                        }
                    }

                    listaVacina.addAll(db.findAllVacinas());
                    recyclerView.setAdapter(new AdapterVacinas(contexto,listaVacina,clickListner()));

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

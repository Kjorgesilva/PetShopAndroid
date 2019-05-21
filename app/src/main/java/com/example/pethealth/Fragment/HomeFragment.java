package com.example.pethealth.Fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pethealth.Adapter.AdapterAgendamento;
import com.example.pethealth.Dao.AgendamentoDAO;
import com.example.pethealth.Dao.AnimalDAO;
import com.example.pethealth.Dao.UsuarioDAO;
import com.example.pethealth.Model.Agenda;
import com.example.pethealth.Model.Animal;
import com.example.pethealth.R;
import com.example.pethealth.WebService.Connection;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private AgendamentoDAO db = new AgendamentoDAO(getContext());
    private Context context;
    private List<Agenda> listaAgenda = new ArrayList<>();

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView = view.findViewById(R.id.recyclerView);
        context = this.getContext();
        db = new AgendamentoDAO(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        UsuarioDAO usuarioDAO = new UsuarioDAO(context);
        listaAgenda.addAll( db.ListarBanco(usuarioDAO.findAllUsuario().getIdCliente()));
        recyclerView.setAdapter(new AdapterAgendamento(context,listaAgenda, clickListner()));



        return view;
    }

    private AdapterAgendamento.AgendamentoOnClickListner clickListner() {
        return new AdapterAgendamento.AgendamentoOnClickListner() {
            @Override
            public void onLongClick(View view, final int position) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(getContext());
                alerta.setTitle("Atenção");
                alerta.setMessage("Deseja cancelar a consulta ?");
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AgendamentoDAO dbAgenda = new AgendamentoDAO(context);
                        Agenda agendaAux = dbAgenda.findAll(listaAgenda.get(i).getId());

                        if (agendaAux != null){
                              cancelarConsulta(String.valueOf(agendaAux));
                        }



                    }
                });
                alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alerta.show();
            }

            @Override
            public void clickListenerView(View view, int index) {

            }
        };
    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    private void cancelarConsulta(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("login", id);
        //arrumar metodo 
        cancelar(context,"consulta/" + 1 + "/atualizaAgenda" ,map);
        Log.e("chamou", "chamou o metodo" + "map" + map);
    }


    public static void cancelar(Context contexto, String url, final Map<String, String> params) {
        RequestQueue queue = Volley.newRequestQueue(contexto);
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, Connection.getUrl() + url,
                new JSONObject(params), new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


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

    }
}

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
import com.example.pethealth.Adapter.AdapterRelatorioMedico;
import com.example.pethealth.Dao.AgendamentoDAO;
import com.example.pethealth.Dao.AnimalDAO;
import com.example.pethealth.Dao.UsuarioDAO;
import com.example.pethealth.Model.Agenda;
import com.example.pethealth.Model.Animal;
import com.example.pethealth.R;
import com.example.pethealth.WebService.Connection;
import com.google.gson.Gson;

import org.json.JSONException;
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
                        Agenda agendaAux = dbAgenda.findAll(listaAgenda.get(position).getId());

                        if (agendaAux != null){
                              cancelarConsulta(String.valueOf(agendaAux.getId()));
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
        map.put("id", id);
        cancelar(context,"consulta/atualizaAgenda" ,map);
        Log.e("chamouAgenda", "chamou o metodo" + "map" + map);
    }


    public  void cancelar(final Context contexto, String url, final Map<String, String> params) {
        final RequestQueue queue = Volley.newRequestQueue(contexto);
        final JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, Connection.getUrl() + url,
                new JSONObject(params), new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {

                    int idAgenda =  Integer.parseInt(response.getString("id"));
                    Agenda ag = db.findAll(idAgenda);
                    ag.setStatus_agendamento("C");
                    db.update(ag);

                    recyclerView.setAdapter(new AdapterAgendamento(context, db.ListarBanco(ag.getIdCliente()),
                            clickListner()));

                    Toast.makeText(context,"retorno: " + ag.getStatus_agendamento(),Toast.LENGTH_LONG).show();




                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            Toast.makeText(context,"Operação não realizada, tente mais tarde.",Toast.LENGTH_LONG).show();

            }
        }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> header = new HashMap<String, String>();
//                header.put("Content-Type", "application/json; charset=UTF-8");
//                return header;
//            }
//        };
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(15000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);

    }
}

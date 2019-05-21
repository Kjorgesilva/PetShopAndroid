package com.example.pethealth.Fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import com.example.pethealth.Activity.RelatorioMedicoActivity;
import com.example.pethealth.Adapter.AdapterHistorico;
import com.example.pethealth.Adapter.AdapterRelatorioMedico;
import com.example.pethealth.Dao.AgendamentoDAO;
import com.example.pethealth.Dao.RespostaRelatorioDAO;
import com.example.pethealth.Dao.UsuarioDAO;
import com.example.pethealth.Model.RespostaRelatorio;
import com.example.pethealth.R;
import com.example.pethealth.WebService.Connection;
import com.example.pethealth.WebService.RespostaRelatorioWs;
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
public class RelatorioMedicoFragment extends Fragment {

    private RecyclerView recyclerView;
    private AgendamentoDAO db = new AgendamentoDAO(getContext());
    private Context context;
    private RespostaRelatorioDAO dao;
    private UsuarioDAO usuarioDAO;
    private List<RespostaRelatorio> respostaRelatorioList = new ArrayList<>();
    private List<RespostaRelatorio> listaAux = new ArrayList<>();


    public RelatorioMedicoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_relatorio_medico, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        context = this.getContext();
        db = new AgendamentoDAO(getContext());
        dao = new RespostaRelatorioDAO(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        usuarioDAO = new UsuarioDAO(context);



        listarRespostaRelatorio(context, "respostaRelatorio/listaRespostaRelatorio");


        return view;
    }


    private AdapterRelatorioMedico.RelatorioOnClick clickListner() {

        return new AdapterRelatorioMedico.RelatorioOnClick() {

            @Override
            public void clickListenerView(View view, int index) {



                    Intent intent = new Intent(context, RelatorioMedicoActivity.class);
                    intent.putExtra("id_relatorio", listaAux.get(index).getIdAgenda());
                    startActivity(intent);

            }
            @Override
            public void onLongClick(View view, int i) {
            }


        };
    }


    public static RelatorioMedicoFragment newInstance() {
        RelatorioMedicoFragment fragment = new RelatorioMedicoFragment();
        return fragment;
    }

    public  void listarRespostaRelatorio(final Context contexto, String path) {

        RequestQueue queue = Volley.newRequestQueue(contexto);
        final JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, Connection.getUrl() + path, null, new Response.Listener<JSONArray>() {


            @Override
            public void onResponse(JSONArray response) {
                RespostaRelatorioDAO db = new RespostaRelatorioDAO(contexto);
                try {
                    List<RespostaRelatorio> list = new Gson().fromJson(response.toString(), new TypeToken<List<RespostaRelatorio>>() {
                    }.getType());


                    Log.e("resposta_teste", String.valueOf(usuarioDAO.findAllUsuario().getIdCliente()));

                    Log.e("resposta_teste", String.valueOf(db.ListarBanco(usuarioDAO.findAllUsuario().getIdCliente()).size()));

                    if (db.ListarBanco(usuarioDAO.findAllUsuario().getIdCliente()).size() > 0) {

                        List<RespostaRelatorio> listDataBase = db.ListarBanco(usuarioDAO.findAllUsuario().getIdCliente());
                        int cont = 0;

                        for (int i = 0; i < list.size(); i++) {

                            for (int x = 0; x < listDataBase.size(); x++) {
                                if (list.get(i).getId() != listDataBase.get(x).getId()) {
                                    cont = cont + 1;

                                }
                            }
                            if (cont == listDataBase.size()) {
                                db.inserir(list.get(i));
                                Log.e("inserioBanco", "inserio" + list.size());
                                cont = 0;
                            }

                        }

                    } else {

                        for (int i = 0; i < list.size(); i++) {
                            db.inserir(list.get(i));
                        }
                    }
                    List<Integer> lista = new ArrayList<>();
                    List<RespostaRelatorio> listaBanco = new ArrayList<>();


                    listaBanco.addAll(dao.ListarBanco(usuarioDAO.findAllUsuario().getIdCliente()));

                    if (listaBanco.size() == 0){
                        Toast.makeText(context, "Carregando relatória... Tente novamente.", Toast.LENGTH_LONG).show();
                    }else{

                        for (int y=0; y<listaBanco.size();y++){
                            Integer idAgenda = listaBanco.get(y).getIdAgenda();
                            if (!lista.contains(idAgenda)){
                                lista.add(idAgenda);
                                listaAux.add(listaBanco.get(y));

                            }
                        }

                        recyclerView.setAdapter(new AdapterRelatorioMedico(context,listaAux,clickListner()));
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

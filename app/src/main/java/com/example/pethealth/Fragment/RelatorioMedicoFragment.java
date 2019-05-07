package com.example.pethealth.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.pethealth.Activity.RelatorioMedicoActivity;
import com.example.pethealth.Adapter.AdapterRespostaRelatorio;
import com.example.pethealth.Dao.RespostaRelatorioDAO;
import com.example.pethealth.Model.RespostaRelatorio;
import com.example.pethealth.R;
import com.example.pethealth.WebService.RespostaRelatorioWs;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RelatorioMedicoFragment extends Fragment {

    private Context contexto;
    private RecyclerView recyclerView;
    private List<RespostaRelatorio> lista = new ArrayList<>();
    private RespostaRelatorioDAO db;

    public RelatorioMedicoFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_relatorio_medico, container, false);
        contexto = getContext();
        db = new RespostaRelatorioDAO(contexto);

        RespostaRelatorioWs.listarRespostaRelatorio(contexto, "respostaRelatorio/listaRespostaRelatorio");
        lista.addAll(db.ListarBanco());

        if (lista.isEmpty()){
            Toast.makeText(contexto,"Lista Vazia",Toast.LENGTH_LONG).show();
        }else {
            recyclerView = view.findViewById(R.id.recyclerViewRelatorio);
            recyclerView.setLayoutManager(new LinearLayoutManager(contexto));
            recyclerView.setAdapter(new AdapterRespostaRelatorio(contexto, lista,clickListner()));
        }
        return view;
    }

    public AdapterRespostaRelatorio.RespostaRelatorioOnclickListener clickListner(){
        return  new AdapterRespostaRelatorio.RespostaRelatorioOnclickListener() {
            @Override
            public void animalOnclickListener(View view, int index) {
                Intent intent = new Intent(contexto,RelatorioMedicoActivity.class);
                startActivity(intent);
            }
        };
    }
    public static RelatorioMedicoFragment newInstance() {
        RelatorioMedicoFragment fragment = new RelatorioMedicoFragment();
        return fragment;
    }

}

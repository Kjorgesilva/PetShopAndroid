package com.example.pethealth.Fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pethealth.Activity.RelatorioMedicoActivity;
import com.example.pethealth.Adapter.AdapterHistorico;
import com.example.pethealth.Dao.AgendamentoDAO;
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

    private RecyclerView recyclerView;
    private AgendamentoDAO db = new AgendamentoDAO(getContext());
    private Context context;
    private List<RespostaRelatorio> listaResposta = new ArrayList<>();
    private RespostaRelatorioDAO dao = new RespostaRelatorioDAO(getContext());


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
        recyclerView.setAdapter(new AdapterHistorico(getContext(), db.ListarBanco(), clickListner()));


        return view;
    }


    private AdapterHistorico.HistoricoOnClickListner clickListner(){

    return new AdapterHistorico.HistoricoOnClickListner() {

        @Override
        public void clickListenerView(View view, int index) {

            Intent intent = new Intent(context, RelatorioMedicoActivity.class);
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

}

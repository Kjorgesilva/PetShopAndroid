package com.example.pethealth.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pethealth.Adapter.AdapterAgendamento;
import com.example.pethealth.Adapter.AdapterHistorico;
import com.example.pethealth.Dao.AgendamentoDAO;
import com.example.pethealth.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoricoFragment extends Fragment {


    private RecyclerView recyclerView;
    private AgendamentoDAO db = new AgendamentoDAO(getContext());
    private Context context;
    private AdapterHistorico adapterHistorico;


    public HistoricoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_historico, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        context = this.getContext();
        db = new AgendamentoDAO(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new AdapterHistorico(getContext(), db.ListarBanco()));

        return view;



    }

    public static HistoricoFragment newInstance() {
        HistoricoFragment fragment = new HistoricoFragment();
        return fragment;
    }

}

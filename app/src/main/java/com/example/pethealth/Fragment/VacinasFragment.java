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

import com.example.pethealth.Adapter.AdapterVacinas;
import com.example.pethealth.Dao.VacinasDAO;
import com.example.pethealth.Model.Vacinas;
import com.example.pethealth.R;
import com.example.pethealth.WebService.VacinasWs;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VacinasFragment extends Fragment {

    private Context contexto;
    private RecyclerView recyclerView;
    private List<Vacinas> listaTeste = new ArrayList<>();
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
        db = new VacinasDAO(getContext());

        listaTeste.addAll(db.findAllVacinas());
        VacinasWs.listarVacinas(contexto, "vacinacao/listavacinas");

        if (listaTeste.isEmpty()){
            Toast.makeText(contexto,"Lista Vazia",Toast.LENGTH_LONG).show();
        }else {
            recyclerView = view.findViewById(R.id.recyclerViewAnimal);
            recyclerView.setLayoutManager(new LinearLayoutManager(contexto));
            recyclerView.setAdapter(new AdapterVacinas(contexto, listaTeste, clickListner()));


        }
        return view;

    }


    private AdapterVacinas.VacinasOnclickListener clickListner() {

        return new AdapterVacinas.VacinasOnclickListener() {

            @Override
            public void vacinasOnclickListener(View view, final int index) {
                Toast.makeText(getContext(), "funcionando", Toast.LENGTH_LONG).show();

            }
        };
    }

    public static VacinasFragment newInstance() {
        VacinasFragment fragment = new VacinasFragment();
        return fragment;
    }

}

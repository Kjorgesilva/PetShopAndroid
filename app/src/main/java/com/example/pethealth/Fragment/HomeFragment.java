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
import com.example.pethealth.Adapter.AdapterAgendamento;
import com.example.pethealth.Dao.AgendamentoDAO;
import com.example.pethealth.Dao.AnimalDAO;
import com.example.pethealth.Model.Animal;
import com.example.pethealth.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private AgendamentoDAO db = new AgendamentoDAO(getContext());
    private Context context;


    public HomeFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView = view.findViewById(R.id.recyclerView);
        context = this.getContext();
        db = new AgendamentoDAO(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new AdapterAgendamento(getContext(), db.ListarBanco(), clickListner()));




        return view;
    }

    private AdapterAgendamento.AgendamentoOnClickListner clickListner() {
        return new AdapterAgendamento.AgendamentoOnClickListner() {
            @Override
            public void onLongClick(View view, final int position) {
//                AlertDialog.Builder alerta = new AlertDialog.Builder(getContext());
//                alerta.setTitle("Atenção");
//                alerta.setMessage("Deseja excluir a consulta ?");
//                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        int id = db.ListarBanco().get(position).getId();
//                        db.delete(id);
//                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                        AdapterAgendamento adapterAgendamento = new AdapterAgendamento(getContext(), db.ListarBanco(), clickListner());
//                        recyclerView.setAdapter(adapterAgendamento);
//                    }
//                });
//                alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
//
//                alerta.show();
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

}

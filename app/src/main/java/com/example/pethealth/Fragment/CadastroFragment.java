package com.example.pethealth.Fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.pethealth.Dao.AgendamentoDAO;
import com.example.pethealth.Dao.MedicoDAO;
import com.example.pethealth.InterfaceHelp.Mask;
import com.example.pethealth.Model.Consulta;
import com.example.pethealth.Model.Medico;
import com.example.pethealth.R;
import com.example.pethealth.WebService.MedicoWS;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroFragment extends Fragment {
    private EditText edt_nome, edt_endereco, edt_data , edt_data_fim;
    private ImageButton imgSpinner;
    private TextView tvSpinner;
    private Button btn_cadastrar;
    private Context contexto;
    private Spinner spinner , spinner2;

    private List<Medico> listaTeste = new ArrayList<>();
    //serve para fazer a ligação com a classe AgendamentoDAO e chama os metodos do Banco
    AgendamentoDAO db = new AgendamentoDAO(getContext());
    MedicoDAO dbMedico = new MedicoDAO(getContext());

    public CadastroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_cadastro, container, false);
        contexto = getContext();
        dbMedico = new MedicoDAO(getContext());

        listaTeste.addAll(dbMedico.findAllMedico());
        MedicoWS.listarMedico(contexto, "medico/listamedico");




        edt_nome = view.findViewById(R.id.edt_nome);
        edt_data_fim = view.findViewById(R.id.edt_data_fim);
        edt_endereco = view.findViewById(R.id.edt_endereco);
        edt_data = view.findViewById(R.id.edt_data);
        btn_cadastrar = view.findViewById(R.id.btn_cadastrar);
        imgSpinner = view.findViewById(R.id.imgSpinner);
        tvSpinner = view.findViewById(R.id.tvSpinner);
        spinner = view.findViewById(R.id.spinnerHoras);
        spinner2 = view.findViewById(R.id.spinnerHorasFim);

        ArrayAdapter adaptador = ArrayAdapter.createFromResource
                (contexto, R.array.horarioInicio, android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adaptador);

        ArrayAdapter adaptador2 = ArrayAdapter.createFromResource
                (contexto, R.array.horarioFim, android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(adaptador2);

        db = new AgendamentoDAO(getContext());

        edt_data.addTextChangedListener(Mask.insert("##/##/####", edt_data));
        edt_data_fim.addTextChangedListener(Mask.insert("##/##/####", edt_data_fim));

        imgSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialogItens();
               // Toast.makeText(contexto,"teste imagem",Toast.LENGTH_LONG).show();
            }
        });

        tvSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogItens();
               // Toast.makeText(contexto,"teste spiner",Toast.LENGTH_LONG).show();

            }
        });



        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_nome.getText().toString().isEmpty()) {
                    edt_nome.setError("Preencha o campo");
                } else if (edt_data_fim.getText().toString().isEmpty()) {
                    edt_data_fim.setError("Preencha o campo");
                } else if (edt_endereco.getText().toString().isEmpty()) {
                    edt_endereco.setError("Preencha o campo");
                } else if (edt_data.getText().toString().isEmpty()) {
                    edt_data.setError("Preencha o campo");
                }else if (tvSpinner.getText().toString().isEmpty()){
                    tvSpinner.setError("Preencha o campo");
                }
                else {
                String nome = edt_nome.getText().toString();
                String endereco = edt_endereco.getText().toString();
                String data = edt_data.getText().toString();
                String spinnerMedico = tvSpinner.getText().toString();
                String dataFim =  edt_data_fim.getText().toString();


                db.inserir(new Consulta(nome, endereco, data , spinnerMedico, dataFim ));
                Toast.makeText(getContext(), "Consulta Agendada", Toast.LENGTH_LONG).show();
                edt_nome.setText("");
                edt_data_fim.setText("");
                edt_endereco.setText("");
                edt_data.setText("");
                tvSpinner.setText("");

            }}
        });





        return view;
    }

    public void showDialogItens() {
        MedicoDAO dao = new MedicoDAO(contexto);
        final List<Medico> produtos = new ArrayList<>();
        produtos.addAll(dao.findAllMedico());
        AlertDialog.Builder alert = new AlertDialog.Builder(contexto);
        alert.setTitle("Selecione um produto abaixo:");
        String[] itens = new String[produtos.size()];
        for (int x = 0; x < produtos.size(); x++) {
            itens[x] = produtos.get(x).getNome();
        }
        alert.setItems(itens, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvSpinner.setText(produtos.get(i).getNome());
            }
        });
        alert.show();
    }
    public static CadastroFragment newInstance() {
        CadastroFragment fragment = new CadastroFragment();
        return fragment;
    }

}

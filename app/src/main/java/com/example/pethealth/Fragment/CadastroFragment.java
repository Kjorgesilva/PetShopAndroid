package com.example.pethealth.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.pethealth.Dao.AgendamentoDAO;
import com.example.pethealth.InterfaceHelp.Mask;
import com.example.pethealth.Model.Consulta;
import com.example.pethealth.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroFragment extends Fragment {
    private EditText edt_nome, edt_telefone, edt_endereco, edt_data, edt_Dr;
    private Button btn_cadastrar;
    //serve para fazer a ligação com a classe AgendamentoDAO e chama os metodos do Banco
    AgendamentoDAO db = new AgendamentoDAO(getContext());

    public CadastroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_cadastro, container, false);
        edt_nome = view.findViewById(R.id.edt_nome);
        edt_telefone = view.findViewById(R.id.edt_telefone);
        edt_endereco = view.findViewById(R.id.edt_endereco);
        edt_data = view.findViewById(R.id.edt_data);
        edt_Dr = view.findViewById(R.id.edt_Dr);
        btn_cadastrar = view.findViewById(R.id.btn_cadastrar);
        db = new AgendamentoDAO(getContext());
        edt_telefone.addTextChangedListener(Mask.insert("(##)#####-####", edt_telefone));
        edt_data.addTextChangedListener(Mask.insert("##/##/####", edt_data));

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_nome.getText().toString().isEmpty()) {
                    edt_nome.setError("Preencha o campo");
                } else if (edt_telefone.getText().toString().isEmpty()) {
                    edt_telefone.setError("Preencha o campo");
                } else if (edt_endereco.getText().toString().isEmpty()) {
                    edt_endereco.setError("Preencha o campo");
                } else if (edt_Dr.getText().toString().isEmpty()) {
                    edt_Dr.setError("Preencha o campo");
                } else if (edt_data.getText().toString().isEmpty()) {
                    edt_data.setError("Preencha o campo");
                } else {
                String nome = edt_nome.getText().toString();
                String telefone = edt_telefone.getText().toString();
                String endereco = edt_endereco.getText().toString();
                String data = edt_data.getText().toString();
                String medico = edt_Dr.getText().toString();


                db.inserir(new Consulta(nome, telefone, endereco, data, medico));
                Toast.makeText(getContext(), "Consulta Agendada", Toast.LENGTH_LONG).show();
                edt_nome.setText("");
                edt_telefone.setText("");
                edt_endereco.setText("");
                edt_data.setText("");
                edt_Dr.setText("");
            }}
        });


        return view;
    }

    public static CadastroFragment newInstance() {
        CadastroFragment fragment = new CadastroFragment();
        return fragment;
    }

}

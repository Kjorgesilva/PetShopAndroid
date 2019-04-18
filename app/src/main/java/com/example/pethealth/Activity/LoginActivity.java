package com.example.pethealth.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pethealth.InterfaceHelp.InterfaceHelp;
import com.example.pethealth.R;
import com.example.pethealth.WebService.Connection;
import com.example.pethealth.WebService.UsuarioWs;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity implements InterfaceHelp {

    private Button btnEnter;
    private EditText edtUsuario, edtSenha;
    private Context contexto = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findView();
        onClickView();
    }
    @Override
    public void findView() {
        btnEnter = findViewById(R.id.btnEnter);
        edtUsuario = findViewById(R.id.edtUsuario);
        edtSenha = findViewById(R.id.edtSenha);
    }

    @Override
    public void onClickView() {
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtUsuario.getText().toString().isEmpty() || edtSenha.getText().toString().isEmpty()) {
                    Toast.makeText(contexto, "Preencha todos os campos", Toast.LENGTH_LONG).show();
                } else {
                    login(edtUsuario.getText().toString(), edtSenha.getText().toString());
                    //Toast.makeText(getApplicationContext(), "login: " + edtUsuario.getText().toString() + " Senha: " + edtSenha.getText().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void login(String login, String senha) {
        Map<String, String> map = new HashMap<>();
        map.put("login", login);
        map.put("senha", senha);
        UsuarioWs.logar(contexto,"usuario", map);
        Log.e("chamou", "chamou o metodo" + "map" + map);
    }

}

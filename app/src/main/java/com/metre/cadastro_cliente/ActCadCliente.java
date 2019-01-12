package com.metre.cadastro_cliente;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActCadCliente extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtEndereco;
    private EditText edtTelefone;
    private EditText edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_cad_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        edtNome = findViewById(R.id.edtNome);
        edtEndereco = findViewById(R.id.edtEndereco);
        edtEmail = findViewById(R.id.edtEmail);
        edtTelefone = findViewById(R.id.edtTelefone);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_cad_cliente,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        System.out.println("ID::::::::::::::::::::::::::::::"+id);
        System.out.println("OK::::::::::::::::::::::::::::::"+R.id.action_ok);
        System.out.println("CA::::::::::::::::::::::::::::::"+R.id.action_cancelar);
        switch (id){
            case R.id.action_ok:
                validarCampos();
                Toast.makeText(this,"OK SELECIONADO",Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_cancelar:
                Toast.makeText(this,"CANCELAR SELECIONADO",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void validarCampos(){
        boolean rest = false;
        String nome = edtNome.getText().toString();

        String telefone = edtTelefone.getText().toString();
        String endereco = edtEndereco.getText().toString();

        String email = edtEmail.getText().toString();
        if(rest = isCampoVazio(nome)){
            edtNome.requestFocus();
        }else{
            if(rest = isCampoVazio(endereco)){
                edtEndereco.requestFocus();
            }else{
                if( rest = isCampoVazio(telefone)){
                    edtTelefone.requestFocus();
                }else{
                    if(rest = !isEmailValido(email)){
                        edtEmail.requestFocus();
                    }
                }
            }
        }
        if(rest){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_aviso);
            dlg.setMessage(R.string.message_campos_invalidos);
            dlg.setNeutralButton(R.string.lbl_ok,null);
            dlg.show();
        }
    }

    private boolean isCampoVazio(String valor){
        return TextUtils.isEmpty(valor) || valor.trim().isEmpty();
    }

    private boolean isEmailValido(String email){
        System.out.println("EMAIL È VALIDO: "+Patterns.EMAIL_ADDRESS.matcher(email).matches());
        System.out.println("EMAIL È VAZIOO: "+!isCampoVazio(email));
        return !isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}

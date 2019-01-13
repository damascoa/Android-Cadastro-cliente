package com.metre.cadastro_cliente;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
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

import com.metre.cadastro_cliente.database.DadosOpenHelper;
import com.metre.cadastro_cliente.dominio.entidades.Cliente;
import com.metre.cadastro_cliente.dominio.repositorio.ClienteRepositorio;

public class ActCadCliente extends AppCompatActivity {
    private ConstraintLayout layoutContextCliente;
    private EditText edtNome;
    private EditText edtEndereco;
    private EditText edtTelefone;
    private EditText edtEmail;

    private SQLiteDatabase conexao;
    private DadosOpenHelper dadosHelper;
    private ClienteRepositorio clienteRepositorio;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_cad_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        layoutContextCliente = findViewById(R.id.layoutContextCliente);

        edtNome = findViewById(R.id.edtNome);
        edtEndereco = findViewById(R.id.edtEndereco);
        edtEmail = findViewById(R.id.edtEmail);
        edtTelefone = findViewById(R.id.edtTelefone);



        criarConexao();



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
        switch (id){
            case R.id.action_ok:
                confirmar();
                break;
            case R.id.action_cancelar:
                Toast.makeText(this,"CANCELAR SELECIONADO",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void confirmar(){
        cliente = new Cliente();
        if(!validarCampos()){
            try {
                clienteRepositorio.inserir(cliente);
            }catch (SQLException e){
                e.printStackTrace();

            }

        }
    }

    private boolean validarCampos(){
        boolean rest = false;
        String nome = edtNome.getText().toString();
        String telefone = edtTelefone.getText().toString();
        String endereco = edtEndereco.getText().toString();
        String email = edtEmail.getText().toString();

        cliente.nome = nome;
        cliente.telefone = telefone;
        cliente.endereco = endereco;
        cliente.email = email;

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
            return rest;
        }
        return rest;
    }

    private boolean isCampoVazio(String valor){
        return TextUtils.isEmpty(valor) || valor.trim().isEmpty();
    }

    private boolean isEmailValido(String email){
        System.out.println("EMAIL È VALIDO: "+Patterns.EMAIL_ADDRESS.matcher(email).matches());
        System.out.println("EMAIL È VAZIOO: "+isCampoVazio(email));
        return !isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }



    private void criarConexao(){
        try {
            dadosHelper = new DadosOpenHelper(this);
            conexao = dadosHelper.getWritableDatabase();

            Snackbar.make(layoutContextCliente,R.string.message_coexao_sucesso, Snackbar.LENGTH_SHORT)
                    .setAction("OK",null).show();
            clienteRepositorio = new ClienteRepositorio(conexao);
        }catch (Exception e){
            e.printStackTrace();
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(e.getMessage());
            dlg.setNeutralButton("OK",null);
            dlg.show();
        }
    }
}

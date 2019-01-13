package com.metre.cadastro_cliente;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.metre.cadastro_cliente.database.DadosOpenHelper;
import com.metre.cadastro_cliente.dominio.entidades.Cliente;
import com.metre.cadastro_cliente.dominio.repositorio.ClienteRepositorio;

import java.util.List;

public class ActMain extends AppCompatActivity {
    private RecyclerView lstDados;
    private FloatingActionButton fab;
    private ConstraintLayout layoutContextMain;


    private SQLiteDatabase conexao;
    private DadosOpenHelper dadosHelper;

    private ClienteAdapter clienteAdapter;
    private ClienteRepositorio clienteRepositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lstDados = findViewById(R.id.lstDados);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActMain.this,ActCadCliente.class);
                startActivity(intent);
            }
        });
        layoutContextMain = findViewById(R.id.layoutContextMain);
        criarConexao();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lstDados.setLayoutManager(linearLayoutManager);
        clienteRepositorio = new ClienteRepositorio(conexao);
        List<Cliente> clientes = clienteRepositorio.buscarTodos();
        clienteAdapter = new ClienteAdapter(clientes);
        lstDados.setAdapter(clienteAdapter);

    }


    private void criarConexao(){
        try {
            dadosHelper = new DadosOpenHelper(this);
            conexao = dadosHelper.getWritableDatabase();

            Snackbar.make(layoutContextMain,R.string.message_coexao_sucesso, Snackbar.LENGTH_SHORT)
            .setAction("OK",null).show();
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

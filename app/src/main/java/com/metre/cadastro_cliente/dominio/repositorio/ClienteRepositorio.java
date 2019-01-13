package com.metre.cadastro_cliente.dominio.repositorio;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.metre.cadastro_cliente.dominio.entidades.Cliente;

import java.util.ArrayList;
import java.util.List;
public class ClienteRepositorio {
    private SQLiteDatabase conexao;

    public ClienteRepositorio(SQLiteDatabase conexao) {
        this.conexao = conexao;
    }

    public void inserir(Cliente c){
        ContentValues values = new ContentValues();
        values.put("NOME",c.nome);
        values.put("ENDERECO",c.endereco);
        values.put("EMAIL",c.email);
        values.put("TELEFONE",c.telefone);
        conexao.insertOrThrow("CLIENTE",null,values);
    }
    public void excluir(Integer codigo){
        String[] parametros = new String[1];
        parametros[0] = codigo.toString();
        conexao.delete("CLIENTE","CODIGO = ?",parametros);
    }
    public void alterar(Cliente c){
        ContentValues values = new ContentValues();
        values.put("NOME",c.nome);
        values.put("ENDERECO",c.endereco);
        values.put("EMAIL",c.email);
        values.put("TELEFONE",c.telefone);
        String[] parametros = new String[1];
        parametros[0] = c.codigo.toString();
        conexao.update("CLIENTE",values,"CODIGO = ?",parametros);
    }
    public List<Cliente> buscarTodos(){
        List<Cliente> clientes = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT CODIGO,NOME,ENDERECO,EMAIL,TELEFONE FROM CLIENTE ");
        Cursor resultado = conexao.rawQuery(sql.toString(),null);
        if(resultado.getCount() > 0) {
            resultado.moveToFirst();
            do{
                Cliente cli = new Cliente();
                cli.codigo      = resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO"));
                cli.nome        =  resultado.getString(resultado.getColumnIndexOrThrow("NOME"));
                cli.endereco    = resultado.getString(resultado.getColumnIndexOrThrow("ENDERECO"));
                cli.email       = resultado.getString(resultado.getColumnIndexOrThrow("EMAIL"));
                cli.telefone    = resultado.getString(resultado.getColumnIndexOrThrow("TELEFONE"));
                clientes.add(cli);
            }while(resultado.moveToNext());
        }

        return clientes;
    }
    public Cliente buscarCliente(Integer codigo){
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT CODIGO,NOME,ENDERECO,EMAIL,TELEFONE FROM CLIENTE WHERE CODIGO = ?");
        String[] parametros = new String[1];
        parametros[0] = codigo.toString();
        Cursor resultado = conexao.rawQuery(sql.toString(),parametros);
        if(resultado.getCount() > 0) {
            resultado.moveToFirst();
                Cliente cli = new Cliente();
                cli.codigo      = resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO"));
                cli.nome        =  resultado.getString(resultado.getColumnIndexOrThrow("NOME"));
                cli.endereco    = resultado.getString(resultado.getColumnIndexOrThrow("ENDERECO"));
                cli.email       = resultado.getString(resultado.getColumnIndexOrThrow("EMAIL"));
                cli.telefone    = resultado.getString(resultado.getColumnIndexOrThrow("TELEFONE"));
                return cli;
        }

        return null;
    }
}

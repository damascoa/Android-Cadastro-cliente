package com.metre.cadastro_cliente;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.metre.cadastro_cliente.dominio.entidades.Cliente;

import java.util.List;

public class ClienteAdapter extends RecyclerView .Adapter<ClienteAdapter.ViewHolderCliente>{

    private List<Cliente> clientes;


    public ClienteAdapter(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @NonNull
    @Override
    public ClienteAdapter.ViewHolderCliente onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.linha_cliente,viewGroup,false);
        ViewHolderCliente holderCliente = new ViewHolderCliente(view);
        return holderCliente;
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteAdapter.ViewHolderCliente viewHolder, int i) {
        if(clientes != null && clientes.size() > 0) {
            Cliente cliente = clientes.get(i);
            viewHolder.txtNome.setText(cliente.nome);
            viewHolder.txtTelefone.setText(cliente.telefone);
        }
    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }

    public class ViewHolderCliente extends RecyclerView.ViewHolder{
        private TextView txtNome;
        private TextView txtTelefone;
        public ViewHolderCliente(@NonNull View itemView) {
            super(itemView);
            txtNome     = itemView.findViewById(R.id.txtNome);
            txtTelefone = itemView.findViewById(R.id.txtTelefone);
        }

    }
}

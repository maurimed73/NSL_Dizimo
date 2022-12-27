package com.example.nsldizimo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nsldizimo.R;
import com.example.nsldizimo.model.Conferida;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Conferida>listaConferidas;

    public MyAdapter(List<Conferida>conferidas) {
        this.listaConferidas = conferidas;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nome;
        TextView valorTotal;
        TextView data;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.name);
            valorTotal = itemView.findViewById(R.id.valor);
            data = itemView.findViewById(R.id.data);


        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Conferida conferida = listaConferidas.get(position);
        holder.nome.setText(conferida.getNomeConferente());
        holder.valorTotal.setText((conferida.getValorTotal()));
        holder.data.setText(conferida.getDataContagem());
    }

    @Override
    public int getItemCount() {
        return listaConferidas.size();
    }

}

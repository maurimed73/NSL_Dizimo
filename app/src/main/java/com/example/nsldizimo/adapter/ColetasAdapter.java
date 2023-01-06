package com.example.nsldizimo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nsldizimo.R;
import com.example.nsldizimo.model.Coletas;
import com.example.nsldizimo.model.Conferida;

import java.util.List;

public class ColetasAdapter extends RecyclerView.Adapter<ColetasAdapter.MyViewHolder> {

    private List<Coletas> listaColetas;

    public ColetasAdapter(List<Coletas> listaColeta) {
        this.listaColetas = listaColeta;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView valorTotal;
        TextView data;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            valorTotal = itemView.findViewById(R.id.valorColeta);
            data = itemView.findViewById(R.id.dataColeta);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemListaColetas = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_coletas,parent,false);
        return new MyViewHolder(itemListaColetas);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Coletas coleta = listaColetas.get(position);
        holder.valorTotal.setText(coleta.getValorColeta());
        holder.data.setText(coleta.getDataColeta());
    }

    @Override
    public int getItemCount() {
        return listaColetas.size();
    }



}


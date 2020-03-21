package org.helloworld.auditivo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.helloworld.auditivo.R;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by jj on 19/12/18.
 */
public class Elemento_Quiz_Sena extends RecyclerView.Adapter<Elemento_Quiz_Sena.ViewHolder>implements View.OnClickListener{
    private View.OnClickListener listener;

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }
    public static class ViewHolder extends  RecyclerView.ViewHolder{
        private TextView text;
        private GifImageView gifImageView;
        public ViewHolder(View itemView){
            super(itemView);
            text=itemView.findViewById(R.id.significado);
            gifImageView=itemView.findViewById(R.id.iconi);
        }
    }
    public ArrayList<Adaptador_Senas_Quiz> lista;
    public Elemento_Quiz_Sena(ArrayList<Adaptador_Senas_Quiz> lista){
        this.lista=lista;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_quiz_sena, null, false);
        ViewHolder viewHolder=new ViewHolder(view);
        view.setOnClickListener(this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.text.setText(lista.get(position).getText());
        holder.gifImageView.setImageResource(lista.get(position).getImg());

    }

    @Override
    public int getItemCount() {
        return lista.size();

    }
    public void setOnClickListener2(View.OnClickListener listenerr){
        this.listener=listenerr;
    }






}


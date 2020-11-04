package org.helloworld.auditivo;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jairo enrique on 19/12/18.
 */
public class Adappter_Palabras_Rapidas_Conversar extends RecyclerView.Adapter<Adappter_Palabras_Rapidas_Conversar.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    private View.OnClickListener listener;
    private View.OnLongClickListener listenerLong;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.temas);
        }
    }

    public ArrayList<Classes> list;
    public Adappter_Palabras_Rapidas_Conversar(ArrayList<Classes> list){
        this.list=list;
    }
    @NonNull
    @Override
    public Adappter_Palabras_Rapidas_Conversar.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_conversor_palabras_rapidas,null,false);
        ViewHolder viewHolder=new ViewHolder(view);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adappter_Palabras_Rapidas_Conversar.ViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getTexto());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnClickListener2(View.OnClickListener listenerr){
        this.listener=listenerr;
    }

    public void setOnLongClickListener2(View.OnLongClickListener listenerr){
        listenerLong=listenerr;
    }

    @Override
    public boolean onLongClick(View v) {
        if(listener!=null){
            listenerLong.onLongClick(v);
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }
}


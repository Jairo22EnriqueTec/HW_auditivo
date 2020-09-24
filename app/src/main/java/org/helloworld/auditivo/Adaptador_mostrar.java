package org.helloworld.auditivo;

import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.helloworld.auditivo.R;

import java.util.List;

/**
 * Created by jj on 26/07/18.
 */

public class Adaptador_mostrar extends RecyclerView.Adapter<Adaptador_mostrar.ViewHolder> {
List<Adaptador_Recyclerm>listacontenido;
    //private Context context;
    TextToSpeech tts=null;
public Adaptador_mostrar(List<Adaptador_Recyclerm>listacontenido){
    this.listacontenido=listacontenido;
}
    @Override
    public Adaptador_mostrar.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclermostrar,parent,false);
    RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);
    view.setLayoutParams(layoutParams);
   // this.context=context;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador_mostrar.ViewHolder holder, int position) {
holder.txtcontent.setText(listacontenido.get(position).getContentestado().toString());
holder.nomb.setText(listacontenido.get(position).getNombree().toString());
    }

    @Override
    public int getItemCount() {
        return listacontenido.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
TextView nomb, txtcontent;
ImageButton imageButton;

        public ViewHolder(View itemView) {
            super(itemView);
            nomb=itemView.findViewById(R.id.nombreusuario);
            txtcontent=itemView.findViewById(R.id.estadotext);
        }
    }
}

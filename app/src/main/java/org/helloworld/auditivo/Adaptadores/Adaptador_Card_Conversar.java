package org.helloworld.auditivo.Adaptadores;

import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.helloworld.auditivo.Clases.Elementos_Card_Conversacion;
import org.helloworld.auditivo.R;
import java.util.List;
import java.util.Locale;


/**
 * Created by azulm on 21/04/2018.
 */

public class Adaptador_Card_Conversar extends RecyclerView.Adapter<Adaptador_Card_Conversar.adaptadorHolder>{
        private View.OnClickListener listener;
        private View.OnLongClickListener listenerLong;


    List<Elementos_Card_Conversacion> ListaElemen;
    public TextToSpeech toSpeech;
    int result=0;


    public Adaptador_Card_Conversar(List<Elementos_Card_Conversacion> listaElemen) {
        this.ListaElemen = listaElemen;
    }

    @Override
    public adaptadorHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        //Tospeech

        try {
            toSpeech = new TextToSpeech(parent.getContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        result = toSpeech.setLanguage(Locale.getDefault());
                    } else {
                        Toast.makeText(parent.getContext(), "Feature not supported in your device", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_conversacion,parent,false);
        final adaptadorHolder holder = new adaptadorHolder(v);

    holder.contenedor.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        toSpeech.speak(ListaElemen.get(holder.getAdapterPosition()).getMensaje(),TextToSpeech.QUEUE_FLUSH, null);
        }
});

holder.contenedor.setOnLongClickListener(new View.OnLongClickListener() {
    @Override
    public boolean onLongClick(View view) {

        return false;
    }
});

        return holder;
    }

    @Override
    public void onBindViewHolder(Adaptador_Card_Conversar.adaptadorHolder holder, int position) {
        try {
            holder.txtMensaje.setText(ListaElemen.get(position).getMensaje().toString());
            holder.txtNombre.setText(ListaElemen.get(position).getNombre().toString());

           if(ListaElemen.get(position).isLado()){
               //si si es, es el usuario
               holder.contenedor.setBackgroundColor(Color.WHITE);
               holder.txtNombre.setTextColor(Color.RED);
           }else{
               holder.content.setGravity(Gravity.RIGHT);
               holder.txtNombre.setTextColor(Color.BLUE);

           }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return ListaElemen.size();
    }

    public class adaptadorHolder extends RecyclerView.ViewHolder{
            TextView txtMensaje,txtNombre;
            LinearLayout content;
            CardView contenedor;
        public adaptadorHolder(View itemView) {
            super(itemView);
            txtMensaje=itemView.findViewById(R.id.txtMensaje);
            txtNombre=itemView.findViewById(R.id.txtNombre);
            contenedor=itemView.findViewById(R.id.contenedor);
            content=itemView.findViewById(R.id.content);

        }
    }
}

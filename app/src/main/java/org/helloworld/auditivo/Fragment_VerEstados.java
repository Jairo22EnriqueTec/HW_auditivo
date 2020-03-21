package org.helloworld.auditivo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.helloworld.auditivo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_VerEstados extends Fragment {
/*EditText est;
ImageButton imageButton;
RecyclerView recyclerView;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    ArrayList<Adaptador_Recyclerm>listaestado;
    ProgressDialog progressDialog;
    EditText texto;
    ImageButton botonsubir;
    String nombre;*/
    public Fragment_VerEstados() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__ver_estados, container, false);
       /* Datos_Usuario conex=new Datos_Usuario(getActivity(),"DBUsuario",null,2);
        SQLiteDatabase db=conex.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT Nombre FROM Usuario",null);
        try{
            if(cursor.moveToFirst()){
                nombre=cursor.getString(0);
            }
        }catch (Exception e){
            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
        }
est=view.findViewById(R.id.textoEstado);
imageButton=view.findViewById(R.id.subir_estado);
recyclerView=view.findViewById(R.id.bdrecyclerview3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);
        listaestado=new ArrayList<>();
requestQueue= Volley.newRequestQueue(getActivity());
texto=view.findViewById(R.id.textoEstado);
botonsubir=view.findViewById(R.id.subir_estado);
botonsubir.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT).show();
        //cargar2();
    }
});
       cargar();*/
        return  view;
    }


    /*private void cargar2() {
        progressDialog.setMessage("Subiendo Estado...");
        progressDialog=new ProgressDialog(getActivity());
        progressDialog.show();
        String url="http://192.168.64.2/BasedeDatos/RegistroEstado.php?Nombre="+nombre+"&Estado="+texto.getText().toString();
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
progressDialog.hide();
                Toast.makeText(getActivity(), "Se ha publicado", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "WS No conecta "+error.toString(), Toast.LENGTH_SHORT).show();
                Log.i("WSES", error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }*/

    /*private void cargar() {
        String url="http://192.168.64.2/BasedeDatos/ConsultarEstados2.php/";
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog=new ProgressDialog(getActivity());
progressDialog.setMessage("Cargando");
progressDialog.show();
                Adaptador_Recyclerm rec = null;
                JSONArray jsonArray=response.optJSONArray("Estados");
                try{
                  for (int i=0;i<jsonArray.length();i++){
                      rec=new Adaptador_Recyclerm();
                      JSONObject jsonObject=null;
                      jsonObject=jsonArray.getJSONObject(i);
                      rec.setNombre(jsonObject.optString("Nombre"));
                      rec.setContentestado(jsonObject.optString("Estado"));
                      listaestado.add(rec);
                  }
                  progressDialog.hide();
                    Adaptador_mostrar adaptador_mostrar=new Adaptador_mostrar(listaestado);
                    recyclerView.setAdapter(adaptador_mostrar);
                }catch(Exception e){
                    Toast.makeText(getActivity(), "No se pudo conectar "+response, Toast.LENGTH_SHORT).show();
                    Log.i("ERRORES",response.toString());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
progressDialog.hide();
                Toast.makeText(getActivity(), "error"+error.toString(), Toast.LENGTH_SHORT).show();
                Log.i("WS",error.toString());
            }
        });
    }*/

}

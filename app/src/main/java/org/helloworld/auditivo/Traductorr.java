package org.helloworld.auditivo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.helloworld.auditivo.Clases.VariablesYDatos;

import java.util.ArrayList;
import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Traductorr.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Traductorr#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Traductorr extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    GifImageView gf;
    public static EditText txtTraducir;
    TextView txtProgreso;
    ImageButton btnTraducir,btnLimpiar,btnMicroTra,btnCameraTra;
    ImageView PlayPause;
    int mayor=0,contador=1;
    String palMayor="";
    ArrayList<Integer> lista;
    int VideoPSettear=0;
    SpeechRecognizer mSpeechRecognizer;
    CountDownTimer countDownTimer;
    Switch swvelocidad;
    int milliseconds=2700;
    private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084;
    private OnFragmentInteractionListener mListener;

    public Traductorr() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Traductorr.
     */
    // TODO: Rename and change types and number of parameters
    public static Traductorr newInstance(String param1, String param2) {
        Traductorr fragment = new Traductorr();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_traductorr, container, false);
        lista=new ArrayList<>();
        swvelocidad = view.findViewById(R.id.swvelocidad);
        swvelocidad.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    swvelocidad.setText("Rápido");
                    milliseconds=1100;
                }else{
                    swvelocidad.setText("Lento");
                    milliseconds=2500;
                }
            }
        });

        txtProgreso=view.findViewById(R.id.txtProgreso);
        PlayPause=view.findViewById(R.id.btnPausePlay);
        //----------------------
        gf=view.findViewById(R.id.gif);
        gf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    countDownTimer.cancel();
                    countDownTimer.start();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        txtTraducir=view.findViewById(R.id.txtTraducir);
        try{
            txtTraducir.setText(getActivity().getIntent().getStringExtra("tra"));

        }catch (Exception e){
            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();
        }

        if(!txtTraducir.getText().toString().equals("")){
            lista.clear();
            contador=1;
            comparar(remplazarComunes(txtTraducir.getText().toString()).split(" "));
        }
        btnLimpiar=view.findViewById(R.id.btnLimpiar);
        btnTraducir=view.findViewById(R.id.btnTraducir);
        btnTraducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtTraducir.getText().toString().equalsIgnoreCase("")){
                    show("Proporciona algo para traducir.");
                    return;
                }
                lista.clear();
                contador=1;

                comparar(remplazarComunes(txtTraducir.getText().toString()).split(" "));
            }
        });
        checkPermission();


        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(getActivity());


        final Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());

        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

                Log.i("AQUI", "ready");
            }

            @Override
            public void onBeginningOfSpeech() {
                Log.i("AQUI", "begin");
            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {
                Log.i("AQUI", "buffer");
            }

            @Override
            public void onEndOfSpeech() {

                Log.i("AQUI", "end");

            }

            @Override
            public void onError(int i) {
                Log.i("AQUI", "error" + i);

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResults(Bundle bundle) {
                //getting all the matches
                ArrayList<String> matches = bundle
                        .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null) {
                    String input = matches.get(0);
                    txtTraducir.setText(input.toString());
                    if(txtTraducir.getText().toString().equalsIgnoreCase("")){
                        show("Proporciona algo para traducir.");
                        return;
                    }
                    lista.clear();
                    contador=1;

                    comparar(remplazarComunes(txtTraducir.getText().toString()).split(" "));
                }
            }
            @Override
            public void onPartialResults(Bundle bundle) {
                ArrayList<String> matches = bundle
                        .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                Log.i("AQUI", "partial");
                //displaying the first match
            }
            @Override
            public void onEvent(int i, Bundle bundle) {
            }
        });
        btnCameraTra=view.findViewById(R.id.btncameratraducir);
        btnCameraTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTraducir.setText("");
                startActivity(new Intent(getActivity(), Lector_Traductor.class));
            }
        });
        btnMicroTra=view.findViewById(R.id.btnmicrotraducir);
        btnMicroTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTraducir.setText("");
                mSpeechRecognizer.startListening(mSpeechRecognizerIntent);

            }
        });
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTraducir.setText("");
            }
        });

    return  view;
    }
    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + getActivity().getPackageName()));
                startActivity(intent);

            }
        }
    }
    void show(String text){
        Toast.makeText(getActivity(),text,Toast.LENGTH_SHORT).show();
    }
    void comparar(String partes []){
        String palabraAC="",palabra="";
        boolean sino=false;
        String [] partesnew=BuscarNumeros(partes);
        for(int i = 0;i<partesnew.length;i++) { //repasar el input
            for (int j = 0; j < VariablesYDatos.etiquetas.length; j++) {
                palabraAC = LimpiarCadenas(partesnew[i]);
                palabraAC=palabraAC.replace(" ","");
                palabra = VariablesYDatos.etiquetas[j][0].toString();
                compararLetras(palabraAC, palabra);
            }
            if (mayor != 0) {
                //-------------si no esta-----------------------------------------------
                if(mayor<3 && !palabraAC.equalsIgnoreCase("ir")&&!palabraAC.equalsIgnoreCase("tu")&&
                        !palabraAC.equalsIgnoreCase("yo") && !EsNumero(palabraAC)){

                    String[] palregresada =SepararLetras(palabraAC);
                    int pos=0;
                    for(int m = 0;m<palregresada.length;m++){
                        for (int k = 0; k < VariablesYDatos.etiquetas.length; k++) {
                            if (palregresada[m].equalsIgnoreCase(VariablesYDatos.etiquetas[k][0].toString())) {
                                pos=k;
                            }
                        }
                        VideoPSettear=((int)(VariablesYDatos.etiquetas[pos][1]));
                        lista.add(VideoPSettear);
                        pos=0;
                    }
                    continue;
                }
                //-----------_______---------------------------------------------------------
                int pos=0;
                for (int k = 0; k < VariablesYDatos.etiquetas.length; k++) {
                    if (palMayor.equalsIgnoreCase(VariablesYDatos.etiquetas[k][0].toString())) {
                        pos=k;
                    }
                }
                VideoPSettear=((int)(VariablesYDatos.etiquetas[pos][1]));
                lista.add(VideoPSettear);
            }
            palMayor="";
            mayor=0;
            if(sino){
                i++;
                sino=false;
            }
        }
        gf.setImageResource(lista.get(0));
        txtProgreso.setText("1/"+lista.size());
        video();
    }
    String remplazarComunes(String frase){

        String [] c= frase.split(" ");
        for(int i = 0;i<c.length;i++){
            //pasado
            if(c[i].endsWith("í")){
                c[i]=c[i] +" ayer";
            }
            //futuro
            if((c[i].endsWith("é")||c[i].endsWith("ría")||c[i].endsWith("ás"))){
                if(!c[i].equalsIgnoreCase("qué")&&!c[i].equalsIgnoreCase("sé")
                        &&!c[i].equalsIgnoreCase("estás")&&!c[i].equalsIgnoreCase("bebé")){
                    c[i]=c[i] +" mañana";
                }
            }
        }
        frase ="";
        for(int i = 0;i<c.length;i++){
            frase+= c[i]+ " ";
        }

        frase=LimpiarCadenas(frase);
        frase=frase.replace("fui","ir ayer");
        frase=frase.replace("ire","ir");
        frase=frase.replace(":"," ");
        frase=frase.replace("entiendes","entender");
        frase=frase.replace("entendiste","entender");
        frase=frase.replace("entiendido","entender");
        frase=frase.replace("hicieron","ustedes hacer ayer");
        frase=frase.replace("hiciste","tu hacer ayer");
        frase=frase.replace("haran","ustedes hacer manana");
        frase=frase.replace("novia","novio mujer");
        frase=frase.replace("mucho gusto","gustoenconocerte");
        frase=frase.replace("dame","dar");



        frase=frase.replace("buenas noches","buenasnoches");
        frase=frase.replace("buena noche","buenasnoches");
        frase=frase.replace("buenas taredes","buenastardes");
        frase=frase.replace("buena tarde","buenastardes");
        frase=frase.replace("buenos dias","buenosdias");
        frase=frase.replace("buen dia","buenosdias");
        frase=frase.replace("como estas","comoestas");
        frase=frase.replace("como estan","comoestas");
        frase=frase.replace("como esta","comoestas");
        frase=frase.replace("cual es tu nombre","tnombre");
        frase=frase.replace("cuál es tu nombre","tnombre");
        frase=frase.replace("como te llamas","tnombre");
        frase=frase.replace("cómo te llamas","tnombre");
        frase=frase.replace("dime tu nombre","tnombre");
        frase=frase.replace("de nada","denada");
        frase=frase.replace("gusto en conocerte","gustoenconocerte");
        frase=frase.replace("gusto en verte","gustoenconocerte");
        frase=frase.replace("hasta luego","nosvemos");
        frase=frase.replace("nos vemos","nosvemos");
        frase=frase.replace("adios","nosvemos");
        frase=frase.replace("a dios","nosvemos");
        frase=frase.replace("medio","masomenos");
        frase=frase.replace("por que","porque");
        frase=frase.replace("para que","paraque");
        frase=frase.replace("mas o menos","masomenos");

        frase=frase.replace("en adelante","enadelante");
        frase=frase.replace("para delante","enadelante");
        frase=frase.replace("adelante","enadelante");
        frase=frase.replace("delante","enadelante");
        frase=frase.replace("hacia delante","enadelante");
        frase=frase.replace("posterior","despues");
        frase=frase.replace("posteriormente","despues");
        frase=frase.replace("medio dia","mediodia");
        frase=frase.replace("otra vez","otravez");
        frase=frase.replace("todavia no","todaviano");
        frase=frase.replace("aun no","todaviano");
        frase=frase.replace("una vez","unavez");
        frase=frase.replace("primera vez","primeravez");
        frase=frase.replace("por primera vez","primeravez");
        frase=frase.replace("mi primera vez","primeravez");

        frase=frase.replace("no poder","nopoder");
        frase=frase.replace("no puedo","nopoder");
        frase=frase.replace("puedo","poder");
        frase=frase.replace("pude","poder");
        frase=frase.replace("podre","poder");
        frase=frase.replace("no pude","nopoder");
        frase=frase.replace("no podre","nopoder");

        frase=frase.replace("voy","yo ir");
        frase=frase.replace("dime","tu decir");

        frase=frase.replace("comi","comer");
        frase=frase.replace("comere","comer");

        frase=frase.replace("te "," tu ");

        frase=frase.replace("mio","yo");
        frase=frase.replace(" mi ","yo");
        frase=frase.replace("usted","tu");
        frase=frase.replace("nuestro","nosotros");
        frase=frase.replace("tuyo","tu");

        frase=frase.replace(" ve ","tu ir");
        frase=frase.replace("vete","tu ir");

        frase=frase.replace("suyo","ellos");

        frase=frase.replace("ahora","ahorita");

        frase=frase.replace(" a ","");
        frase=frase.replace(" las ","");
        frase=frase.replace(" la "," l ");
        frase=frase.replace("roja","rojo");

        frase=frase.replace(" uno ","1");
        frase=frase.replace(" dos ","2");
        frase=frase.replace("tres","3");
        frase=frase.replace("cuatro","4");
        frase=frase.replace("cinco","5");
        frase=frase.replace("seis","6");
        frase=frase.replace("siete","7");
        frase=frase.replace("ocho","8");
        frase=frase.replace("nueve","9");
        frase=frase.replace("diez","10");
        frase=frase.replace("once","11");
        frase=frase.replace("doce","12");
        frase=frase.replace("trece","13");
        frase=frase.replace("catorce","14");
        frase=frase.replace("quince","15");
        frase=frase.replace("diezyseis","16");
        frase=frase.replace("diezysiete","17");
        frase=frase.replace("diezyocho","18");
        frase=frase.replace("diezynueve","19");
        frase=frase.replace("veinte","20");

        //--------numeros


        return frase;
    }
    String LimpiarCadenas(String palabraAC){
        String original = "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖØÙÚÛÜÝßàáâãäåæçèéêëìíîïðñòóôõöøùúûüýÿ";
        // Cadena de caracteres ASCII que reemplazarán los originales.
        String ascii = "AAAAAAACEEEEIIIIDNOOOOOOUUUUYBaaaaaaaceeeeiiiionoooooouuuuyy";
        for (int k=0; k<original.length(); k++) {
            palabraAC = palabraAC.replace(original.charAt(k), ascii.charAt(k));
        }
        palabraAC=palabraAC.toLowerCase();
        palabraAC = palabraAC.replace("?", "");palabraAC = palabraAC.replace("!", "");palabraAC = palabraAC.replace("¿", "");
        palabraAC = palabraAC.replace("¡", "");palabraAC = palabraAC.replace(",", "");palabraAC = palabraAC.replace(".", "");
        //palabraAC = palabraAC.replace(" ", "");
        return palabraAC;
    }
    String [] BuscarNumeros(String[] frase){
        ArrayList<String> pal =new ArrayList<>();

        for(int i = 0;i<frase.length;i++){
            if(EsNumero(frase[i])){
                if(frase[i].substring(0,1).equals("0")){
                    frase[i]=frase[i].substring(1);
                }
                int lent=frase[i].length();
                if(lent==3){
                    pal.add(frase[i].substring(0,1)+"00");
                    if(!frase[i].substring(1,2).equalsIgnoreCase("1")){
                        if(frase[i].substring(1,2).equalsIgnoreCase("0")){
                            pal.add(frase[i].substring(2,3));
                        }else{
                            pal.add(frase[i].substring(1,2)+"0");
                            if(!frase[i].substring(2,3).equals("0")){
                                pal.add(frase[i].substring(2,3));
                            }
                        }
                    }else{
                        pal.add(frase[i].substring(1,3));
                    }
                }
                else if(lent==2){
                    if(!frase[i].substring(0,1).equalsIgnoreCase("1")){
                        if(frase[i].substring(0,1).equalsIgnoreCase("0")){
                            pal.add(frase[i].substring(1,2));
                        }else{
                            pal.add(frase[i].substring(0,1)+"0");
                            if(!frase[i].substring(1,2).equals("0")){
                                pal.add(frase[i].substring(1,2));
                            }

                        }
                    }else{
                        pal.add(frase[i].substring(0,2));
                    }
                }

            }else{
                pal.add(frase[i].toString());
            }
        }
        String[] reg = new String[pal.size()];
        for(int i = 0;i<pal.size();i++){
            reg[i] = pal.get(i);
        }
        //no debe regresar frase
        return reg;
    }

    void compararLetras(String palabraAC,String palabra){

        int total = palabra.length();
        int totalAC = palabraAC.length();
        int aciertos=0,menor=0;
        if(total<totalAC){
            menor=total;
        }else if(total==totalAC){
            menor=total;
        }else{
            menor=totalAC;
        }
        for(int i = 0;i<menor;i++){
            if(palabra.charAt(i)==palabraAC.charAt(i)){
                aciertos++;
            }
        }
        int res = (int) (menor/2);
        //System.out.println("res: "+res+"\nAciertos: "+aciertos+"\n------------------------");
        if(aciertos>mayor && aciertos>res){
            mayor=aciertos;
            palMayor=palabra;
        }

//        return aciertos>=res;
    }
    boolean EsNumero(String num){
        try{
            int no = Integer.parseInt(num);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    String[] SepararLetras(String palabra){
        String[] res=new String[palabra.length()];
        for(int i = 0;i<palabra.length();i++){
            res[i]=palabra.charAt(i)+"";
        }
        return res;
    }
    void video (){
        countDownTimer = new CountDownTimer(milliseconds, 1000) { // adjust the milli seconds here
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
                if(contador==lista.size()){
                    contador=0;
                    PlayPause.setImageResource(R.drawable.ic_action_reload_video);
                    return;
                }
                gf.setImageResource(lista.get(contador));
                txtProgreso.setText((contador+1)+"/"+lista.size());
                contador++;
                video();
            }
        }.start();
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

package org.helloworld.auditivo.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jj on 08/07/20.
 */

public class Cliente {
    public static Retrofit getCliente(String url){
Retrofit retrofit=new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
return  retrofit;
    }
}

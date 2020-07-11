package org.helloworld.auditivo.Utils;

import android.telecom.Call;

import org.helloworld.auditivo.Model.Usuario;

import java.util.List;
import retrofit2.http.GET;

/**
 * Created by jj on 08/07/20.
 */

public interface PersonaService {
    @GET("listar/")
    retrofit2.Call<List<Usuario>> getUsuario();
}

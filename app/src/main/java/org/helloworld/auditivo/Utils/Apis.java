package org.helloworld.auditivo.Utils;

/**
 * Created by jj on 08/07/20.
 */

public class Apis {
public static final String URL_001="https://helloworldapp.000webhostapp.com/";
public static PersonaService getPersonaService(){
    return Cliente.getCliente(URL_001).create(PersonaService.class);
}
}

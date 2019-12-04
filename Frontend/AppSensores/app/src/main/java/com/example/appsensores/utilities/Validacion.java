package com.example.appsensores.utilities;

public class Validacion {
    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String numpattern = "[4-9][0-9]{7}";
    String alfanumpattern = "[a-zA-Z0-9]+";
    String codigopattern = "[A-Z0-9]+";

    public boolean validacionEmail(String text){
        Boolean resultado;
        if(text.isEmpty()){
            resultado = false;
        }else{
            if(text.trim().matches(emailpattern)){
                resultado = true;
            }else{
                resultado = false;
            }
        }
        return resultado;
    }

    public boolean validacionNumerica(String text){
        Boolean resultado;
        if(text.isEmpty()){
            resultado = false;
        }else{
            if(text.trim().matches(numpattern)){
                resultado = true;
            }else{
                resultado = false;
            }
        }
        return resultado;
    }

    public boolean validacionAlfaNumerica(String text){
        Boolean resultado;
        if(text.isEmpty()){
            resultado = false;
        }else{
            if(text.trim().matches(alfanumpattern)){
                resultado = true;
            }else{
                resultado = false;
            }
        }
        return resultado;
    }

    public boolean validacionCodigo(String text){
        Boolean resultado;
        if(text.isEmpty()){
            resultado = false;
        }else{
            if(text.trim().matches(codigopattern)){
                resultado = true;
            }else{
                resultado = false;
            }
        }
        return resultado;
    }
}

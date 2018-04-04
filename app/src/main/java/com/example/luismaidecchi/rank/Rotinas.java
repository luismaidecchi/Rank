package com.example.luismaidecchi.rank;

/**
 * Created by luismaidecchi on 21/04/17.
 */

public class Rotinas {
    public int RetornaEspacosEOCodigo_TamanhoCodigo;
    public String RetornaEspacosEOCodigo_Codigo;

    public String RetornaEspacosEOCodigo() {

        int qtd = 6 - String.valueOf(RetornaEspacosEOCodigo_TamanhoCodigo).toString().length();
        StringBuilder Espacos = new StringBuilder();

        for (int i = 1; i <= qtd; i++) {
            Espacos.append(" ");
        }

        return Espacos.toString() + String.valueOf(RetornaEspacosEOCodigo_Codigo);
    }
}

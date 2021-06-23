package br.com.william.a2pdm.util;

public class StringUtil {
    public static String preencheZero(String texto, int casas) {
        while(texto.length() < casas) {
            texto = "0".concat(texto);
        }

        return texto;
    }
}

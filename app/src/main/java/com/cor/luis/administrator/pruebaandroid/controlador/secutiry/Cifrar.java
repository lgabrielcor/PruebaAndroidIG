package com.cor.luis.administrator.pruebaandroid.controlador.secutiry;

/**
 * Created by luisgabrielcorredorcombita on 17/07/16.
 */

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;



public class Cifrar {

    public static String cifrarMD5(String dato){
        String md5Hex = new String(Hex.encodeHex(DigestUtils.md5(dato)));
        return md5Hex;
    }

    public static String descifrarMD5(String dato){
        String decode = "";
        try {

            decode= new String(Hex.decodeHex(dato.toCharArray()));
        } catch (DecoderException e) {
            e.printStackTrace();
        }

        return decode;
    }

    public static String bytesToHex(byte[] bytes, char[] strArray) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = strArray[v >>> 4];
            hexChars[j * 2 + 1] = strArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}

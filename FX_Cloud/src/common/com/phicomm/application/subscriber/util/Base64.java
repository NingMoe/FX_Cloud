package com.phicomm.application.subscriber.util;

import java.nio.charset.Charset;

import sun.misc.BASE64Decoder;

public class Base64 {
    public static String encode64(String s) {
        if( s == null ) {
            return null;
        }
        return (new sun.misc.BASE64Encoder()).encode( s.getBytes(Charset.forName("utf-8")) );
    }

    public static String decode64(String s) {
        if( s == null ) {
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            return new String( decoder.decodeBuffer(s),"utf-8");
        } catch (Exception ex ) {
            ex.printStackTrace();
        }
        return null;
    }
}

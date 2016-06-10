package org.evilcamp.v.utils;


public class LogUtil {
    public static String geneRequestId(){

        return MD5.result(System.currentTimeMillis(), Thread.currentThread().getId());
    }

}

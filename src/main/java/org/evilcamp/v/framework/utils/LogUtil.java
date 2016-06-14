package org.evilcamp.v.framework.utils;


public class LogUtil {

    public static String geneRequestId(){
        return MD5.result(System.currentTimeMillis(), Thread.currentThread().getId());
    }

}

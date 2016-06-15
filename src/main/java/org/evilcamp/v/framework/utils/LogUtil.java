package org.evilcamp.v.framework.utils;


public class LogUtil {

    public static String geneRequestId(){
        return MD5Util.result(System.currentTimeMillis(), Thread.currentThread().getId());
    }

}

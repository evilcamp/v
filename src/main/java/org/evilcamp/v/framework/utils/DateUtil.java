package org.evilcamp.v.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String getCommonFormat(Date date){
        if(date==null){
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

}

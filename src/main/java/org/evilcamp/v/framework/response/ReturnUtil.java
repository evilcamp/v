package org.evilcamp.v.framework.response;

import org.evilcamp.v.framework.utils.JsonUtil;

public class ReturnUtil {

    public static ReturnMsg buildMsg(String code,String msg,Object data){
        ReturnMsg rm = new ReturnMsg();
        rm.setCode(code);
        rm.setMsg(msg);
        rm.setData(data);
        return rm;
    }


    public static ReturnMsg buildSuccessMsg(){
        return ReturnUtil.buildMsg("200","success",null);
    }

    public static ReturnMsg buillFailedMsg(){
        return ReturnUtil.buildMsg("505","failed",null);
    }

    public static String buildSuccessMsgStr(){
        return JsonUtil.getJsonString(ReturnUtil.buildSuccessMsg());
    }

    public static String buildSuccessMsgStr(Object data){
        ReturnMsg result = ReturnUtil.buildSuccessMsg();
        result.setData(data);
        return JsonUtil.getJsonString(result);
    }

    public static String buillFailedMsgStr(){
        return JsonUtil.getJsonString(ReturnUtil.buillFailedMsg());
    }


}

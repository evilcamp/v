package org.evilcamp.v.framework.utils;

import org.springframework.util.StringUtils;

/**
 * 把字符串判断操作收敛到这里,如果以后更换字符串工具类
 * 目前使用spring的字符串工具类
 */
public class StringTool {

    public static boolean hasText(String text){
        return StringUtils.hasText(text);
    }
}

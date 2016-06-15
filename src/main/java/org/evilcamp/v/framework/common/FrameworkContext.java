package org.evilcamp.v.framework.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.spi.LoggerContext;
import org.evilcamp.v.framework.exception.VException;
import org.evilcamp.v.framework.utils.StringTool;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * 此类存放应用程序上下文环境,以及初始化
 */
public class FrameworkContext {

    /**
     * 配置文件根目录,所有配置文件加载,取文件根目录相对路径,不使用默认路径,保证配置文件加载过程清晰.
     */
    private static String rootConfigDir = "";

    private static String URL_SEPARATOR = "/";


    public static String getRootConfigDir(){
        if(StringTool.hasText(rootConfigDir)){
            return rootConfigDir;
        }
        URL url = FrameworkContext.class.getProtectionDomain()
                .getCodeSource().getLocation();

        String currentFilePath = null;
        try {
            currentFilePath = URLDecoder.decode(url.getPath(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        File file = new File(currentFilePath);
        String fileAbsolutePath = file.getAbsolutePath();

        // 兼容class方式运行
        if(fileAbsolutePath.endsWith(".class")){
            rootConfigDir = fileAbsolutePath.substring(0,
                    fileAbsolutePath.lastIndexOf("classes"));
        }else{
            throw new VException("获取配置路径失败");
        }
        return rootConfigDir;
    }


    public static void init(){
        System.setProperty("log4j.configurationFile",rootConfigDir+"log4j2.xml");
        LoggerContext context =(LoggerContext) LogManager.getContext(false);


    }
}

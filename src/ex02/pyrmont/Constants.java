package ex02.pyrmont;

import java.io.File;

public class Constants {
    // System.getProperty("user.dir") 当前工程路径
    // File.separator 分隔符，不同系统分隔符不同,解决系统路径兼容问题
    public static final String WEB_ROOT =
            System.getProperty("user.dir") + File.separator +"webroot";
}

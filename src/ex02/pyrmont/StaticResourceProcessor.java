package ex02.pyrmont;

import java.io.IOException;

/**
 * 用于处理静态资源的请求
 */
public class StaticResourceProcessor {
    public void process(Request request , Response response ){
        try {
            response.sendStaticResource();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}

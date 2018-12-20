package ex02.pyrmont;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

public class ServletProcessor1 {
    public void process(Request request , Response response){
        //设定uri格式：/servlet/servletName
        String uri = request.getUri();
        //得到类名，载入类
        String servletName = uri.substring(uri.lastIndexOf("/")+1);
        //载入类用
        URLClassLoader loader = null;
        try {
            // URL对象指明了类载入器到哪查找类："/"结尾指向目录，否则指向JAR文件
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File(Constants.WEB_ROOT);
            // 类载入器查找servlet类的目录称为仓库repository
            // 查找仓库目录
            String repository =
                    (new URL("file",null,classPath.getCanonicalPath()+File.separator)).toString();
            // streamHandler是为了指明URL调用哪个构造器
            urls[0] = new URL(null,repository, streamHandler);
            loader = new URLClassLoader(urls);
        }
        catch (IOException e){
            System.out.println(e.toString());
        }

        Class myClass = null;
        try {
            // 载入servlet类
            // 名字取到了，但是没能加载到
            myClass = loader.loadClass(servletName);
        }
        catch (ClassNotFoundException e){
            System.out.println(e.toString());
        }

        Servlet servlet = null;
        try {
            // 创造servlet实例,调用service方法
            servlet = (Servlet)myClass.newInstance();
            servlet.service((ServletRequest) request , (ServletResponse) response);
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        catch (Throwable e){
            System.out.println(e.toString());
        }

    }
}

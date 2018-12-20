package ex02.pyrmont;

import ex01.pyrmont.HttpServer;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

public class Response implements ServletResponse {

    private static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream output;
    PrintWriter writer;
    public Response(OutputStream output) {
        this.output = output;
    }
    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        try {
            // 书上方法
//            File file = new File(HttpServer.WEB_ROOT, request.getUri());
//            fis = new FileInputStream(file);
//            int ch = fis.read(bytes, 0, BUFFER_SIZE);
//            while (ch!=-1) {
//                output.write(bytes, 0, ch);
//                ch = fis.read(bytes, 0, BUFFER_SIZE);
//            }

            // 修改过的方法
            File file = new File(HttpServer.WEB_ROOT, request.getUri());
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuffer stringBuffer = new StringBuffer() ;
            String line = null;
            while ((line = reader.readLine()) != null){
                stringBuffer.append(line).append("\r\n");
            }
            StringBuffer result = new StringBuffer();
            result.append("HTTP/1.1 200 OK\r\n");
            result.append("Content-Type:text/html\r\n");
            result.append("Content-Length:" + file.length() + "\r\n");
            result.append("\r\n"+stringBuffer.toString());
            output.write(result.toString().getBytes());
            output.flush();
            output.close();
        }
        catch (FileNotFoundException e) {
             String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: 23\r\n" +
                "\r\n" +
                "<h1>File Not Found</h1>";
             output.write(errorMessage.getBytes());
        }
        finally {
            if (fis!=null){
                fis.close();
            }
        }
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        // autoflush is true, println() will flush,(刷新输出)
        // but print() will not.
        writer = new PrintWriter(output,true);
        return writer;
    }


    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public void setCharacterEncoding(String s) {

    }

    @Override
    public void setContentLength(int i) {

    }

    @Override
    public void setContentType(String s) {

    }

    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale locale) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}

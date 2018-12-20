package ex01.pyrmont;

import java.io.*;


/*
HTTP Response = Status-Line
*(( general-header | response-header | entity-header ) CRLF)
CRLF
[ message-body ]
Status-Line = HTTP-Version SP Status-Code SP Reason-Phrase CRLF
*/
public class Response {
    private static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream output;
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
            File file = new File(HttpServer.WEB_ROOT,request.getUri());
            if (file.exists()) {
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
//                fis = new FileInputStream(file);
//                int ch = fis.read(bytes, 0, BUFFER_SIZE);
//                while (ch!=-1) {
//                    output.write(bytes, 0, ch);
//                    ch = fis.read(bytes, 0, BUFFER_SIZE);
//                }
            }
            else {
                // file not found
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: 23\r\n" +
                        "\r\n"+
                        "<h1>File Not Found</h1>";
                output.write(errorMessage.getBytes());
            }
        }
        catch (Exception e) {
            // thrown if cannot instantiate a File object
            System.out.println(e.toString() );
        }
        finally {
            if (fis!=null)
                fis.close();
        }
    }
}

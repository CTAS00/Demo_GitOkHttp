package ctas.httpframework.http.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by CTAS on 2017/12/29.
 */
public abstract class AbstractHttpRequest implements HttpRequest{

    private static final String GZIP ="gzip" ;
    private GZIPOutputStream gzipOutputStream;


    private HttpHeader httpHeader = new HttpHeader();



    private boolean isGzip(){
        String content_encording = getHttpHeadder().getContentEncoding();
        if(GZIP.equals(content_encording)){
            return true;
        }
        return false;
    }
    private OutputStream getGzipOutputStream(OutputStream outputStream) throws IOException {
        return new GZIPOutputStream(outputStream);

    }

    @Override
    public OutputStream getBody()throws IOException {
        OutputStream outputStream =getBodyOutputstream();
        if(isGzip()){
            outputStream = getGzipOutputStream(getBodyOutputstream());
        }
        return outputStream;
    }

    protected abstract OutputStream getBodyOutputstream();

    @Override
    public HttpReponse execute() throws IOException {
        // 这个方法也要重写
        if(gzipOutputStream!=null){
            gzipOutputStream.close();
        }
        // 当要执行网络请求的时候 需要请求头和body  body是post请求时候所需的
        return internalExecute(httpHeader);
    }

    protected abstract HttpReponse internalExecute(HttpHeader httpHeader) throws IOException;

    @Override
    public HttpHeader getHttpHeadder() {
        return httpHeader;
    }
}

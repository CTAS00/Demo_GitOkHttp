package ctas.httpframework.http.http;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by CTAS on 2017/12/29.
 */
public interface HttpReponse extends Header{
    HttpStatus getCode();
    String getMessage();
    InputStream getBody() throws IOException;
    void close() throws IOException;
    long getContentLength();
}

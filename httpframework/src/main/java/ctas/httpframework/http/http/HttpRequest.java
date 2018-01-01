package ctas.httpframework.http.http;

import android.net.Uri;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by CTAS on 2017/12/29.
 */
public interface HttpRequest extends Header {

    HttpMethod getHttpMethod();
    Uri getUri();
    OutputStream getBody() throws IOException;
    HttpReponse execute() throws IOException;// 执行的方法



}

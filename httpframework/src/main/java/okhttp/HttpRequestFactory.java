package okhttp;

import android.net.Uri;

import java.io.InputStream;

import javax.net.ssl.SSLSocketFactory;

import ctas.httpframework.http.http.HttpMethod;
import ctas.httpframework.http.http.HttpRequest;

/**
 * Created by CTAS on 2017/12/29.
 */
public interface HttpRequestFactory {

    HttpRequest createRequest(Uri uri,HttpMethod method);

    SSLSocketFactory getSocketFactory(InputStream inputStream);
}

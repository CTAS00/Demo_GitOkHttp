package originhttp;

import android.net.Uri;

import java.net.HttpURLConnection;

import ctas.httpframework.http.http.HttpMethod;
import ctas.httpframework.http.http.HttpRequest;
import okhttp.HttpRequestFactory;

/**
 * Created by CTAS on 2017/12/29.
 */
public class OriginHttpRequestFactory implements HttpRequestFactory {

    private HttpURLConnection mConnection;

    public OriginHttpRequestFactory() {

    }

    public void setReadTimeOut(int readTimeOut) {
        mConnection.setReadTimeout(readTimeOut);
    }

    public void setConnectionTimeOut(int connectionTimeOut) {
        mConnection.setConnectTimeout(connectionTimeOut);
    }

//    @Override
//    public HttpRequest createHttpRequest(Uri uri, HttpMethod method)  {
////        mConnection =
////        mConnection = (HttpURLConnection) uri.toURL().openConnection();
//        return new OriginHttpRequest(mConnection, method, uri.toString());
//    }

    @Override
    public HttpRequest createRequest(Uri uri, HttpMethod method) {
        //TODO connection需要(HttpURLConnection) uri.toURL().openConnection(); 才能打开
        return   new OriginHttpRequest(mConnection, method, uri.toString());
    }
}

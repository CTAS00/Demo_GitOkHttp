package service;

import android.net.Uri;

import java.io.IOException;

import ctas.httpframework.http.http.HttpMethod;
import ctas.httpframework.http.http.HttpRequest;
import okhttp.HttpRequestFactory;
import okhttp.OkHttpRequestFactory;
import originhttp.OriginHttpRequestFactory;

/**
 * Created by CTAS on 2017/12/29.
 */
public class HttpRequestProvider {

    private static boolean OKHTTP_REQUEST = utils.isExist("okhttp3.OkHttpClient", HttpRequestProvider.class.getClassLoader());

    private HttpRequestFactory mHttpRequestFactory;

    public HttpRequestProvider() {
        if (OKHTTP_REQUEST) {
            mHttpRequestFactory = new OkHttpRequestFactory();
        }else {
            mHttpRequestFactory = new OriginHttpRequestFactory();
        }
    }

    public HttpRequest getHttpRequest(Uri uri, HttpMethod httpMethod) throws IOException {
        return mHttpRequestFactory.createRequest(uri, httpMethod);
    }

    public HttpRequestFactory getHttpRequestFactory() {
        return mHttpRequestFactory;
    }

    public void setHttpRequestFactory(HttpRequestFactory httpRequestFactory) {
        mHttpRequestFactory = httpRequestFactory;
    }
}

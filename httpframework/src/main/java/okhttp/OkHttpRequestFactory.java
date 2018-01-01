package okhttp;

import android.net.Uri;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;

import ctas.httpframework.http.http.HttpMethod;
import ctas.httpframework.http.http.HttpRequest;
import okhttp3.OkHttpClient;

/**工厂类  顺带帮其添加一些属性
 * Created by CTAS on 2017/12/29.创建httprequest所要添加的属性
 *
 */
public class OkHttpRequestFactory implements HttpRequestFactory {

    private OkHttpClient mClient;

    public OkHttpRequestFactory() {
        this.mClient = new OkHttpClient();
    }

    public OkHttpRequestFactory(OkHttpClient client) {
        this.mClient = client;
    }

    public void setReadTimeOut(int readTimeOut) {
        this.mClient = mClient.newBuilder().
                readTimeout(readTimeOut, TimeUnit.MILLISECONDS).
                build();
    }

    public void setWriteTimeOut(int writeTimeOut) {
        this.mClient = mClient.newBuilder().
                writeTimeout(writeTimeOut, TimeUnit.MILLISECONDS).
                build();
    }

    public void setConnectionTimeOut(int connectionTimeOut) {
        this.mClient = mClient.newBuilder().
                connectTimeout(connectionTimeOut, TimeUnit.MILLISECONDS).
                build();
    }


    @Override
    public HttpRequest createRequest(Uri uri, HttpMethod method) {
        return new OkHttpRequest(mClient, method, uri.toString());
    }

    @Override
    public SSLSocketFactory getSocketFactory(InputStream inputStream) {



        return null;
    }
}

import java.net.URI;

import basehttp.HttpMethod;
import basehttp.HttpRequest;
import okhttp.HttpRequestFactory;
import okhttp.OkHttpRequestFactory;
import utils.Utils;

/**
 * Created by CTAS on 2018/1/1.
 */
public class HttpRequestProvider {
    private HttpRequestFactory httpRequestFactory;
    private static boolean IS_HAVE_OKHTTP = Utils.isExist("okhttp3.OkHttpClient",HttpRequestProvider.class.getClassLoader());


    public HttpRequestProvider(){
        if(IS_HAVE_OKHTTP){
            httpRequestFactory = new OkHttpRequestFactory();
        }
    }

    public HttpRequest getHttpRequest(String url, HttpMethod method){

    return    httpRequestFactory.createRequest(URI.create(url),method);
    }
}

package newhttp;

import android.net.Uri;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import ctas.httpframework.http.http.HttpReponse;
import ctas.httpframework.http.http.HttpRequest;
import okhttp3.*;
import service.HttpRequestProvider;
import service.convert.Convert;
import utils.TypeUtils;

/**
 * Created by CTAS on 2017/12/30.
 */
public class HttpCall implements HttpEngine {
    private Request request;
    private ExecutorService executorService;
    private List<Convert> converts;
    private Callback callback;
    private HttpRequestProvider provider;
    public HttpCall(Request request, ExecutorService executorService, List<Convert> converts, Callback callback, HttpRequestProvider provider) {
        this.request = request;
        this.executorService = executorService;
        this.converts = converts;
        this.callback = callback;
        this.provider = provider;
    }
    @Override
    public HttpReponse execute() throws IOException {
        HttpRequest request = null;
        request = provider.getHttpRequest(Uri.parse(this.request.getUrl()),this.request.getMethod());
        request.getBody().write(this.request.getData());
        return request.execute();
    }
    public <T> T invoke(Class responseType){
        Type type = TypeUtils.getType(responseType);
        HttpReponse response;
        T result = null;
        try {
            response=execute();
            if(!response.getCode().isSuccess()){
                callback.onFailure(response.getMessage());
                return null;
            }
            result = convertResponse(response,type);
            callback.onSuccess(new Response(result,response));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public <T> T convertResponse(HttpReponse response, Type type) throws IOException {
        for (Convert convert : converts) {
            return (T) convert.parse(response,type);
        }
        return null;
    }
    public <T> Future<T> invoke(){
     return    executorService.submit(new Callable<T>() {
         @Override
         public T call() throws Exception {
             return invoke(callback.getClass());
         }
     });
    }
}

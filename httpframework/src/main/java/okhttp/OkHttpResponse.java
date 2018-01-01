package okhttp;

import java.io.InputStream;

import ctas.httpframework.http.http.AbstractHttpResponse;
import ctas.httpframework.http.http.HttpHeader;
import okhttp3.Response;

/**
 * Created by CTAS on 2017/12/29.
 */
public class OkHttpResponse extends AbstractHttpResponse{
    private Response response;
    private HttpHeader httpheader;

    public OkHttpResponse(Response response){
            this.response = response;
    }


    @Override
    public int getCode() {
        return response.code();
    }

    @Override
    public String getMessage() {
        return response.message();
    }

    @Override
    public HttpHeader getHttpHeadder() {
        if(httpheader == null){
            httpheader = new HttpHeader();
        }
        for (String name : response.headers().names()) {
            httpheader.set(name,response.headers().get(name));
        }
        return httpheader;
    }

    @Override
    protected InputStream getInternalInputstream() {
        return response.body().byteStream();
    }

    @Override
    protected void closeInternal() {
        response.body().close();
    }
}

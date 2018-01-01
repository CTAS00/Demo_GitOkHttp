package okhttp;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

import basehttp.BufferAbstractHttpRequest;
import basehttp.HttpHeader;
import basehttp.HttpMethod;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by CTAS on 2018/1/1.
 */
public class OkHttpRequest extends BufferAbstractHttpRequest {

    private OkHttpClient client;
    private String url;
    private HttpMethod method;

    public OkHttpRequest(OkHttpClient client, String url, HttpMethod method) {
        this.client = client;
        this.url = url;
        this.method = method;
    }

    @Override
    protected void bufferInternalExecute(HttpHeader httpheader, byte[]  data) throws IOException {
        boolean isPost = HttpMethod.POST == method;
        RequestBody requestBody = null;
        if(isPost){
            requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), data) ;
        }
        Request.Builder builder = new Request.Builder().url(url).method(method.name(), requestBody);
        for (Map.Entry<String, String> entry : httpheader.entrySet()) {
            builder.addHeader(entry.getKey(), entry.getValue());
        }
        Response response = client.newCall(builder.build()).execute();
        // 待定
//        return new OkHttpResponse(response);

    }

    @Override
    public URI getUri() {
        return URI.create(url);
    }

    @Override
    public HttpHeader getHttpHeader() {
        return null;
    }
}

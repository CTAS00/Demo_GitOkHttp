package okhttp;

import android.net.Uri;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import ctas.httpframework.http.http.BufferAbstractHttpRequest;
import ctas.httpframework.http.http.HttpHeader;
import ctas.httpframework.http.http.HttpMethod;
import ctas.httpframework.http.http.HttpReponse;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by CTAS on 2017/12/29.
 *
 */
public class OkHttpRequest extends BufferAbstractHttpRequest {

    private OkHttpClient mClient;

    private HttpMethod mMethod;

    private String mUrl;

    public OkHttpRequest(OkHttpClient client, HttpMethod method, String url) {
        this.mClient = client;
        this.mMethod = method;
        this.mUrl = url;
    }

   //  getBody().write("username=haha&userage=20");
    @Override
    protected HttpReponse byteInternalExecute(HttpHeader httpHeader, byte[] bytes) throws IOException {
        boolean isBody = mMethod == HttpMethod.POST;
        RequestBody requestBody =null;
        if (isBody) {
            requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), bytes);
        }
        Request.Builder builder = new Request.Builder().url(mUrl).method(mMethod.name(), requestBody);

        for (Map.Entry<String, String> entry : httpHeader.entrySet()) {
            builder.addHeader(entry.getKey(), entry.getValue());
        }
        Response response = mClient.newCall(builder.build()).execute();
        return new OkHttpResponse(response);

        // 当测试的时候会 getBody.write
    }

    @Override
    public HttpMethod getHttpMethod() {
        return mMethod;
    }

    @Override
    public Uri getUri() {
        return Uri.parse(mUrl);

    }

}

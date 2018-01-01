package originhttp;

import android.net.Uri;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Map;

import ctas.httpframework.http.http.BufferAbstractHttpRequest;
import ctas.httpframework.http.http.HttpHeader;
import ctas.httpframework.http.http.HttpMethod;
import ctas.httpframework.http.http.HttpReponse;

/**
 * Created by CTAS on 2017/12/29.
 */
public class OriginHttpRequest extends BufferAbstractHttpRequest {

    private HttpURLConnection mConnection;

    private String mUrl;

    private HttpMethod mMethod;

    public OriginHttpRequest(HttpURLConnection connection, HttpMethod method, String url) {
        this.mConnection = connection;
        this.mUrl = url;
        this.mMethod = method;
    }



    @Override
    public HttpMethod getHttpMethod() {
        return mMethod;
    }

    @Override
    public Uri getUri() {
        return Uri.parse(mUrl);
    }


    @Override
    protected HttpReponse byteInternalExecute(HttpHeader header, byte[] data) throws IOException {
        for (Map.Entry<String, String> entry : header.entrySet()) {
            mConnection.addRequestProperty(entry.getKey(), entry.getValue());
        }
        mConnection.setDoOutput(true);
        mConnection.setDoInput(true);
        mConnection.setRequestMethod(mMethod.name());
        mConnection.connect();
        if (data != null && data.length > 0) {
            OutputStream out = mConnection.getOutputStream();
            out.write(data,0,data.length);
            out.close();
        }
        OriginHttpResponse response = new OriginHttpResponse(mConnection);
        return response;
    }
}

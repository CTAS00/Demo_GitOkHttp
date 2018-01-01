package originhttp;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

import ctas.httpframework.http.http.AbstractHttpResponse;
import ctas.httpframework.http.http.HttpHeader;
import ctas.httpframework.http.http.HttpStatus;

/**
 * Created by CTAS on 2017/12/29.
 */
public class OriginHttpResponse extends AbstractHttpResponse {

    private HttpURLConnection mConnection;

    public OriginHttpResponse(HttpURLConnection connection) {
        this.mConnection = connection;
    }
    @Override
    protected InputStream getInternalInputstream() throws IOException {
        return mConnection.getInputStream();
    }


    @Override
    protected void closeInternal() {
        mConnection.disconnect();

    }


    @Override
    public HttpStatus getCode() {
        try {
            return HttpStatus.getValue(mConnection.getResponseCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getMessage() {
        try {
            return mConnection.getResponseMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public HttpHeader getHttpHeadder() {

        HttpHeader header = new HttpHeader();
        for (Map.Entry<String, List<String>> entry : mConnection.getHeaderFields().entrySet()) {
            header.set(entry.getKey(), entry.getValue().get(0));
        }
        return header;
    }
}

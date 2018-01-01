package okhttp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import basehttp.GzipAbstractHttpResponse;
import basehttp.HttpHeader;
import basehttp.HttpStatus;
import okhttp3.Response;

/**
 * Created by CTAS on 2018/1/1.
 */
public class OkHttpResponse extends GzipAbstractHttpResponse {

    private Response response;
    private InputStream inputStream;

    public OkHttpResponse(Response response) {
        this.response = response;
    }

    @Override
    protected void closeInternalOutputStream() throws IOException {
        if(inputStream!=null){
            inputStream.close();
        }
    }

    @Override
    protected InputStream getInternalInputStream() {
        inputStream = response.body().byteStream();
        return inputStream;
    }


    @Override
    public HttpStatus getStatus() {
        return HttpStatus.getValue(response.code());
    }

    @Override
    public String getMessage() {
        return response.message();
    }

    @Override
    public HttpHeader getHttpHeader() {
        HttpHeader httpHeader = new HttpHeader();
        for (String name : response.headers().names()) {
            httpHeader.set(name,response.headers().get(name));
        }
        return httpHeader;
    }
}

package ctas.httpframework.http.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/**
 * Created by CTAS on 2017/12/29.
 * 对于gzip的统一处理
 */
public abstract class AbstractHttpResponse implements HttpReponse {


    private static final String GZIP ="gzip" ;
    private GZIPInputStream gzipInputStream;

    private boolean isGzip(){
        String content_encording = getHttpHeadder().getContentEncoding();
        if(GZIP.equals(content_encording)){
            return true;
        }
        return false;
    }

    private InputStream getGzipInputStream(InputStream inputstream) throws IOException {
        return new GZIPInputStream(inputstream);

    }
    @Override
    public InputStream getBody() throws IOException {
        InputStream body = getInternalInputstream();
        if(isGzip()){
            gzipInputStream = (GZIPInputStream) getGzipInputStream(getInternalInputstream());
            return gzipInputStream;
        }
        return body;
    }
    // 拿到上一层的body
    protected abstract InputStream getInternalInputstream() throws IOException;
    protected abstract void closeInternal();

    @Override
    public void close() throws IOException {
        if(gzipInputStream!=null){
            gzipInputStream.close();
        }
        closeInternal();
    }
}

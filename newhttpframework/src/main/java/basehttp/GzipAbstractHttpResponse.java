package basehttp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/** 对于gzip的处理
 * Created by CTAS on 2018/1/1.
 */
public abstract class GzipAbstractHttpResponse implements HttpResponse {
    private static final String GZIP ="gzip";
    private InputStream inputStream=null;//gzip的outputstream

    @Override
    public InputStream getBody() throws IOException {
        InputStream inputStream = getInternalInputStream();
        if(isGzip()){
           return  this.inputStream = new GZIPInputStream(inputStream);
        }
        return inputStream;
    }

    @Override
    public void close() throws IOException {
        if(inputStream!=null){
            inputStream.close();
        }
        closeInternalOutputStream();

    }
    // 关闭输出流
    protected abstract void closeInternalOutputStream() throws IOException;
    // 获取到otputstream
    protected abstract InputStream getInternalInputStream();
    private boolean isGzip(){
        if(GZIP.equals(getHttpHeader().getContentEncoding())){
            return true;
        }
        return false;
    }
}

package basehttp;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by CTAS on 2018/1/1.
 */
public abstract class GzipAbstractHttpRequest implements  HttpRequest{

    private static final String GZIP ="gzip";
    private OutputStream outputStream;
    private HttpHeader httpheader = new HttpHeader();
    @Override
    public OutputStream getbody() throws IOException {
        // 对于数据采用缓存处理
        OutputStream outputStream = getInternalOutputStream();
        if(isGzip()){
            this.outputStream = new GZIPOutputStream(outputStream);
        }
        return outputStream;
    }
    private boolean isGzip(){
        if(GZIP.equals(getHttpHeader().getContentEncoding())){
            return true;
        }
        return false;
    }
    @Override
    public void close() throws IOException {
        if(outputStream!=null){
            outputStream.close();
        }
        internalClose();
    }

    @Override
    public void execute() {
        // 运行的时候 将header 和数据塞进去
        InternalExecute(httpheader);

    }
    // 运行时候的处理
    protected abstract void InternalExecute(HttpHeader httpheader);
    // 获取到outputstream 数据
    protected abstract OutputStream getInternalOutputStream();
    protected abstract void internalClose() throws IOException;
}

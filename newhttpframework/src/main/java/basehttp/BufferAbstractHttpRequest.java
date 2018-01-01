package basehttp;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by CTAS on 2018/1/1.
 */
public abstract  class BufferAbstractHttpRequest extends GzipAbstractHttpRequest {
    private ByteArrayOutputStream byteArrayOutputStream;

    @Override
    protected OutputStream getInternalOutputStream() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        return byteArrayOutputStream;
    }
    @Override
    protected void internalClose() throws IOException {
        if(byteArrayOutputStream!=null){
            byteArrayOutputStream.close();
        }
    }

    @Override
    protected void InternalExecute(HttpHeader httpheader) {
        bufferInternalExecute(httpheader,byteArrayOutputStream.toByteArray());
    }

    protected abstract void bufferInternalExecute(HttpHeader httpheader, byte[] data) throws IOException;
}

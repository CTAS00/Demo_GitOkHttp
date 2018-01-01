package ctas.httpframework.http.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by CTAS on 2017/12/29.
 */
public abstract  class BufferAbstractHttpRequest extends AbstractHttpRequest {
    
    
    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    protected OutputStream getBodyOutputstream(){
        return byteArrayOutputStream;
        
    }
    // 对于二进制数据的优化
    protected  HttpReponse internalExecute(HttpHeader httpHeader) throws IOException {
        
       return  byteInternalExecute(httpHeader,byteArrayOutputStream.toByteArray());
    }

    protected abstract HttpReponse byteInternalExecute(HttpHeader httpHeader, byte[] bytes) throws IOException;


}

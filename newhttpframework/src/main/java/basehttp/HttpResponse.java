package basehttp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by CTAS on 2018/1/1.
 */
public interface HttpResponse extends Header {
    InputStream getBody() throws IOException;
    HttpStatus getStatus();
    String getMessage();
    void close() throws IOException;
}

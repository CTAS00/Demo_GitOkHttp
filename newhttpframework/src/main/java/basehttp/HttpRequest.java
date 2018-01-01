package basehttp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 * Created by CTAS on 2018/1/1.
 */
public interface HttpRequest extends Header {
    OutputStream getbody() throws IOException;
    URI getUri();
    void close() throws IOException;
    void execute();
}

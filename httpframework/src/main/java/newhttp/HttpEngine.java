package newhttp;

import java.io.IOException;

import ctas.httpframework.http.http.HttpReponse;

/**
 * Created by CTAS on 2017/12/30.
 */
public interface HttpEngine {

    HttpReponse execute() throws IOException;
}

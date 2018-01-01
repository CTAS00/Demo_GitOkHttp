package service.convert;

import java.io.IOException;
import java.lang.reflect.Type;

import ctas.httpframework.http.http.HttpReponse;

/**
 * Created by CTAS on 2017/12/30.
 */
public interface Convert {


    boolean isCanParse(String contentType);

    Object parse(String content, Type type) throws IOException;

    Object parse(HttpReponse reponse, Type type) throws IOException;
}

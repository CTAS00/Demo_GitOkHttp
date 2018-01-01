package ctas.httpframework.http.http;

import java.util.Map;

/**
 * Created by CTAS on 2017/12/29.
 */
public interface NameValueMap<K,V> extends Map<K,V>{
    String get(String name);

    void set(String name, String value);

    void setAll(Map<String, String> map);
}

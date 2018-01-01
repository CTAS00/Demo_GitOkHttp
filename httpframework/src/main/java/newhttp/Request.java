package newhttp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import ctas.httpframework.http.http.HttpHeader;
import ctas.httpframework.http.http.HttpMethod;

/**上层业务的请求对象
 * Created by CTAS on 2017/12/30.
 */
public class Request {

    private static final String ENCODING = "utf-8";
    private String url;
    private HttpMethod method;
    private HttpHeader header;
    private byte[] data;

    public Request(Builder builder){
        url = builder.url;
        method = builder.method;
        header = builder.header;
        data = encodeParam(builder.mFormParams);

    }

    public static String getENCODING() {
        return ENCODING;
    }

    public String getUrl() {
        return url;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public HttpHeader getHeader() {
        return header;
    }

    public byte[] getData() {
        return data;
    }

    // 先转换成 string 然后转换成字节数组
    public  byte[] encodeParam(Map<String, String> value) {
        if (value == null || value.size() == 0) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        int count = 0;
        try {
            for (Map.Entry<String, String> entry : value.entrySet()) {

                buffer.append(URLEncoder.encode(entry.getKey(), ENCODING)).append("=").
                        append(URLEncoder.encode(entry.getValue(), ENCODING));
                if (count != value.size() - 1) {
                    buffer.append("&");
                }
                count++;

            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return buffer.toString().getBytes();
    }


    public static class Builder{
        private String url;
        private HttpMethod method;
        private HttpHeader header;

        private Map<String,String> mFormParams = new HashMap<>();
        private Map<String,String> mQueryParams = new HashMap<>();

        public Builder url(String url){
            this.url = url;
            return this;
        }
        public Builder httpMethod(HttpMethod method){
            this.method = method;
            return this;
        }
        public Builder HttpHeader(HttpHeader header){
            this.header = header;
            return this;
        }

        public Builder addFormParam(String key,String value){
            mFormParams.put(key,value);
            return this;
        }

        public Builder addQueryParam(String key,String value){
            mQueryParams.put(key,value);
            return this;
        }

        public Request builder(){
            return new Request(this);

        }
    }


}

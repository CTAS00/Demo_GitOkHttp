package service;

import basehttp.HttpMethod;

/**业务层的调用对象
 * Created by CTAS on 2018/1/1.
 */
public class MoocRequest {
    private String url;
    private HttpMethod httpMethod;
    private byte[] data;

    public MoocRequest(String url, HttpMethod httpMethod, byte[] data) {
        this.url = url;
        this.httpMethod = httpMethod;
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}

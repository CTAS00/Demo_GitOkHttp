package service;

import ctas.httpframework.http.http.HttpMethod;

/**给业务层所调用的
 * Created by CTAS on 2017/12/30.
 */
public class MoocRequest {
    private String url;
    private HttpMethod method;
    private byte[] data;
    private MoocResponse response;
    private String contentType;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public MoocResponse getResponse() {
        return response;
    }

    public void setResponse(MoocResponse response) {
        this.response = response;
    }
}

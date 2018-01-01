package service;

/**@deprecated  可以返回范形的数据
 * @author CTAS
 */
public interface MoocResponse<T> {


    void success(MoocRequest request,T data);
    void fail(int errorCode,String errorMsg);
}

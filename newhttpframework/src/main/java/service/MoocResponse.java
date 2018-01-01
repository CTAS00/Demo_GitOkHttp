package service;

/**
 * Created by CTAS on 2018/1/1.
 */
public interface MoocResponse<T> {
    void success(T data);
    void fail(int errorcode,String errormessage)
}

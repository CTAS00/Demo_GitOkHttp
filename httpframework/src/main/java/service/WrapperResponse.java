package service;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import service.convert.Convert;

/**
 * Created by CTAS on 2017/12/30.
 */
public class WrapperResponse implements MoocResponse<String> {

    private MoocResponse mMoocResponse;

    private List<Convert> mConvert;

    public WrapperResponse(MoocResponse moocResponse, List<Convert> converts) {
        this.mMoocResponse = moocResponse;
        this.mConvert = converts;
    }

    @Override
    public void success(MoocRequest request, String data) {

        for (Convert convert : mConvert) {
            if (convert.isCanParse(request.getContentType())) {

                try {
                    Object object = convert.parse(data, getType());
                    mMoocResponse.success(request, object);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }

        }


    }

    //获得旁边的T
    public Type getType() {
        Type type = mMoocResponse.getClass().getGenericSuperclass();
        Type[] paramType = ((ParameterizedType) type).getActualTypeArguments();
        return paramType[0];
    }

    @Override
    public void fail(int errorCode, String errorMsg) {

    }
}

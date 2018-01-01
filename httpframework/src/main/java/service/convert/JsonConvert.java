package service.convert;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by CTAS on 2017/12/30.
 */
public class JsonConvert implements Convert {
    // 用来解析json数组的
    private Gson gson = new Gson();

    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";



    @Override
    public boolean isCanParse(String contentType) {
        return CONTENT_TYPE.equals(contentType);
    }

    @Override
    public Object parse(String content, Type type) throws IOException {
        return gson.fromJson(content, type);
    }
}

package newhttp;

import okhttp3.*;

/**
 * Created by CTAS on 2017/12/30.
 */
public interface Callback<T> {

    void onSuccess(Response<T> response);

    void onFailure(String errormessage);
}

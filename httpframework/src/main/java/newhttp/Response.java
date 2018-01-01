package newhttp;

import ctas.httpframework.http.http.HttpReponse;

/**上层业务的response
 * Created by CTAS on 2017/12/30.
 */
public class Response<T> {
    private T body;
    private HttpReponse httpReponse;
    public Response(T body, HttpReponse httpReponse) {
        this.body = body;
        this.httpReponse = httpReponse;
    }
    public boolean isSuccess(){
        return httpReponse.getCode().isSuccess();
    }
}

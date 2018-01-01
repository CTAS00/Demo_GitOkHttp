package newhttp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import annotation.Field;
import annotation.Header;
import annotation.Post;
import ctas.httpframework.http.http.HttpMethod;
import service.convert.Convert;
import service.convert.JsonConvert;

/**
 * Created by CTAS on 2017/12/30.
 */
public class Text {

    interface Api{
        @Header()
        @Post{"login"}
        void fetch(@Field("phone") String phone,)


    }



    public static void main(String args[]){

        Request request= new Request.Builder()
                .url("")
                .addFormParam("phone","123")
                .addFormParam("id","123")
                .httpMethod(HttpMethod.POST)
                .builder();

        NiceClient client = new NiceClient.Builder()
                .url()
                .executor(Executors.newCachedThreadPool());
                .build();
        Api api = client.create(Api.class);
        api.fetch()


        List<Convert> converts = new ArrayList<>();
        converts.add(new JsonConvert());
        HttpCall httpCall = new HttpCall(request, Executors.newCachedThreadPool(),converts,new )
        try {
            httpCall.invoke().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

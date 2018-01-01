package newhttp;

import java.util.List;
import java.util.concurrent.ExecutorService;

import service.HttpRequestProvider;
import service.convert.Convert;

/**
 * Created by CTAS on 2017/12/30.
 */
public class NiceClient {


    public NiceClient(Builder builder){

    }

    public static class Builder{
        private String url;
        private HttpRequestProvider httpRequestProvider;
        private ExecutorService executorService;
        private List<Convert> convertList;



    }
}

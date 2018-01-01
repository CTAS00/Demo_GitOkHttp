package service;

import android.net.Uri;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import ctas.httpframework.http.http.HttpMethod;
import ctas.httpframework.http.http.HttpRequest;

/**暴露给最上层的业务信息 主要是线程管理和队列管理
 * Created by CTAS on 2017/12/30.
 */
public class WorkStation {
    private static final int MAX_REQUEST_SIZE = 60;

    private static final ThreadPoolExecutor sThreadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory() {

        private AtomicInteger index = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("http thread name is " + index.getAndIncrement());
            return thread;
        }
    });

    private  HttpRequestProvider httpRequestFactory;

    public WorkStation(){
        httpRequestFactory = new HttpRequestProvider();
    }


    private Deque<MoocRequest> mRunning = new ArrayDeque<>();

    private Deque<MoocRequest> mCache = new ArrayDeque<>();
    private void doHttpRequest(MoocRequest request){
        HttpRequest httpRequest =null;
        try {
            httpRequest =httpRequestFactory.getHttpRequest(Uri.parse(request.getUrl()),request.getMethod());
            sThreadPool.execute(new HttpRunnable(httpRequest,request,this));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add(MoocRequest request){
        if(mRunning.size()>MAX_REQUEST_SIZE){
            mCache.add(request);
        }else{
            // 网络请求进行处理
            mRunning.add(request);
            doHttpRequest(request);
        }
    }

    public void finish(MoocRequest request){

        mRunning.remove(request);
        if(mRunning.size()>MAX_REQUEST_SIZE){
            return;
        }
        if(mCache.size() == 0){
            return;
        }
        while(mCache.iterator().hasNext()){
            MoocRequest request1  = mCache.iterator().next();
            mRunning.add(request1);
            mCache.remove(request1);
            doHttpRequest(request1);
        }

    }

}

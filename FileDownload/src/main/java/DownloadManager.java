import android.content.Context;

import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import http.DownloadCallback;
import http.HttpManager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by CTAS on 2017/12/28.
 */
public class DownloadManager {

    private static final DownloadManager sManager=new DownloadManager();
    private Context mContext;
    public void init(Context context){
        mContext = context;
    }
    public static DownloadManager getInstance(){
        return sManager;
    }
    private DownloadManager(){
    }
    public final static int MAX_THREAD = 2;
    public final static int LOCAL_PROGRESS_SIZE = 1;


    // 添加队列的机制 防止重复点击
    private HashSet<DownloadTask> mHashSet = new HashSet<>();

    private static ThreadPoolExecutor sThreadPool = new ThreadPoolExecutor(MAX_THREAD, MAX_THREAD, 60, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(), new ThreadFactory() {

        private AtomicInteger mInteger = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "download thread #" + mInteger.getAndIncrement());
            return thread;
        }
    });


   private void finishTask(DownloadTask task){
       mHashSet.remove(task);
   }
    public void download(final String url, final DownloadCallback callback){
        final DownloadTask task = new DownloadTask(url,callback);
        if(mHashSet.contains(task)){
            callback.fail(HttpManager.NETWORK_RUNNING_TASK,"任务已经在运行中");
            return;
        }
        mHashSet.add(task);
        HttpManager.getInstance().asyncRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                finishTask(task);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(!response.isSuccessful()&& callback!=null){
                    callback.fail(HttpManager.NETWORK_CODE,"网络请求错误");
                    return;
                }

                long content_length = response.body().contentLength();
                if(content_length == -1){
                    callback.fail(HttpManager.NETWORK_CONTENT_LENGTH,"content-length -1");
                    return;
                }

                processDownload(url,content_length,callback);
                // 伪代码的实现
                finishTask(task);

            }
        });


    }

    private void processDownload(String url, long length, DownloadCallback callback) {
    long threadSize = length/MAX_THREAD;
        for(int i=0;i<MAX_THREAD;i++){
            long startSize = i*threadSize;
            long endSize = (i+1)*threadSize-1;
            sThreadPool.execute(new DownloadRunnable(startSize,endSize,url,callback));
        }
        }
}

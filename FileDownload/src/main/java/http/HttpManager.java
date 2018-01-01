package http;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import ctas.filedownload.utils.File.FileStorageManager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by CTAS on 2017/12/28.
 */
public class HttpManager {
    private static final HttpManager sManager=new HttpManager();
    private Context mContext;
    public void init(Context context){
        mContext = context;
    }
    public static HttpManager getInstance(){
        return sManager;
    }
    private HttpManager(){
        mClient = new OkHttpClient();
    }
    private OkHttpClient mClient ;

    public static final int NETWORK_CODE = 1;
    public static final int NETWORK_CONTENT_LENGTH = 2;
    public static final int NETWORK_RUNNING_TASK = 3;


    public Response syncRequest(String url){
        Request request = new Request.Builder().url(url).build();
        try {
            return mClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response syncRequestByRange(String url,long start,long end){
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Range", "bytes=" + start + "-" + end)
                .build();
        try {
            return mClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void asyncRequest(String url,Callback callback){
        Request request = new Request.Builder().url(url).build();
        mClient.newCall(request).enqueue(callback);
    }



    public void asyncRequest(final String url, final DownloadCallback callback){
        Request request = new Request.Builder().url(url).build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(!response.isSuccessful()&&callback!=null){
                    callback.fail(NETWORK_CODE,"请求失败");
                }
                File file = FileStorageManager.getInstance().getFileName(url);
                byte[] buffer = new byte[1024*500];
                int len;
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                InputStream inputStream = response.body().byteStream();
                while((len = inputStream.read(buffer))!=-1){
                    fileOutputStream.write(buffer,0,len);
                    fileOutputStream.flush();
                }
                fileOutputStream.close();
                inputStream.close();
                if(callback!=null){
                    callback.success(file);
                }

            }
        });
    }

}



import android.os.Process;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import ctas.filedownload.utils.File.FileStorageManager;
import http.DownloadCallback;
import http.HttpManager;
import okhttp3.Response;

/**
 * @author nate
 */
public class DownloadRunnable implements Runnable {

    private long mStart;

    private long mEnd;

    private String mUrl;

    private DownloadCallback mCallback;

    public DownloadRunnable(long mStart, long mEnd, String mUrl, DownloadCallback mCallback) {
        this.mStart = mStart;
        this.mEnd = mEnd;
        this.mUrl = mUrl;
        this.mCallback = mCallback;
    }

    @Override
    public void run() {
        // n进行网络请求
       Response response =  HttpManager.getInstance().syncRequestByRange(mUrl, mStart, mEnd);
        if(response==null &&mCallback!=null ){
            mCallback.fail(HttpManager.NETWORK_CODE,"网络请求出错");
        }
        File file = FileStorageManager.getInstance().getFileName(mUrl);
        try {
            RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rwd");
            randomAccessFile.seek(mStart);// 定位到这一步
            InputStream inputStream = response.body().byteStream();
            byte[] buffer = new byte[1024*500];
            int len=0;
            while((len = inputStream.read(buffer))!=-1){
                randomAccessFile.write(buffer,0,len);
            }
            mCallback.success(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

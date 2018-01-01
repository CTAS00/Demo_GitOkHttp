package ctas.filedownload.utils.File;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

import ctas.filedownload.utils.Md5Utils;

/**
 * Created by CTAS on 2017/12/28.
 * 文件管理类
 */
public class FileStorageManager {

    private static final FileStorageManager sManager=new FileStorageManager();

    private Context mContext;

    public void init(Context context){
        mContext = context;
    }
    public static FileStorageManager getInstance(){
        return sManager;
    }
    private FileStorageManager(){

    }

    public File getFileName(String url){
        File parent;
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            parent = mContext.getExternalCacheDir();
        }else {
            parent = mContext.getCacheDir();
        }

        String fileName = Md5Utils.generateCode(url);
        File file = new File(parent,fileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }



}

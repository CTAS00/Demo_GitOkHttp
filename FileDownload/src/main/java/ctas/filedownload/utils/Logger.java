package ctas.filedownload.utils;

import android.util.Log;

/**
 * Created by CTAS on 2017/12/28.
 */
public class Logger {
    public static final boolean DEBUG =true;


    public static void error(String tag,String message){
        if(DEBUG){
            Log.e(tag,message);
        }
    }

}

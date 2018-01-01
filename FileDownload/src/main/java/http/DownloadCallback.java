package http;

import java.io.File;

/**
 * Created by CTAS on 2017/12/28.
 */
public interface DownloadCallback {
    void success(File file);
    void fail(int errorcode,String errorMessage);
    void Progress(int progress);
}

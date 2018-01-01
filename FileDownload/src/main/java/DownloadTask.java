import http.DownloadCallback;

/**
 * Created by CTAS on 2017/12/29.
 */
public class DownloadTask {
    private String url;
    private DownloadCallback callback;

    public DownloadTask(String url, DownloadCallback callback) {
        this.url = url;
        this.callback = callback;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DownloadTask that = (DownloadTask) o;

        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        return !(callback != null ? !callback.equals(that.callback) : that.callback != null);

    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (callback != null ? callback.hashCode() : 0);
        return result;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DownloadCallback getCallback() {
        return callback;
    }

    public void setCallback(DownloadCallback callback) {
        this.callback = callback;
    }
}

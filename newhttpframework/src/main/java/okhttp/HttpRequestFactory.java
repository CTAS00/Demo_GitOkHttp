package okhttp;

import android.net.Uri;

import java.net.URI;

import basehttp.HttpMethod;
import basehttp.HttpRequest;

/**
 * Created by CTAS on 2018/1/1.
 */
public interface HttpRequestFactory {
    HttpRequest createRequest(URI uri, HttpMethod method);
}

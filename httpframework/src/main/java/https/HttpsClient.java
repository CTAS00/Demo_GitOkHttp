package https;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

/**
 * Created by CTAS on 2017/12/30.
 */
public class HttpsClient {
  static class HostVerify implements HostnameVerifier{
      @Override
      public boolean verify(String hostname, SSLSession session) {
          return true;
      }
  }
    // 校验证书
    static class Trust implements X509TrustManager{


        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }


}

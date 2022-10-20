package com.live.wallpaper.net;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/**
 * ----- Date: 2019/4/19
 * ----- Time: 14:40
 * ----- author: YangC
 * ----- Content:
 */
public class SSLSocketClient {

    //获取这个SSLSocketFactory
    public static SSLSocketFactory getSSLSocketFactory() {
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new X509TrustManager[]{getTrustManager()}, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取TrustManager
    public static X509TrustManager getTrustManager() {
        X509TrustManager trustManager = new X509TrustManager() {
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
        };
        return trustManager;
    }


    //获取HostnameVerifier
    public static HostnameVerifier getHostnameVerifier() {

        HostnameVerifier hostnameVerifier = (hostname, session) -> hostname.equals(session.getPeerHost());

        //        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
        //            @Override
        //            public boolean verify(String s, SSLSession session) {
        //
        //                //3
        //                if (s.equals(Constants.HTTP_URL_CIP)) {
        //                    return true;
        //                } else {
        //                    HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
        //                    return hv.verify(Constants.HTTP_URL_CIP, session);
        //                }
        //
        //                //1
        ////                return true;
        //
        //                //2
        ////                if (s.equals(Constants.HTTP_URL_CIP)) {
        ////                    return true;
        ////                } else {
        ////                    return false;
        ////                }
        //
        //            }
        //        };
        return hostnameVerifier;
    }
}
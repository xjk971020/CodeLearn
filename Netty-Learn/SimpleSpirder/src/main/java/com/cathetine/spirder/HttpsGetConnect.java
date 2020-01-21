package com.cathetine.spirder;

import com.cathetine.spirder.UserAgentPool;
import com.cathetine.spirder.proxy.IPBean;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * @Author:xjk
 * @Date 2019/12/4 20:19
 * https get请求
 */
public class HttpsGetConnect {

    public static String getResponseContent(String url) throws Exception {
        return getResponseContent(url,null,null);
    }
    /**
     * @param url
     * @param headers 请求头部
     * @param ipBean
     * @return
     * @throws Exception
     */
    public static String getResponseContent(String url, Map<String, String> headers, IPBean ipBean) throws Exception {
        HttpsURLConnection connection = null;

        // 设置代理
        if (ipBean != null) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ipBean.getIp(), Integer.valueOf(ipBean.getPort())));

            connection = (HttpsURLConnection) new URL(url).openConnection(proxy);

            if ("HTTPS".equalsIgnoreCase(ipBean.getType())) {
                SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
                connection.setSSLSocketFactory(sslContext.getSocketFactory());
                connection.setHostnameVerifier(new TrustAnyHostnameVerifier());
            }
        }

        if (connection == null) {
            connection = (HttpsURLConnection) new URL(url).openConnection();
        }

        // 添加请求头部
        connection.setRequestProperty("User-Agent", UserAgentPool.getRandomUserAgent());
        if (headers != null) {
            headers.forEach(connection::setRequestProperty);
        }

        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();
        inputStream.close();
        return stringBuilder.toString();
    }


    private static class TrustAnyTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
}

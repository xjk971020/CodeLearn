package com.cathetine.spirder;

import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 * @Author:xjk
 * @Date 2019/12/3 11:18
 * 常用get请求发送类
 */
public class HttpGetConnect {
    public static String connect(String url) {
        return connect(url,Constant.CHARSET,null,null,null);
    }
        /**
         * 获取html内容
         *
         * @param url
         * @param charsetName UTF-8、GB2312
         * @param referer
         * @param cookies
         * @param headers
         * @return
         * @throws IOException
         */
    public static String connect(String url, String charsetName, String referer, List<Cookie> cookies, Map<String, String> headers) {
        BasicHttpClientConnectionManager connManager = new BasicHttpClientConnectionManager();
        CookieStore cookieStore = new BasicCookieStore();
        String content = "";
        try (CloseableHttpClient httpclient = HttpClients.custom()
                .setConnectionManager(connManager)
                .setDefaultCookieStore(cookieStore)
                .build()) {
            HttpGet httpget = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(5000)
                    .setConnectTimeout(50000)
                    .setConnectionRequestTimeout(50000)
                    .build();
            httpget.setConfig(requestConfig);
            if (cookies != null && cookies.size() != 0) {
                cookies.forEach(cookieStore::addCookie);
            }
            if (headers != null) {
                headers.forEach(httpget::setHeader);
            }
            if (referer != null) {
                httpget.setHeader("Referer", referer);
            }
            httpget.setHeader("User-Agent", UserAgentPool.getRandomUserAgent());
            CloseableHttpResponse response = httpclient.execute(httpget);
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                InputStream instream = entity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(instream, charsetName));
                StringBuilder sbf = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sbf.append(line).append("\n");
                }
                br.close();
                content = sbf.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
}

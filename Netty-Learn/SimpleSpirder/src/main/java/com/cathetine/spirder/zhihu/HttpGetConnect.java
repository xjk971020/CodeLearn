package com.cathetine.spirder.zhihu;

import com.cathetine.spirder.UserAgentPool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author:cathetine
 * @Date:19-10-12
 * @Time:上午9:27
 * 牛客网get请求发送类
 */
public class HttpGetConnect {
    /**
     * 获取html内容
     * @param url
     * @param charsetName  UTF-8、GB2312
     * @param referer
     * @param cookie
     * @return
     * @throws IOException
     */
    public static String connect(String url,String charsetName,String referer, String cookie) throws IOException{
        BasicHttpClientConnectionManager connManager = new BasicHttpClientConnectionManager();
        CloseableHttpClient httpclient = HttpClients.custom()
                .setConnectionManager(connManager)
                .build();
//        httpclient.getParams().setParameter(HTTP_CONTENT_CHARSET, "UTF-8");
        String content = "";

        try{
            HttpGet httpget = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(5000)
                    .setConnectTimeout(50000)
                    .setConnectionRequestTimeout(50000)
                    .build();
            httpget.setConfig(requestConfig);
            httpget.setHeader("Accept", "*/*");
            httpget.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
            httpget.setHeader("Connection", "keep-alive");
            httpget.setHeader("User-Agent", UserAgentPool.getRandomUserAgent());
            httpget.setHeader("cache-control", "max-age=0");
            if (referer != null) {
                httpget.setHeader("Referer",referer);
            }
            //设置cookie
            httpget.setHeader("Cookie", cookie);

            CloseableHttpResponse response = httpclient.execute(httpget);

            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                InputStream instream = entity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(instream,charsetName));
                StringBuffer sbf = new StringBuffer();
                String line;
                while ((line = br.readLine()) != null){
                    sbf.append(line + "\n");
                }
                br.close();
                content = sbf.toString();
            } else {
                content = "";
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            httpclient.close();
        }
//        log.info("content is " + content);
        return content;
    }

    private static Log log = LogFactory.getLog(HttpGetConnect.class);
}

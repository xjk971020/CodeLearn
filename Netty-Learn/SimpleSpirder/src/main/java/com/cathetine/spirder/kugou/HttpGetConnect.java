package com.cathetine.spirder.kugou;

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
 * httpclient 工具类
 */
public class HttpGetConnect {

    /**
     * 获取html内容
     * @param url
     * @param charsetName  UTF-8、GB2312
     * @return
     * @throws IOException
     */
    public static String connect(String url,String charsetName) throws IOException{
        BasicHttpClientConnectionManager connManager = new BasicHttpClientConnectionManager();

        CloseableHttpClient httpclient = HttpClients.custom()
                .setConnectionManager(connManager)
                .build();
        String content = "";

        try{
            HttpGet httpget = new HttpGet(url);

            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(5000)
                    .setConnectTimeout(50000)
                    .setConnectionRequestTimeout(50000)
                    .build();
            httpget.setConfig(requestConfig);
            httpget.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
            httpget.setHeader("Accept-Encoding", "gzip,deflate,sdch");
            httpget.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
            httpget.setHeader("Connection", "keep-alive");
            httpget.setHeader("Upgrade-Insecure-Requests", "1");
            httpget.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36");
            httpget.setHeader("cache-control", "max-age=0");

            httpget.setHeader("Referer","https://music.163.com/");

            //设置cookie
            httpget.setHeader("Cookie", "mail_psc_fingerprint=b41da994be293f8647defc171be9ce3e; _iuqxldmzr_=32; _ntes_nnid=614e5b4b6841ae0e1ef5d8affae62e94,1566116679873; _ntes_nuid=614e5b4b6841ae0e1ef5d8affae62e94; WM_TID=eOW44GX5fXtAFFAABQYppdhO92FWSRxY; usertrack=CrH/BF1ftwSu8UaPAwRHAg==; __f_=1566553862158; mp_MA-9ADA-91BF1A6C9E06_hubble=%7B%22sessionReferrer%22%3A%20%22https%3A%2F%2Fcampus.163.com%2Fapp%2FjobDetail%2Findex%3Fid%3D424%22%2C%22updatedTime%22%3A%201567340901689%2C%22sessionStartTime%22%3A%201567340807481%2C%22sendNumClass%22%3A%20%7B%22allNum%22%3A%205%2C%22errSendNum%22%3A%200%7D%2C%22deviceUdid%22%3A%20%22657b203e-9c9d-4c59-8788-7938da16fb63%22%2C%22persistedTime%22%3A%201567340807474%2C%22LASTEVENT%22%3A%20%7B%22eventId%22%3A%20%22da_screen%22%2C%22time%22%3A%201567340901689%7D%2C%22sessionUuid%22%3A%20%228cce900b-508d-4111-9d19-772831f39aea%22%7D; nts_mail_user=15975769482@163.com:-1:1; P_INFO=m15975769482_1@163.com|1570676092|0|mail163|00&99|gud&1570633097&mail163#gud&440700#10#0#0|159482&1|mail163|15975769482@163.com; WM_NI=LvtprYKM%2BGt0HB%2BHVSM6HWUbA%2BxwZE8OXhm3Apge4shIYFO7kUbW6%2FG4z6VCF00C59Z6ZMEjktzXvEk4k6iLUowO3k8S2cK0o0U6NLOiJZbhWK64Zk7TaXR1Rix6wee0R1U%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6eeb9e247b696fd82bb62a68a8fa3d44a868e9ebbb740f7e7fdaee46fa19c889bca2af0fea7c3b92a90aabc91f1538993e5a8f73c8c9ee5a9d5598fea88b8d86a83f5b898d94ffc919889f03983b0a986b779ac9797a7ce3da98baeb8b52585ecb6d7d54986f000a4b8339aea96aec862a9acb6b9f54fa9eca5d7ec4897b1b9b0b261fc8dfe84ca3cadf09bd7cd488db786bbe1649595fad5d961f58cafb9d13b92b7b8d7f66fa7f09ed2d837e2a3; playerid=76539969; JSESSIONID-WYYY=2SIWuTMMUgDU%2Fsvmg1PNsK8R%2FCacconriF8N7AOJ%5COX6kz6SBt8ubqGxopEg%2BGOl2s2ZzmC9%2BplumVq%5CSlN4gRg5R%5CD3Jis%2BoU40jw5gMkT%5Cl2JH3IRUdbapbVKO80J5i1wBRUoaApY%2BMwn5t8yN5AUY4ecNjX%2Ft2S4jCs6nwFg6yfOE%3A1570718599694");


            CloseableHttpResponse response = httpclient.execute(httpget);

            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {

                HttpEntity entity = response.getEntity();
                InputStream instream = entity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(instream,charsetName));
                StringBuffer sbf = new StringBuffer();
                String line = null;
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

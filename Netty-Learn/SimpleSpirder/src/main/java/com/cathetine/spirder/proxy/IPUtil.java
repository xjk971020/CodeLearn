package com.cathetine.spirder.proxy;

import com.cathetine.spirder.HttpsGetConnect;
import com.sun.deploy.net.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @Author:xjk
 * @Date 2019/12/3 11:14
 * ip类工具，校验ip是否有效
 */
public class IPUtil {
    /**
     * 检测ip是否有效，cmd ping 命令测试
     * ping得同不一定能访问成功
     *
     * @param ip
     * @return
     */
    public static boolean isValid_methodOne(String ip) {
        Runtime run = Runtime.getRuntime();
        String cmd = "cmd /k  ping " + ip;
        try {
            Process p = run.exec(cmd);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), Charset.forName("GBK")));
            String lineMes;
            int lineCount = 0;
            while ((lineMes = br.readLine()) != null) {
                lineCount++;
                if (lineCount > 0) {
                    String result = "来自";
                    if (lineMes.startsWith(result)) {
                        br.close();
                        return true;
                    }
                }
                if (lineCount >= 3) {
                    br.close();
                    return false;
                }
            }
            //检查命令是否执行失败。
            if (p.waitFor() != 0) {
                //0表示正常结束，1：非正常结束
                if (p.exitValue() == 1) {
                    System.err.println("命令执行失败!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 可靠方法:尝试访问某url能否返回数据
     *
     * @param ipBean
     * @return
     */
    public static boolean isValid_methodTwo(IPBean ipBean) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ipBean.getIp(), Integer.parseInt(ipBean.getPort())));
        int code = 0;
        if (ipBean.getType() != null && "http".equalsIgnoreCase(ipBean.getType())) {
            URLConnection connection = new URL("https://www.baidu.com/").openConnection(proxy);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            try {
                code = ((HttpURLConnection) connection).getResponseCode();
            } catch (IOException e) {
//                e.printStackTrace();
                return false;
            }
        } else {
            HttpsURLConnection connection = (HttpsURLConnection) new URL("https://www.baidu.com/").openConnection(proxy);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
            connection.setSSLSocketFactory(sslContext.getSocketFactory());
            connection.setHostnameVerifier(new TrustAnyHostnameVerifier());
            try {
                code = connection.getResponseCode();
            } catch (IOException e) {
//                e.printStackTrace();
                return false;
            }
        }
        return code == 200;
    }

    private static final String MY_IP_API = "https://www.ipip.net/ip.html";

    // 获取当前ip地址，判断是否代理成功
    public static String getMyIp() {
        try {
            String html = HttpsGetConnect.getResponseContent(MY_IP_API);
            Document doc = Jsoup.parse(html);
            Element element = doc.selectFirst("div.tableNormal");
            return element.selectFirst("a").text();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

package com.cathetine.spirder.proxy;

import com.cathetine.spirder.HttpGetConnect;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author:xjk
 * @Date 2019/12/3 11:10
 * 爬取西刺网ip 有http类型和https类型
 */
public class IPCrawler {
    private static String HTTP_URL = "https://www.xicidaili.com/wt/page";
    private static String HTTPS_URL = "https://www.xicidaili.com/wn/page";
    private static String PAGE_REPLACE = "page";
    private static int PAGE_COUNT = 20;

    public static List<IPBean> getHttpsIp() throws InterruptedException {
        return getIp(HTTPS_URL);
    }

    public static List<IPBean> getHttpIp() throws InterruptedException {
        return getIp(HTTP_URL);
    }

    public static List<IPBean> getIp(String url) throws InterruptedException {
        int pageIndex = 1;
        List<IPBean> result = new ArrayList<>();
        for (; pageIndex <= PAGE_COUNT; pageIndex++) {
            String targetUrl = url.replace(PAGE_REPLACE, String.valueOf(pageIndex));
            String content = HttpGetConnect.connect(targetUrl);
            Document html = Jsoup.parse(content);

            Element table = html.getElementById("ip_list");
            Elements ipInfo = table.getElementsByTag("tr");
            int ipCount = ipInfo.size();
            int ipCurIndex = 1;
            for (; ipCurIndex < ipCount; ipCurIndex++) {
                Element ipDetail = ipInfo.get(ipCurIndex);
                Elements details = ipDetail.getElementsByTag("td");
                String ip = details.get(1).text();
                String port = details.get(2).text();
                String address = details.get(3).text();
                String isHide = details.get(4).text();
                String type = details.get(5).text();
                String existTime = details.get(8).text();
                if (existTime.contains("天")) {
                    IPBean ipBean = new IPBean(ip, port, address, isHide, type, existTime);
                    result.add(ipBean);
                }
            }
            TimeUnit.SECONDS.sleep(2);
        }
        return result;
    }

}

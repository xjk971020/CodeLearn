package com.cathetine.spirder.nowcoder;

import com.cathetine.spirder.Constant;
import com.cathetine.spirder.zhihu.HttpGetConnect;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author:cathetine
 * @Date:19-10-13
 * @Time:下午8:37 牛客网阿里巴巴java面经爬虫
 */
public class MianJingCrawler {
    /**
     * _TAG_ : 标签名, [秋招][校招][面经]....
     * _TYPE_ : 类型, [站内公告][笔经面经][我要提问]....
     * _EXPTAG_ : 职位 [java工程师][算法工程师][前端工程师]....
     * _ORDER_ : [最新回复][最新发表][最热][精华]
     * _PAGE_ : 页数
     */
    private static String url = "http://www.nowcoder.com/discuss/tag/_TAG_?type=_TYPE_&order=_ORDER_&pageSize=30&expTag=_EXPTAG_&query=&page=_PAGE_";
    private static String cookie = "NOWCODERUID=14448AFE08E127EAAAE1ECC1110B6736; NOWCODERCLINETID=A9B93C557B61C2D28DB7A4E51B29055A; gr_user_id=f515eafa-8e01-4d0c-961f-9b93c6c6afa5; grwng_uid=df443e7b-e5bc-4b60-8c42-244edfb6a4f5; Hm_lvt_a808a1326b6c06c437de769d1b85b870=1570886864,1570969549,1570974065,1571056431; SERVERID=11b18158070cf9d7800d51a2f8a74633|1571058493|1571056429; Hm_lpvt_a808a1326b6c06c437de769d1b85b870=1571058494";
    private static String nowcoderUrl = "http://www.nowcoder.com";
    private static String path = Constant.FILE_PATH + "/nowcoder/";

    static {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 面经爬虫
     *
     * @param result
     * @param tagId
     * @param start
     * @param end
     * @param typeId
     * @param orderId
     * @param expTagId
     * @return
     */
    public static List<String> getMianJing(List<String> result, String tagId, int start, int end, String typeId, String orderId, String expTagId) {
        String targetUrl = url.replace("_TAG_", tagId).replace("_ORDER_", orderId).replace("_TYPE_", typeId).replace("_EXPTAG_", expTagId);
        List<String> mianjingDetailUrls = new ArrayList<>();
        try {
            int page = start;
            for (; page <= end; ) {
                //替换页码
                targetUrl = targetUrl.replace("_PAGE_", String.valueOf(page));
                System.out.println(targetUrl);
                if (page == end) {
                    System.out.println(Thread.currentThread().getName() + " : " + targetUrl);
                }
                String content = HttpGetConnect.connect(targetUrl, Constant.CHARSET, targetUrl, cookie);
//                System.out.println(content);
                Document document = Jsoup.parse(content);
                Elements commonList = document.getElementsByClass("common-list");
                Element ul = null;
                try {
                    ul = commonList.get(0);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(targetUrl);
                    System.out.println(content);
                    return result;
                }
                Elements titles = ul.getElementsByClass("clearfix");
                for (int i = 0; i < titles.size(); i = i + 2) {
                    Element titleDetail = titles.get(i).getElementsByClass("discuss-main clearfix").get(0);
                    String title = titleDetail.getElementsByTag("a").get(0).text().replace("精", "").replace("烫", "");
                    String mianjingDetailUrl = titleDetail.getElementsByTag("a").get(0).attr("href");
                    mianjingDetailUrl = nowcoderUrl + mianjingDetailUrl;
//                    System.out.println(mianjingDetailUrl);
                    mianjingDetailUrls.add(mianjingDetailUrl);
                }
                //将页面数值替换成 _PAGE_, 并让页码的数值加１
                String temp = "page=" + String.valueOf(page);
                targetUrl = targetUrl.replace(temp, "page=_PAGE_");
                page++;
                try {
//                    TimeUnit.SECONDS.sleep((int) (Math.random() * 10));
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            getMianJingDetail(result, mianjingDetailUrls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取具体的面经内容
     *
     * @param result
     * @param urls
     */
    private static void getMianJingDetail(List<String> result, List<String> urls) throws IOException {
        urls.forEach(url -> {
            String content = null;
            try {
                content = HttpGetConnect.connect(url, Constant.CHARSET, url, cookie);
                Document document = Jsoup.parse(content);
                String mianjingDeatil = document.getElementsByClass("post-topic-main").get(0).text();
                System.out.println(mianjingDeatil);
                result.add(mianjingDeatil);
                TimeUnit.SECONDS.sleep(10);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        List<String> result = new ArrayList<>();
        getMianJing(result, "134", 1, 5, "2", "1", "1");
    }
}

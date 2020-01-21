package com.cathetine.spirder.movie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cathetine.spirder.Constant;
import com.cathetine.spirder.zhihu.HttpGetConnect;
import com.cathetine.spirder.WordCloudGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author:cathetine
 * @Date:19-10-14
 * @Time:下午9:53
 * 爬取豆瓣电影
 */
public class DouBanMovie {
    private static String url = "http://movie.douban.com/j/search_subjects?type=movie&tag=_TAG_&sort=recommend&page_limit=20&page_start=_START_";
    private static String cookie = "ll=\"118281\"; bid=4ekLTFXOujY; __utmz=30149280.1566394879.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; gr_user_id=16cb5b73-9674-42b4-b56a-cb1186367e6c; _vwo_uuid_v2=D0A1DEC29E915406C85EABC793BFA53EE|57a7ace5ef6fc88fcafeefa52b69f5af; __utma=30149280.970982586.1566394879.1566394879.1571060580.2; __utmb=30149280.0.10.1571060580; __utmc=30149280; __utma=223695111.321822545.1571060580.1571060580.1571060580.1; __utmb=223695111.0.10.1571060580; __utmc=223695111; __utmz=223695111.1571060580.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); _pk_ses.100001.4cf6=*; ap_v=0,6.0; __yadk_uid=7Rr7r1AkNwtyDikBqSa7znWlFwBqV9k1; trc_cookie_storage=taboola%2520global%253Auser-id%3Dcca15416-45a8-46b0-a6e7-22c7ba87f6f4-tuct49c2b55; _pk_id.100001.4cf6=d55014eb1eac0246.1571060580.1.1571061735.1571060580.";
    private static String referer = "https://movie.douban.com/explore";
    private static String path = Constant.FILE_PATH + "/nowcoder/";

    public static List<String> getMovieDetail(String tag, int start, int end) {
        String targetUrl = url.replace("_TAG_", tag);
        List<String> result = new ArrayList<>();
        while (start <= end) {
            try {
                String movieUrl = targetUrl.replace("_START_", String.valueOf(start));
                System.out.println(movieUrl);
                String content = HttpGetConnect.connect(movieUrl, Constant.CHARSET, referer, cookie);
                JSONObject subject = JSON.parseObject(content);
                JSONArray movies = subject.getJSONArray("subjects");
                for (Object movieObj : movies) {
                    JSONObject movie = JSON.parseObject(movieObj.toString());
                    String rate = movie.getString("rate");
                    System.out.println(rate);
                    result.add(rate);
                }
                start = start + 20;
                TimeUnit.SECONDS.sleep((int) (Math.random() * 10));
//                    TimeUnit.SECONDS.sleep(4);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        List<String> result = getMovieDetail("热门", 0, 400);
        WordCloudGenerator.writeTitle(result, path, "豆瓣点评评分.txt");
        WordCloudGenerator.getWordCloud(path, "豆瓣点评评分.txt","豆瓣点评评分词图.png");
    }
}

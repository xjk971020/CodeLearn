package com.cathetine.spirder.wangyiyun;

import com.cathetine.spirder.zhihu.FileDownload;
import com.cathetine.spirder.kugou.HttpGetConnect;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @Author:cathetine
 * @Date:19-10-10
 * @Time:下午5:03
 * 网易云音乐歌单封面爬虫
 */
public class ImgCrawler {

    /**
     * 抓取网易云歌单分类的爬虫
     *
     * @param type 　歌单类型
     * @param path 　存放图片的路径
     */
    public static void crawler(String type, String path) {
        try {
            String url = "https://music.163.com/discover/playlist/?order=hot&cat=" + type + "&limit=_limit_&offset=_offset_";
            for (int offset = 0; offset <= 1295;offset = offset + 35 ) {
                url = url.replace("_limit_", "35").replace("_offset_", String.valueOf(offset));
                System.out.println("url : " + url);
                String content = HttpGetConnect.connect(url, "UTF-8");
                Document document = Jsoup.parse(content);
                Element element = document.getElementsByClass("m-cvrlst f-cb").get(0);
                Elements elements = element.getElementsByTag("li");
                for (Element li : elements) {
                    String imageUrl = li.getElementsByClass("j-flag").get(0).attr("abs:src");
                    String name = li.getElementsByClass("msk").get(0).attr("title");
                    System.out.println("正在下载　　文件名:" + name.replaceAll("/", "-") + "  路径:" + imageUrl);
                    FileDownload.download(imageUrl, path + name.replaceAll("/", "-"));
                }
                url = url.replace("_limit_", "35").replace(String.valueOf(offset), "_offset_");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "/home/cathetine/wangyiyun-pictures/";
        crawler("放松", path);
    }
}

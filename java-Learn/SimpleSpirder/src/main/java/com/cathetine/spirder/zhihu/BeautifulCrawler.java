package com.cathetine.spirder.zhihu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cathetine.spirder.Constant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @Author:cathetine
 * @Date:19-10-12
 * @Time:下午10:55
 */
public class BeautifulCrawler {

    /**
     * 获取话题  有没有谁让你感叹道世界上怎么会有如此好看的人？　高赞回答中的图片
     */
    public static void getPic(String url, String childPath) {
        String referer = "https://www.zhihu.com/search?type=content&q=%E5%A5%BD%E7%9C%8B";
        String cookie =  "_zap=ddf822af-4310-46a2-ad2e-be2ff3c141ae; d_c0=\"AKBiolUO5Q-PTm15ntzUZewfOgItH1l4lAE=|1565849725\"; _xsrf=gdMH2IgQ7Ko8CHU8YCqgCpw5Q0e5RPkP; tgw_l7_route=f2979fdd289e2265b2f12e4f4a478330; capsion_ticket=\"2|1:0|10:1570891494|14:capsion_ticket|44:OGU2OWZkODFjNzE3NDMwOTkwNjI5NzI4MjcwMjllNmQ=|fa7dd31c9ac8be274e4cc23a272b83331a1f20bea7f5266d6720befb26d98beb\"; Hm_lvt_98beee57fd2ef70ccdd5ca52b9740c49=1570535348,1570891475,1570891492,1570891506; Hm_lpvt_98beee57fd2ef70ccdd5ca52b9740c49=1570892121";
        int loadCount = 1;
        int urlCount = 1;
        String nextUrl = url;
        try {
            do {
                String content = HttpGetConnect.connect(nextUrl, Constant.CHARSET,referer,cookie);
                urlCount++;
                JSONObject contentJSON = JSON.parseObject(content);
                JSONArray data = (JSONArray) contentJSON.get("data");
                for (Object datum : data) {
                    JSONObject everyInfo = (JSONObject) datum;
                    JSONObject authorInfo = everyInfo.getJSONObject("author");
                    String userName = authorInfo.getString("name");
                    System.out.println("用户名:" + userName);
                    String everyInfoHtml = everyInfo.get("content").toString();
                    Document document = Jsoup.parse(everyInfoHtml);
                    Elements images = document.getElementsByTag("img");
                    int curIndex = 1;
                    for (int i = 1; ; i++) {
                        if (i >= images.size()) {
                            break;
                        }
                        String imageSrc = images.get(i).attr("src");
                        if (imageSrc.startsWith("data")) {
                            continue;
                        }
                        String suffix = imageSrc.substring(imageSrc.length() - 3);
                        System.out.println("下载第" + loadCount++ + "张图片, 图片地址: " + images.get(i).attr("src"));
                        try {
                            FileDownload.download(imageSrc, Constant.FILE_PATH + childPath + userName + "_" + curIndex++ + "." + suffix);
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                            continue;
                        }
                    }
                }
                JSONObject next = contentJSON.getJSONObject("paging");
                nextUrl = next.getString("next");
            } while (nextUrl != null || urlCount <= 5);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BeautifulCrawler.getPic("https://www.zhihu.com/api/v4/questions/33797069/answers?include=data%5B*%5D.is_normal%2Cadmin_closed_comment%2Creward_info%2Cis_collapsed%2Cannotation_action%2Cannotation_detail%2Ccollapse_reason%2Cis_sticky%2Ccollapsed_by%2Csuggest_edit%2Ccomment_count%2Ccan_comment%2Ccontent%2Ceditable_content%2Cvoteup_count%2Creshipment_settings%2Ccomment_permission%2Ccreated_time%2Cupdated_time%2Creview_info%2Crelevant_info%2Cquestion%2Cexcerpt%2Crelationship.is_authorized%2Cis_author%2Cvoting%2Cis_thanked%2Cis_nothelp%2Cis_labeled%2Cis_recognized%2Cpaid_info%2Cpaid_info_content%3Bdata%5B*%5D.mark_infos%5B*%5D.url%3Bdata%5B*%5D.author.follower_count%2Cbadge%5B*%5D.topics&offset=3&limit=5&sort_by=default&platform=desktop", "/zhihu/有没有谁让你感叹道世界上怎么会有如此好看的人/");
    }
}

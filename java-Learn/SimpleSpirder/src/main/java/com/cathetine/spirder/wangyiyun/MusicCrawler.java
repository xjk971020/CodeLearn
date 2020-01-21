package com.cathetine.spirder.wangyiyun;

import com.cathetine.spirder.zhihu.FileDownload;
import com.cathetine.spirder.kugou.HttpGetConnect;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:cathetine
 * @Date:19-10-10
 * @Time:下午8:27
 * 网易云音乐歌曲爬虫
 */
public class MusicCrawler {

    private static Log log = LogFactory.getLog(MusicCrawler.class);

    private static String MUSIC_DOWNLOAD_URL = "http://music.163.com/song/media/outer/url?id=";
    private static String MUSIC_DOWNLOAD_PATH = "/home/cathetine/wangyiyun-musics/";

    /**
     * 根据歌手id获取该歌手的收藏热门歌曲50首
     * @param singerId
     * @return
     */
    public static Map<String, String> getMusicsBySingerId(String singerId) {
        String url = "https://music.163.com/artist?id=" + singerId;
        Map<String,String> songList = new HashMap<>();
        try {
            String content = HttpGetConnect.connect(url,"UTF-8");
            Document document = Jsoup.parse(content);
            Element table = document.getElementById("song-list-pre-cache");
            Elements lis = table.getElementsByTag("li");
            for (Element li : lis) {
                String songId = li.getElementsByTag("a").attr("href");
                songId = songId.substring(songId.indexOf("=") + 1);
                String songName = li.getElementsByTag("a").text();
//                log.info("歌曲id : " + songId + " 歌曲名称: " + singName);
                System.out.println("歌曲id : " + songId + " 歌曲名称: " + songName );
                songList.put(songId, songName );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return songList;
    }

    public static void downloadMusic(String songId, String songName) {
        FileDownload.download(MUSIC_DOWNLOAD_URL + songId, MUSIC_DOWNLOAD_PATH + songName);
    }

    public static void main(String[] args) {
        Map<String,String> musicData = getMusicsBySingerId("861777");
        musicData.entrySet().forEach(entry -> {
            String songId = entry.getKey();
            String songName = entry.getValue();
            downloadMusic(songId, songName);
        });
    }
}

package com.cathetine.spirder.zhihu;

/**
 * @Author:xjk
 * @Date 2019/12/3 10:26
 * 知乎视频爬取
 */
public class VideoCrawler {
    public static void getVideo(String url) {
        FileDownload.download(url, "D:\\爬虫资料\\知乎视频\\艺妓回忆录.mp4");
    }

    public static void main(String[] args) {
//        String url = "https://vdn1.vzuu.com/SD/f1431af6-16c1-11ea-996f-2e1c8acebc16.mp4?disable_local_cache=1&bu=http-com&expiration=1576063025&auth_key=1576063025-0-0-bc7b5fd80413d02332ffeff462ed9c00&f=mp4&v=hw ";
//        String url = "https://vdn1.vzuu.com/SD/743bc13c-0791-11ea-89b3-52a98671e3ae.mp4?disable_local_cache=1&bu=http-com&expiration=1576062533&auth_key=1576062533-0-0-e7fdda482805ad57580622e3121050ca&f=mp4&v=hw";
        String url = "https://vdn1.vzuu.com/SD/ff1c9326-a6f7-11e9-a3ff-0a580a434680.mp4?disable_local_cache=1&bu=http-com&expiration=1576063102&auth_key=1576063102-0-0-90f459245d8d82fbee7b3b6dd7baab0f&f=mp4&v=hw";
        getVideo(url);
    }
}

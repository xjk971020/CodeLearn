package com.cathetine.spirder.movie;

import com.cathetine.spirder.Constant;
import com.cathetine.spirder.zhihu.FileDownload;

/**
 * @Author:cathetine
 * @Date:19-10-13
 * @Time:下午3:10
 *　无法全部爬取，只能爬取第一段视频
 */
public class VipMovieCrawler {
    public static String url = "http://183.235.254.87/cache/18ce6c9d89dabc49.ngaa.net.cn/newstreamcdnct.inter.iqiyi.com/videos/v0/20190907/fc/64/27df3db750888d2e7b66a42b825655f5.mp4?key=0086b05bf4c61184596b21b306e02487c&dis_k=7e093f7b0c9d53d6e4056f32e93d33d1&dis_t=1570941258&dis_dz=CT-JiangSu&dis_st=44&src=iqiyi.com&dis_hit=0&uuid=0a798247-5da2a94a-bc&qd_ip=3da0df02&dfp=&ip=61.160.223.2&qd_k=a020b7bf2f6e0c44273dce2804d70240&qd_uid=0&qd_tm=1570941258129&m=v&qd_tvid=367710700&qd_p=3da0df02&sgti=&ssl=&qd_vip=0&dis_src=vrs&qd_src=76f90cbd92f94a2e925d83e8ccd22cb7&qdv=1&ich_args2=16-13150812006935_e1abbd63e5977becd4b2c2d6ba85fd11_10010421_9c89632cdec6f1d4973e518939a83798_cd6e3e7b77429bcff09ac3a1b3e75627";
    public static void download() {
        String path = Constant.FILE_PATH + "/movie/战狼.mp4";
        FileDownload.download(url, path);
    }

    public static void main(String[] args) {
        download();
    }
}

package com.cathetine.spirder.proxy;

import com.cathetine.spirder.HttpsGetConnect;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:xjk
 * @Date 2019/12/4 20:50
 * 代理连接测试
 */
public class ConnectionTest {
    public static void main(String[] args) {
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("Cookie","BD_UPN=12314753; BIDUPSID=F311370B9FE9107F4BEE4091EB491DC8; BAIDUID=6DE76AC211459A1C79F185C838B384A8:FG=1; PSTM=1563548221; BDUSS=NUQ3JlOG9tYmFZV2ZpSGRkb2J5YkplTGctOHp2VEFUWkplOTBwT0I3SkdjbTVkRVFBQUFBJCQAAAAAAAAAAAEAAAAHFFWleHVrYWlrYWkxMjMzNDUAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEblRl1G5UZdYU; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; BD_HOME=1; H_PS_PSSID=1447_21089_30210_30086; delPer=0; BD_CK_SAM=1; PSINO=7; H_PS_645EC=e708noxAqAU0%2Bo%2FfNri16mOC5EnwpCHubERG%2FcPY7EflNZQrOG5dixn9gpY; sugstore=1");
            headers.put("Host","www.baidu.com");
            headers.put("Referer","https://www.baidu.com/s?wd=java%20Proxy%20%E5%87%BA%E7%8E%B0%E8%BF%9E%E6%8E%A5%E8%A2%AB%E6%8B%92%E7%BB%9D&pn=10&oq=java%20Proxy%20%E5%87%BA%E7%8E%B0%E8%BF%9E%E6%8E%A5%E8%A2%AB%E6%8B%92%E7%BB%9D&ie=utf-8&rsv_pq=a508aab30001abe7&rsv_t=3615gHwrY%2FUiWRMS%2Fki9GsPhFKGttfOf%2B2lbPeK%2BPwPW7%2B15lGk9I32RUpU&rsv_page=1");
            headers.put("Sec-Fetch-Mode","navigate");
            headers.put("Sec-Fetch-Site","same-origin");
            headers.put("Upgrade-Insecure-Requests","1");
            String url = "https://www.baidu.com/";
            IPBean ipBean = new IPBean();
            //使用一个有效的ip
            ipBean.setIp("61.153.251.150");
            ipBean.setPort("22222");
            ipBean.setType("HTTP");
            System.out.println(IPUtil.isValid_methodTwo(ipBean));
            System.out.println(HttpsGetConnect.getResponseContent(url, headers, ipBean));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

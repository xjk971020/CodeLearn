package com.cathetine.spirder;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.KumoFont;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.nlp.tokenizers.ChineseWordTokenizer;
import com.kennycason.kumo.palette.LinearGradientColorPalette;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @Author:cathetine
 * @Date:19-10-12
 * @Time:上午11:03
 * 词云生成器
 */
public class WordCloudGenerator {

    /**
     * 将爬取到的数据存入到txt文件中
     *
     * @param result
     * @param filePath
     * @param fileName
     */
    public static void writeTitle(List<String> result, String filePath, String fileName) {

        File file = new File(filePath, fileName);
        file.delete();
        FileOutputStream fileWriter = null;
        try {
            boolean create = file.createNewFile();
            if (create) {
                System.out.println("创建文件: " + filePath + fileName);
            }
            fileWriter = new FileOutputStream(file);
            for (String s : result) {
                s = s + "\n";
                fileWriter.write(s.getBytes(Constant.CHARSET));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据文件生成词云
     * @param filePath
     * @param txtFileName
     * @param outputPic
     */
    public static void getWordCloud(String filePath, String txtFileName, String outputPic) throws IOException {
        //建立词频分析器，设置词频，以及词语最短长度，此处的参数配置视情况而定即可
        FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        frequencyAnalyzer.setWordFrequenciesToReturn(600);
        frequencyAnalyzer.setMinWordLength(2);

        //引入中文解析器
        frequencyAnalyzer.setWordTokenizer(new ChineseWordTokenizer());
        //指定文本文件路径，生成词频集合
        final List<WordFrequency> wordFrequencyList = frequencyAnalyzer.load(filePath + txtFileName);
        //设置图片分辨率
        Dimension dimension = new Dimension(1920,1080);
        //此处的设置采用内置常量即可，生成词云对象
        WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
        //设置边界及字体
        wordCloud.setPadding(2);
        java.awt.Font font = new java.awt.Font("STSong-Light", 2, 20);
        //设置词云显示的三种颜色，越靠前设置表示词频越高的词语的颜色
        wordCloud.setColorPalette(new LinearGradientColorPalette(Color.RED, Color.BLUE, Color.GREEN, 30, 30));
        wordCloud.setKumoFont(new KumoFont(font));
        //设置背景色
        wordCloud.setBackgroundColor(new Color(255,255,255));
        //设置背景图片
        //wordCloud.setBackground(new PixelBoundryBackground("E:\\爬虫/google.jpg"));
        //设置背景图层为圆形
        wordCloud.setBackground(new CircleBackground(255));
        wordCloud.setFontScalar(new SqrtFontScalar(12, 45));
        //生成词云
        wordCloud.build(wordFrequencyList);
        wordCloud.writeToFile(filePath + outputPic);
    }
}

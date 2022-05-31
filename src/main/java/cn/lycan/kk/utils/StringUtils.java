package cn.lycan.kk.utils;

import java.util.Random;

/**
 * @author Makkapakka
 * @date 2022-5-31
 * @package_name cn.lycan.kk.utils
 * @description 图片上传功能--后端主要解决如下两个问题：
 * 如何接收前端传来的图片数据并保存
 * 如何避免重名（图片资源的名字很可能重复，如不修改可能出现问题）
 */
public class StringUtils {
    /**
     * 编写生成指定长度（长度为参数length的长度）随机字符串的方法
     * @param length
     * @return
     */
    public static String getRandomString(int length){
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0;i<length;i++){
            int number = random.nextInt(base.length());
            stringBuffer.append(base.charAt(number));
        }
        return stringBuffer.toString();
    }
}

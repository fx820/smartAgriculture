package cn.ecnu.utils;

import java.util.*;

/**
 * @intro:图像识别工具类
 * @author: zachary
 * @version: 1.0
 */
public class Easyutils {
    public static String easyDLImageClassify() {

        String base64 = ImageToBase64Utils.ImageToBase64ByOnline("http://rv5avdw47.bkt.clouddn.com/pest/image%20%282%29.JPG");

        // 请求url
        String url = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/classification/smart-agriculture";

        try {
            Map<String, Object> map = new HashMap<>();
            //image	是	string	-	图像数据，base64编码，要求base64编码后大小不超过4M，最短边至少15px，最长边最大4096px,支持jpg/png/bmp格式 注意请去掉头部
            //top_num	否	number	-返回分类数量，默认为6个
            //url	否	string	-	如果在请求URL参数中增加“input_type=url”，则该参数必传，否则“image”参数必传。参数内容为URL string，用户需确保该string是有效的图片URL，否则会下载失败
            map.put("image",base64);
            map.put("top_num", "1");

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。

            String accessToken = (String) EasyDLTokenUtils.getToken().get("access_token");

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


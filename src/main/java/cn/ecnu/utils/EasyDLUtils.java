package cn.ecnu.utils;

import java.util.HashMap;
import java.util.Map;

import static cn.ecnu.constant.CommonConstant.EASY_DL_URL;

/**
 * @intro:图像识别工具类
 * @author: zachary
 * @version: 1.0
 */
public class EasyDLUtils {
    public static String easyDLImageClassify(String imageUrl) {
        //在线图片路径
        String base64 = ImageToBase64Utils.ImageToBase64ByOnline(imageUrl);
        try {
            Map<String, Object> map = new HashMap<>();

            //base64 图片信息
            map.put("image",base64);
            //返回分类数量，默认为6个
            map.put("top_num", "1");
            //请求参数封装map
            String param = GsonUtils.toJson(map);
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = (String) EasyDLTokenUtils.getToken().get("access_token");
            String result = HttpUtil.post(EASY_DL_URL, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

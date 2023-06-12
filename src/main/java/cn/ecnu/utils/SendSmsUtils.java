package cn.ecnu.utils;

import cn.ecnu.constant.RedisConstant;
import cn.ecnu.constant.SmsConstant;
import com.zhenzi.sms.ZhenziSmsClient;

import java.util.HashMap;
import java.util.Map;

/**
 * @intro:榛子云短信工具类
 * @author: zachary
 * @version: 1.0
 */

public class SendSmsUtils {

    public static void send(String phoneNumber, String code) {
        // 使用自己的 AppId 和 AppSecret
        ZhenziSmsClient client = new ZhenziSmsClient(SmsConstant.API_URL, SmsConstant.APP_ID,SmsConstant.APP_SECRET);
        Map<String, Object> params = new HashMap<>();
        params.put("number", phoneNumber);
        // 修改为自己的templateId
        params.put("templateId", SmsConstant.TEMPLATE_ID);
        String[] templateParams = new String[2];
        templateParams[0] = code;
        templateParams[1] = String.valueOf(SmsConstant.CODE_EXPIRE_TIME);
        params.put("templateParams", templateParams);
        try {
            String result = client.send(params);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

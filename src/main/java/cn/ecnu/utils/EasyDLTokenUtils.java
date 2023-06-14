package cn.ecnu.utils;

import com.squareup.okhttp.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static cn.ecnu.constant.CommonConstant.EASY_DL_OAUTH;

/**
 * @intro:EasyDL鉴权工具类
 * @author: zachary
 * @version: 1.0
 */
public class EasyDLTokenUtils {
    static final OkHttpClient HTTP_CLIENT = new OkHttpClient();

    /**
     * 返回Token
     * @return
     * @throws IOException
     */
    public final static Map<String,Object> getToken() throws IOException {
        HashMap map = new HashMap<>();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(EASY_DL_OAUTH)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        String result = response.body().string();
        String[] split = result.split(",");
        map.put("access_token",split[3]);
        map.put("expires_in",split[1]);
        return map;
    }

}

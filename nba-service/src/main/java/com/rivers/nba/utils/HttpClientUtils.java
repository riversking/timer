package com.rivers.nba.utils;

import lombok.extern.log4j.Log4j2;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

@Log4j2
public class HttpClientUtils {

    public static String get(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        String bodyStr = "";
        try (Response response = client.newCall(request).execute()) {
            ResponseBody body = response.body();
            if (body != null) {
                bodyStr = body.string();
            }
        } catch (IOException e) {
            log.error("NBA ERROR", e);
        }
        return bodyStr;
    }

}

package com.bytedance.sdk.adnet.game.http.b;

import com.bytedance.b.a.a.c.e;
import com.bytedance.sdk.account.utils.UrlBuilder;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.MediaType;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Response;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.ResponseBody;
import com.bytedance.sdk.b.d.a;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    public static final MediaType f5030a = MediaType.parse(e.f3610c);

    /* renamed from: b, reason: collision with root package name */
    public static final MediaType f5031b = MediaType.parse("text/plain; charset=utf-8");

    /* renamed from: c, reason: collision with root package name */
    public static final MediaType f5032c = MediaType.parse(a.f.f5151a);
    public static final MediaType d = MediaType.parse(UrlBuilder.CONTENT_TYPE);

    public static String a(ResponseBody responseBody) {
        if (responseBody == null) {
            return "";
        }
        try {
            return responseBody.string();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static Map<String, String> a(Response response) {
        if (response == null || response.headers() == null) {
            return new HashMap();
        }
        HashMap map = new HashMap();
        int size = response.headers().size();
        for (int i = 0; i < size; i++) {
            map.put(response.headers().name(i), response.headers().value(i));
        }
        return map;
    }

    public static JSONObject a(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new JSONObject(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String b(ResponseBody responseBody) {
        String string;
        if (responseBody == null) {
            return "";
        }
        MediaType mediaTypeContentType = responseBody.contentType();
        if (mediaTypeContentType != null && (string = mediaTypeContentType.toString()) != null) {
            String lowerCase = string.toLowerCase();
            if (lowerCase.contains("audio") || lowerCase.contains("video") || lowerCase.contains("pdf") || lowerCase.contains("image") || lowerCase.contains("android.package")) {
                return "";
            }
        }
        return a(responseBody);
    }

    public static JSONArray b(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new JSONArray(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static MediaType c(String str) {
        String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(str.replace("#", ""));
        return contentTypeFor == null ? f5032c : MediaType.parse(contentTypeFor);
    }
}

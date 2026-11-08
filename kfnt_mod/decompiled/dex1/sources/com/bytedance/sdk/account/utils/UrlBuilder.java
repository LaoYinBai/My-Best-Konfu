package com.bytedance.sdk.account.utils;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class UrlBuilder {
    public static final String ASCII = "ASCII";
    public static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
    public static final String DEFAULT_CONTENT_CHARSET = "ISO-8859-1";
    public static final String DEFAULT_PROTOCOL_CHARSET = "US-ASCII";
    public static final String ISO_8859_1 = "ISO-8859-1";
    private static final String NAME_VALUE_SEPARATOR = "=";
    private static final String PARAMETER_SEPARATOR = "&";
    public static final String US_ASCII = "US-ASCII";
    public static final String UTF_16 = "UTF-16";
    public static final String UTF_8 = "UTF-8";
    private final Map<String, String> mList;
    private String mUrl;

    public UrlBuilder(String str) {
        this.mList = new LinkedHashMap();
        this.mUrl = str;
    }

    public UrlBuilder() {
        this.mList = new LinkedHashMap();
        this.mUrl = null;
    }

    public void setUrl(String str) {
        this.mUrl = str;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void addParam(String str, int i) {
        this.mList.put(str, String.valueOf(i));
    }

    public void addParam(String str, long j) {
        this.mList.put(str, String.valueOf(j));
    }

    public void addParam(String str, double d) {
        this.mList.put(str, String.valueOf(d));
    }

    public void addParam(String str, String str2) {
        this.mList.put(str, str2);
    }

    public Map<String, String> getParamList() {
        return this.mList;
    }

    public String build() {
        if (this.mList.isEmpty()) {
            return this.mUrl;
        }
        String str = format(this.mList, "UTF-8");
        if (this.mUrl == null || this.mUrl.length() == 0) {
            return str;
        }
        if (this.mUrl.indexOf(63) >= 0) {
            return this.mUrl + "&" + str;
        }
        return this.mUrl + "?" + str;
    }

    public static String format(Map<String, String> map, String str) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String strEncode = encode(entry.getKey(), str);
            String value = entry.getValue();
            String strEncode2 = value != null ? encode(value, str) : "";
            if (!TextUtils.isEmpty(strEncode2)) {
                strEncode2 = strEncode2.replace("+", "%20");
            }
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(strEncode);
            sb.append(NAME_VALUE_SEPARATOR);
            sb.append(strEncode2);
        }
        return sb.toString();
    }

    private static String decode(String str, String str2) {
        if (str2 == null) {
            str2 = "ISO-8859-1";
        }
        try {
            return URLDecoder.decode(str, str2);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static String encode(String str, String str2) {
        if (str2 == null) {
            str2 = "ISO-8859-1";
        }
        try {
            return URLEncoder.encode(str, str2);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public String toString() {
        return build();
    }
}

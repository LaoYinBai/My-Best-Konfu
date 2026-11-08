package com.bytedance.sdk.adtnc.g;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    private final Map<String, List<String>> f5118a;

    /* renamed from: b, reason: collision with root package name */
    private String f5119b;

    public e() {
        this.f5118a = new LinkedHashMap();
        this.f5119b = null;
    }

    public e(String str) {
        this.f5118a = new LinkedHashMap();
        this.f5119b = str;
    }

    private String b(String str, String str2) {
        if (str2 == null) {
            str2 = "ISO-8859-1";
        }
        try {
            return URLEncoder.encode(str, str2);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public String a() {
        StringBuilder sb;
        String str;
        if (this.f5118a.isEmpty()) {
            return this.f5119b;
        }
        String strA = a(this.f5118a, "UTF-8");
        if (this.f5119b == null || this.f5119b.length() == 0) {
            return strA;
        }
        if (this.f5119b.indexOf(63) >= 0) {
            sb = new StringBuilder();
            sb.append(this.f5119b);
            str = "&";
        } else {
            sb = new StringBuilder();
            sb.append(this.f5119b);
            str = "?";
        }
        sb.append(str);
        sb.append(strA);
        return sb.toString();
    }

    public String a(Map<String, List<String>> map, String str) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String strB = b(entry.getKey(), str);
            List<String> value = entry.getValue();
            if (value != null && value.size() > 0) {
                Iterator<String> it = value.iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    String strB2 = next != null ? b(next, str) : "";
                    if (sb.length() > 0) {
                        sb.append("&");
                    }
                    sb.append(strB);
                    if (!strB2.isEmpty()) {
                        sb.append("=");
                        sb.append(strB2);
                    }
                }
            }
        }
        return sb.toString();
    }

    public void a(String str, double d) {
        List<String> linkedList = this.f5118a.get(str);
        if (linkedList == null) {
            linkedList = new LinkedList<>();
        }
        linkedList.add(String.valueOf(d));
        this.f5118a.put(str, linkedList);
    }

    public void a(String str, int i) {
        List<String> linkedList = this.f5118a.get(str);
        if (linkedList == null) {
            linkedList = new LinkedList<>();
        }
        linkedList.add(String.valueOf(i));
        this.f5118a.put(str, linkedList);
    }

    public void a(String str, String str2) {
        List<String> linkedList = this.f5118a.get(str);
        if (linkedList == null) {
            linkedList = new LinkedList<>();
        }
        linkedList.add(String.valueOf(str2));
        this.f5118a.put(str, linkedList);
    }

    public String toString() {
        return a();
    }
}

package com.bytedance.sdk.b.f;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private final Map<String, List<String>> f5158a;

    /* renamed from: b, reason: collision with root package name */
    private String f5159b;

    public d(String str) {
        this.f5158a = new LinkedHashMap();
        this.f5159b = str;
    }

    public d() {
        this.f5158a = new LinkedHashMap();
        this.f5159b = null;
    }

    public void a(String str) {
        this.f5159b = str;
    }

    public String a() {
        return this.f5159b;
    }

    public void a(String str, int i) {
        List<String> linkedList = this.f5158a.get(str);
        if (linkedList == null) {
            linkedList = new LinkedList<>();
        }
        linkedList.add(String.valueOf(i));
        this.f5158a.put(str, linkedList);
    }

    public void a(String str, long j) {
        List<String> linkedList = this.f5158a.get(str);
        if (linkedList == null) {
            linkedList = new LinkedList<>();
        }
        linkedList.add(String.valueOf(j));
        this.f5158a.put(str, linkedList);
    }

    public void a(String str, double d) {
        List<String> linkedList = this.f5158a.get(str);
        if (linkedList == null) {
            linkedList = new LinkedList<>();
        }
        linkedList.add(String.valueOf(d));
        this.f5158a.put(str, linkedList);
    }

    public void a(String str, String str2) {
        List<String> linkedList = this.f5158a.get(str);
        if (linkedList == null) {
            linkedList = new LinkedList<>();
        }
        linkedList.add(String.valueOf(str2));
        this.f5158a.put(str, linkedList);
    }

    public Map<String, String> b() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (this.f5158a != null && this.f5158a.size() > 0) {
            for (Map.Entry<String, List<String>> entry : this.f5158a.entrySet()) {
                String key = entry.getKey();
                if (!TextUtils.isEmpty(key)) {
                    List<String> value = entry.getValue();
                    String str = "";
                    if (value != null && value.size() > 0) {
                        str = value.get(0);
                    }
                    linkedHashMap.put(key, str);
                }
            }
        }
        return linkedHashMap;
    }

    public Map<String, List<String>> c() {
        return this.f5158a;
    }

    public String d() {
        StringBuilder sb;
        String str;
        if (this.f5158a.isEmpty()) {
            return this.f5159b;
        }
        String strA = a(this.f5158a, "UTF-8");
        if (this.f5159b == null || this.f5159b.length() == 0) {
            return strA;
        }
        if (this.f5159b.indexOf(63) >= 0) {
            sb = new StringBuilder();
            sb.append(this.f5159b);
            str = "&";
        } else {
            sb = new StringBuilder();
            sb.append(this.f5159b);
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

    public String toString() {
        return d();
    }
}

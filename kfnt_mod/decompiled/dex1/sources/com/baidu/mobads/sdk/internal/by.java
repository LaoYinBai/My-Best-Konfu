package com.baidu.mobads.sdk.internal;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class by {

    /* renamed from: a, reason: collision with root package name */
    private static volatile by f3194a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f3195b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f3196c;

    private by() {
    }

    public static by a() {
        if (f3194a == null) {
            synchronized (by.class) {
                if (f3194a == null) {
                    f3194a = new by();
                }
            }
        }
        return f3194a;
    }

    public void a(boolean z) {
        this.f3196c = z;
    }

    public boolean b() {
        return this.f3196c;
    }

    public void b(boolean z) {
        this.f3195b = z;
    }

    public Boolean a(String str) {
        return a(str, "http:");
    }

    public Boolean b(String str) {
        return a(str, "https:");
    }

    public String c(String str) {
        return (this.f3195b && a(str).booleanValue()) ? str.replaceFirst("(?i)http", "https") : str;
    }

    public Boolean d(String str) {
        return Boolean.valueOf(a(str, "sms:").booleanValue() || a(str, "smsto:").booleanValue() || a(str, "mms:").booleanValue());
    }

    private Boolean a(String str, String str2) {
        boolean z = false;
        if (str != null && str.trim().toLowerCase(Locale.getDefault()).indexOf(str2) == 0) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    public String e(String str) {
        try {
            String path = new URI(str).getPath();
            return path.substring(path.lastIndexOf(47) + 1, path.length());
        } catch (URISyntaxException unused) {
            return "";
        }
    }

    public HttpURLConnection a(URL url) {
        if (url.getProtocol().toLowerCase().equals("https")) {
            return (HttpsURLConnection) url.openConnection();
        }
        return (HttpURLConnection) url.openConnection();
    }

    public String f(String str) {
        if (str == null) {
            return null;
        }
        return (a(str).booleanValue() || b(str).booleanValue()) ? str.split("\\?")[0] : str;
    }

    public String g(String str) {
        if (str == null) {
            return null;
        }
        String[] strArrSplit = (a(str).booleanValue() || b(str).booleanValue()) ? str.split("\\?") : null;
        if (strArrSplit == null || strArrSplit.length < 2) {
            return null;
        }
        return strArrSplit[1];
    }

    public void h(String str) {
        new ae(str).b();
    }

    public String a(String str, HashMap<String, String> map) {
        StringBuilder sb = new StringBuilder(str);
        if (map == null || map.isEmpty()) {
            return sb.toString();
        }
        sb.append("?");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            try {
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
                sb.append("&");
            } catch (Exception e) {
                bb.a().c(e);
            }
        }
        return sb.toString().substring(0, r3.length() - 1);
    }

    public void a(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            try {
                httpURLConnection.getInputStream().close();
            } catch (Throwable unused) {
            }
            try {
                httpURLConnection.getOutputStream().close();
            } catch (Throwable unused2) {
            }
            try {
                httpURLConnection.disconnect();
            } catch (Throwable unused3) {
            }
        }
    }
}

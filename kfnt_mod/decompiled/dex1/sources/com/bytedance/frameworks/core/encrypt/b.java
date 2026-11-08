package com.bytedance.frameworks.core.encrypt;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import anet.channel.util.HttpConstant;
import com.appsflyer.share.Constants;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static final String f3857a = "RequestEncryptUtils";

    /* renamed from: b, reason: collision with root package name */
    private static final String f3858b = "device_id";

    /* renamed from: c, reason: collision with root package name */
    private static final String f3859c = "device_type";
    private static final String d = "device_brand";
    private static final String e = "uuid";
    private static final String f = "openudid";
    private static final String g = "ss_queries";
    private static final String h = "X-SS-QUERIES";
    private static final String i = "ss_encrypt";
    private static a j = null;
    private static boolean k = false;
    private static final String l = "&";
    private static final String m = "=";
    private static final String n = "ISO-8859-1";

    public interface a {
        boolean a();

        boolean b();

        boolean c();
    }

    public static void a(a aVar) {
        j = aVar;
    }

    public static void a(boolean z) {
        k = z;
    }

    public static String a(String str, List<Pair<String, String>> list) {
        if (TextUtils.isEmpty(str) || j == null || k) {
            return str;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        try {
            URI uriCreate = URI.create(str);
            a(uriCreate, linkedHashMap);
            LinkedList linkedList = new LinkedList();
            a(linkedHashMap, linkedList, "device_id");
            a(linkedHashMap, linkedList, f3859c);
            a(linkedHashMap, linkedList, d);
            a(linkedHashMap, linkedList, e);
            a(linkedHashMap, linkedList, f);
            String strA = a((List<Pair<String, List<String>>>) linkedList, false, "UTF-8");
            if (TextUtils.isEmpty(strA)) {
                return str;
            }
            byte[] bytes = strA.getBytes();
            byte[] bArrA = TTEncryptUtils.a(bytes, bytes.length);
            if (bArrA == null) {
                return str;
            }
            String strEncodeToString = Base64.encodeToString(bArrA, 2);
            LinkedList linkedList2 = new LinkedList();
            if (j.a()) {
                LinkedList linkedList3 = new LinkedList();
                linkedList3.add(strEncodeToString);
                linkedList2.add(new Pair(g, linkedList3));
            }
            if (j.b() && list != null) {
                list.add(new Pair<>(h, a(strEncodeToString, "UTF-8")));
            }
            if (!linkedHashMap.isEmpty()) {
                for (Map.Entry entry : linkedHashMap.entrySet()) {
                    if (entry != null) {
                        linkedList2.add(new Pair(entry.getKey(), entry.getValue()));
                    }
                }
            }
            return a(uriCreate.getScheme(), uriCreate.getHost(), uriCreate.getPort(), uriCreate.getRawPath(), a((List<Pair<String, List<String>>>) linkedList2, true, "UTF-8"), uriCreate.getRawFragment()).toString();
        } catch (Throwable th) {
            th.printStackTrace();
            return str;
        }
    }

    public static byte[] a(byte[] bArr) {
        if (bArr == null || bArr.length == 0 || k) {
            return bArr;
        }
        try {
            return TTEncryptUtils.a(bArr, bArr.length);
        } catch (Throwable th) {
            th.printStackTrace();
            return bArr;
        }
    }

    private static boolean a(Map<String, List<String>> map, List<Pair<String, List<String>>> list, String str) throws UnsupportedEncodingException {
        if (TextUtils.isEmpty(str) || list == null || !map.containsKey(str)) {
            return false;
        }
        list.add(new Pair<>(str, map.get(str)));
        if (j.c()) {
            return true;
        }
        map.remove(str);
        return true;
    }

    public static void a(URI uri, Map<String, List<String>> map) throws IOException {
        if (uri == null) {
            throw new IOException("parseUrl url is null !!!");
        }
        if (map != null) {
            try {
                String rawQuery = uri.getRawQuery();
                if (rawQuery != null) {
                    for (String str : rawQuery.split("&")) {
                        int iIndexOf = str.indexOf(m);
                        if (iIndexOf >= 0) {
                            String strDecode = URLDecoder.decode(str.substring(0, iIndexOf), "UTF-8");
                            List<String> linkedList = map.get(strDecode);
                            if (linkedList == null) {
                                linkedList = new LinkedList<>();
                            }
                            linkedList.add(URLDecoder.decode(str.substring(iIndexOf + 1), "UTF-8"));
                            map.put(strDecode, linkedList);
                        } else {
                            String strDecode2 = URLDecoder.decode(str, "UTF-8");
                            List<String> linkedList2 = map.get(strDecode2);
                            if (linkedList2 == null) {
                                linkedList2 = new LinkedList<>();
                            }
                            linkedList2.add("");
                            map.put(strDecode2, linkedList2);
                        }
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
                throw new IOException("parseUrl url is null !!!");
            }
        }
    }

    private static URI a(String str, String str2, int i2, String str3, String str4, String str5) throws URISyntaxException {
        StringBuilder sb = new StringBuilder();
        if (str2 != null) {
            if (str != null) {
                sb.append(str);
                sb.append(HttpConstant.SCHEME_SPLIT);
            }
            sb.append(str2);
            if (i2 > 0) {
                sb.append(':');
                sb.append(i2);
            }
        }
        if (str3 == null || !str3.startsWith(Constants.URL_PATH_DELIMITER)) {
            sb.append('/');
        }
        if (str3 != null) {
            sb.append(str3);
        }
        if (str4 != null) {
            sb.append('?');
            sb.append(str4);
        }
        if (str5 != null) {
            sb.append('#');
            sb.append(str5);
        }
        return new URI(sb.toString());
    }

    private static String a(List<Pair<String, List<String>>> list, boolean z, String str) {
        StringBuilder sb = new StringBuilder();
        for (Pair<String, List<String>> pair : list) {
            String strA = (String) pair.first;
            if (z) {
                strA = a((String) pair.first, str);
            }
            List<String> list2 = (List) pair.second;
            if (list2 != null && list2.size() > 0) {
                for (String strA2 : list2) {
                    if (z) {
                        strA2 = strA2 != null ? a(strA2, str) : "";
                    }
                    if (sb.length() > 0) {
                        sb.append("&");
                    }
                    sb.append(strA);
                    sb.append(m);
                    sb.append(strA2);
                }
            }
        }
        return sb.toString();
    }

    private static String a(String str, String str2) {
        if (str2 == null) {
            str2 = "ISO-8859-1";
        }
        try {
            return URLEncoder.encode(str, str2);
        } catch (UnsupportedEncodingException e2) {
            throw new IllegalArgumentException(e2);
        }
    }
}

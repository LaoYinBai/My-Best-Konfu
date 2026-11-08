package com.huawei.a.a.b.k;

import android.annotation.TargetApi;
import android.text.TextUtils;
import android.webkit.URLUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f {
    private static final String[] a = {"e2f856b9f9a4fd4cb2795aeaf83268e4bff189aaec05d691ffde76e075b82648", "173cf86fe9894a0f70dadd09d4fd88c380836099d4939f8c3754361bdc16a32b", "b368b110e3b565fe97c91f786e11bc48754cc8e4e6f21d8a94a68ac6ad67aaaf", "db48223fd9e143f7e133c57f5d08a4e38549ce3ebd921fe3b4003c26e5e35bed", "4bdecdf772491e35c4e8b48f88aee22bae1311984f2e1da4dfad0b78ee7f5163", "3081a0adab3018d57165e6dd24074bdbac640f6dbe21a9e24d3474a87ebf38b8", "db53fcdc9ab71e9bdd4eab257fe1aba7989ad2b24fbe3a85dfef72ea1dd6bae2", "d80f18e8081b624cc64985f87f70118f1702985d2e10dbc985ee7be334fd3c7d", "5fed96c85bd58c58aadbd465c172a4c9a794d8eb2f86cbc7bcee6caf4c7a2c5f", "07ff9b7aeeff969173c45b285fe0fecdbaae244576ff7a2796a36f1c0c11adb4", "92974c6802419e4d18b5ec536cbfa167b8e8eff09ec4c8510a5b95750b1e0c82", "403f14ad2f0e5eb3c4f3a0bcd5c1592cc4492662ad53191c92905255d4990656", "4230baa077b401374d0fc012375047e79ea0790d58d095ef18d97d95470c738d"};

    private static String a(String str, int i) {
        if (TextUtils.isEmpty(str) || i <= 0) {
            com.huawei.a.a.b.e.a.b("UriUtil", "url is null");
            return str;
        }
        String[] strArrSplit = str.split("\\.");
        if (strArrSplit.length < i) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(strArrSplit[strArrSplit.length - i]);
        for (int i2 = 1; i2 < i; i2++) {
            stringBuffer.append(".");
            stringBuffer.append(strArrSplit[(strArrSplit.length - i) + i2]);
        }
        return stringBuffer.toString();
    }

    public static boolean a(String str) {
        if (com.huawei.a.a.g.a.a.booleanValue()) {
            return true;
        }
        for (String str2 : a) {
            if (a(str, str2, 2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(String str, String str2, int i) {
        String strB = b(str);
        if (TextUtils.isEmpty(strB) || TextUtils.isEmpty(str2)) {
            com.huawei.a.a.b.e.a.d("UriUtil", "url or whitelistHash is null");
            return false;
        }
        String strA = a(strB, i);
        if (TextUtils.isEmpty(strA)) {
            com.huawei.a.a.b.e.a.d("UriUtil", "get urlLastNStr is null");
            return false;
        }
        if (str2.equals(com.huawei.a.a.b.a.b.a().a(strB))) {
            return true;
        }
        if (str2.equals(com.huawei.a.a.b.a.b.a().a(strA))) {
            try {
                String strSubstring = strB.substring(0, strB.length() - strA.length());
                if (strSubstring.endsWith(".")) {
                    return strSubstring.matches("^[A-Za-z0-9.-]+$");
                }
                return false;
            } catch (IndexOutOfBoundsException e) {
                com.huawei.a.a.b.e.a.d("UriUtil", "IndexOutOfBoundsException" + e.getMessage());
            } catch (Exception e2) {
                com.huawei.a.a.b.e.a.d("UriUtil", "Exception : " + e2.getMessage());
                return false;
            }
        }
        return false;
    }

    @TargetApi(9)
    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            com.huawei.a.a.b.e.a.b("UriUtil", "url is null");
            return str;
        }
        try {
            if (URLUtil.isNetworkUrl(str) && !str.toLowerCase(Locale.US).startsWith("http:")) {
                return new URI(str).getHost();
            }
            com.huawei.a.a.b.e.a.d("UriUtil", "url don't starts with https");
            return null;
        } catch (URISyntaxException e) {
            com.huawei.a.a.b.e.a.d("UriUtil", "getHostByURI error : " + e.getMessage());
            return null;
        }
    }
}

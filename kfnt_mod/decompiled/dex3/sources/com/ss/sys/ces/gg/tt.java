package com.ss.sys.ces.gg;

import android.util.Log;
import androidx.annotation.NonNull;
import c.a.a.a.a.ba;
import c.a.a.a.a.bi;
import com.bytedance.frameworks.baselib.network.http.NetworkParams;
import com.ss.sys.ces.b;
import com.ss.sys.ces.out.StcSDKFactory;
import com.xiaomi.mipush.sdk.Constants;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class tt {

    static class a implements NetworkParams.AddSecurityFactorProcessCallback {
        a() {
        }

        public Map<String, String> onCallToAddSecurityFactor(String str, Map<String, List<String>> map) {
            return tt.calcHttpRequestChecksum(str, map);
        }
    }

    private static boolean a(String str) {
        return false;
    }

    private static String b(String str) {
        for (String str2 : str.replace(" ", "").split(Constants.ACCEPT_TIME_SEPARATOR_SP)) {
            int iIndexOf = str2.indexOf("sessionid=");
            if (iIndexOf != -1) {
                return str2.substring(iIndexOf + 10);
            }
        }
        return null;
    }

    private static String c(String str) {
        int iIndexOf = str.indexOf("?");
        int iIndexOf2 = str.indexOf("#");
        if (iIndexOf == -1) {
            return null;
        }
        if (iIndexOf2 == -1) {
            return str.substring(iIndexOf + 1);
        }
        if (iIndexOf2 < iIndexOf) {
            return null;
        }
        return str.substring(iIndexOf + 1, iIndexOf2);
    }

    @NonNull
    public static Map<String, String> calcHttpRequestChecksum(@NonNull String str, @NonNull Map<String, List<String>> map) {
        String str2;
        HashMap map2 = new HashMap();
        try {
            if (!str.toLowerCase().contains(com.sigmob.sdk.common.Constants.HTTP) && !str.toLowerCase().contains(com.sigmob.sdk.common.Constants.HTTPS)) {
                throw new NullPointerException("nein http/https");
            }
            if (str.toLowerCase().contains("x-khronos") && str.toLowerCase().contains("x-gorgon")) {
                throw new NullPointerException("it was");
            }
        } catch (Throwable th) {
            if (!th.getMessage().contains("filter_1")) {
                Log.e("CZL_ALGO", "[-] ****** Gorgon Fetch Error !!!");
                th.printStackTrace();
            }
        }
        if (a(str)) {
            throw new NullPointerException("filter_1");
        }
        int iCurrentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        String strC = c(str);
        String str3 = null;
        String strA = (strC == null || strC.length() <= 0) ? null : bi.a(strC);
        String strA2 = null;
        String strA3 = null;
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (entry.getKey().toUpperCase().contains("X-SS-STUB")) {
                str3 = entry.getValue().get(0);
            }
            if (entry.getKey().toUpperCase().contains("COOKIE") && (str2 = entry.getValue().get(0)) != null && str2.length() > 0) {
                strA2 = bi.a(str2);
                String strB = b(str2);
                if (strB != null && strB.length() > 0) {
                    strA3 = bi.a(strB);
                    StcSDKFactory.getInstance().setSession(strB);
                }
            }
        }
        map2.put("X-Khronos", "" + iCurrentTimeMillis);
        if (strA == null || strA.length() == 0) {
            strA = "00000000000000000000000000000000";
        }
        if (str3 == null || str3.length() == 0) {
            str3 = "00000000000000000000000000000000";
        }
        if (strA2 == null || strA2.length() == 0) {
            strA2 = "00000000000000000000000000000000";
        }
        if (strA3 == null || strA3.length() == 0) {
            strA3 = "00000000000000000000000000000000";
        }
        long timeInMillis = b.b().a ? Calendar.getInstance().getTimeInMillis() : 0L;
        String strA4 = ba.a(com.ss.sys.ces.a.leviathan(iCurrentTimeMillis, ba.a(strA + str3 + strA2 + strA3)));
        long timeInMillis2 = b.b().a ? Calendar.getInstance().getTimeInMillis() : 0L;
        if (b.b().a) {
            Log.d("CZL_Efficient", "[Efficient] gorgon : " + (timeInMillis2 - timeInMillis));
        }
        map2.put("X-Gorgon", strA4);
        return map2;
    }

    public static void init_gorgon() {
        NetworkParams.setAddSecurityFactorProcessCallback(new a());
    }
}

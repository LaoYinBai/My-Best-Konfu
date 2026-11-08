package com.mbridge.msdk.out;

import android.net.Uri;
import android.text.TextUtils;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.n;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbbid.out.BidResponsed;
import com.mbridge.msdk.system.NoProGuard;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class CustomInfoManager implements NoProGuard {
    private static CustomInfoManager INSTANCE = null;
    private static String TAG = "CustomInfoManager";
    public static final int TYPE_BID = 6;
    public static final int TYPE_BIDLOAD = 7;
    public static final int TYPE_LOAD = 8;
    private ConcurrentHashMap<String, String> infoMap = new ConcurrentHashMap<>();

    private CustomInfoManager() {
    }

    public static synchronized CustomInfoManager getInstance() {
        if (INSTANCE == null) {
            synchronized (CustomInfoManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CustomInfoManager();
                }
            }
        }
        return INSTANCE;
    }

    public void setCustomInfo(String str, int i, String str2) {
        if (TextUtils.isEmpty(str) || str2 == null) {
            return;
        }
        String strA = n.a(str2);
        switch (i) {
            case 6:
                this.infoMap.put(str + "_bid", strA);
                break;
            case 7:
                this.infoMap.put(str + "_bidload", strA);
                break;
            case 8:
                this.infoMap.put(str, strA);
                break;
        }
    }

    private String getCustomInfoByUnitId(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        switch (i) {
            case 6:
                return this.infoMap.get(str + "_bid");
            case 7:
                return this.infoMap.get(str + "_bidload");
            case 8:
                return this.infoMap.get(str);
            default:
                return "";
        }
    }

    public String getCustomInfoByUnitId(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        try {
            Uri uri = Uri.parse(str2);
            if (uri == null) {
                return "";
            }
            String host = uri.getHost();
            String path = uri.getPath();
            if (!TextUtils.isEmpty(host) && host.contains(CampaignEx.JSON_KEY_HB) && !TextUtils.isEmpty(path) && path.contains(BidResponsed.KEY_BID_ID)) {
                return getCustomInfoByUnitId(str, 6);
            }
            if (TextUtils.isEmpty(host) || !host.contains(CampaignEx.JSON_KEY_HB) || TextUtils.isEmpty(path) || !path.contains("load")) {
                return (TextUtils.isEmpty(path) || !path.contains("v3")) ? "" : getCustomInfoByUnitId(str, 8);
            }
            return getCustomInfoByUnitId(str, 7);
        } catch (Throwable th) {
            s.b(TAG, "Exception", th);
            return "";
        }
    }
}

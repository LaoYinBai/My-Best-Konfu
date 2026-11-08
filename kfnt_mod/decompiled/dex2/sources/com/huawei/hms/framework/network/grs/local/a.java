package com.huawei.hms.framework.network.grs.local;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class a {
    protected com.huawei.hms.framework.network.grs.local.model.a a;
    protected List<com.huawei.hms.framework.network.grs.local.model.b> b;
    protected Map<String, String> c = new ConcurrentHashMap(16);
    protected boolean d = false;
    protected Set<String> e = new HashSet(16);

    private Map<String, String> a(List<com.huawei.hms.framework.network.grs.local.model.b> list, GrsBaseInfo grsBaseInfo) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(16);
        concurrentHashMap.put("no_route_country", "no-country");
        for (com.huawei.hms.framework.network.grs.local.model.b bVar : list) {
            if (bVar.b().contains(grsBaseInfo.getIssueCountry())) {
                concurrentHashMap.put(grsBaseInfo.getIssueCountry(), bVar.a());
            }
            if (bVar.b().contains(grsBaseInfo.getRegCountry())) {
                concurrentHashMap.put(grsBaseInfo.getRegCountry(), bVar.a());
            }
            if (bVar.b().contains(grsBaseInfo.getSerCountry())) {
                concurrentHashMap.put(grsBaseInfo.getSerCountry(), bVar.a());
            }
        }
        return concurrentHashMap;
    }

    private int d(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        int iC = c(str);
        if (iC != 0) {
            return iC;
        }
        int iA = a(str);
        return iA != 0 ? iA : b(str);
    }

    private int e(String str) {
        int iC;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        return ((this.b == null || this.b.isEmpty()) && (iC = c(str)) != 0) ? iC : b(str);
    }

    public abstract int a(String str);

    int a(String str, Context context) throws IOException {
        int i;
        if (d(com.huawei.hms.framework.network.grs.c.c.a(str, context)) == 0) {
            Logger.i("AbstractLocalManager", "load APP_CONFIG_FILE success.");
            i = 0;
        } else {
            i = -1;
        }
        try {
            String[] list = context.getAssets().list("");
            if (list == null || list.length <= 0) {
                return i;
            }
            int i2 = i;
            for (String str2 : list) {
                try {
                    if (Pattern.matches("^grs_sdk_global_route_config_[a-zA-Z]+\\.json$", str2) && e(com.huawei.hms.framework.network.grs.c.c.a(str2, context)) == 0) {
                        Logger.i("AbstractLocalManager", "load SDK_CONFIG_FILE sucess.");
                        i2 = 0;
                    }
                } catch (IOException unused) {
                    i = i2;
                    Logger.w("AbstractLocalManager", "list assets files fail,please check if according to our standard config json files.");
                    return i;
                }
            }
            return i2;
        } catch (IOException unused2) {
        }
    }

    public com.huawei.hms.framework.network.grs.local.model.a a() {
        return this.a;
    }

    public String a(GrsBaseInfo grsBaseInfo, String str, String str2) {
        Map<String, String> mapA = a(grsBaseInfo, str);
        if (mapA != null) {
            return mapA.get(str2);
        }
        Logger.w("AbstractLocalManager", "addresses not found by routeby in local config{%s}", str);
        return null;
    }

    public Map<String, String> a(GrsBaseInfo grsBaseInfo, String str) {
        if (!this.d) {
            return null;
        }
        com.huawei.hms.framework.network.grs.local.model.c cVarB = this.a.b(str);
        if (cVarB == null) {
            Logger.w("AbstractLocalManager", "service not found in local config{%s}", str);
            return null;
        }
        String strA = e.a(cVarB.b(), grsBaseInfo);
        if (strA == null) {
            Logger.w("AbstractLocalManager", "country not found by routeby in local config{%s}", cVarB.b());
            return null;
        }
        List<com.huawei.hms.framework.network.grs.local.model.b> listC = cVarB.c();
        com.huawei.hms.framework.network.grs.local.model.d dVarB = cVarB.b(((listC == null || listC.size() == 0) ? this.c : a(listC, grsBaseInfo)).get(strA));
        if (dVarB == null) {
            return null;
        }
        return dVarB.b();
    }

    public boolean a(GrsBaseInfo grsBaseInfo) {
        if (this.b == null || this.b.isEmpty()) {
            return false;
        }
        this.c.put("no_route_country", "no-country");
        for (com.huawei.hms.framework.network.grs.local.model.b bVar : this.b) {
            if (bVar.b().contains(grsBaseInfo.getIssueCountry())) {
                this.c.put(grsBaseInfo.getIssueCountry(), bVar.a());
            }
            if (bVar.b().contains(grsBaseInfo.getRegCountry())) {
                this.c.put(grsBaseInfo.getRegCountry(), bVar.a());
            }
            if (bVar.b().contains(grsBaseInfo.getSerCountry())) {
                this.c.put(grsBaseInfo.getSerCountry(), bVar.a());
            }
        }
        return true;
    }

    public abstract int b(String str);

    public boolean b() {
        return this.d;
    }

    public abstract int c(String str);
}

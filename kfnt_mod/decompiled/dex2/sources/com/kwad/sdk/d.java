package com.kwad.sdk;

import android.text.TextUtils;
import com.kwad.sdk.plugin.DevelopMangerPlugin;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d {
    public static String a = "kwrules.csv";
    public static String b = "";
    public static String c = "adEcDistribution";

    public static class a {
        public static String a() {
            return "/rest/sdk/common/appDownLoadInfo";
        }
    }

    public static String a() {
        DevelopMangerPlugin.DevelopValue developValueA = ((DevelopMangerPlugin) com.kwad.sdk.plugin.f.a(DevelopMangerPlugin.class)).a("KEY_HOST_API");
        if (developValueA == null) {
            return "https://open.e.kuaishou.com";
        }
        String string = developValueA.getValue().toString();
        return !TextUtils.isEmpty(string) ? string : "https://open.e.kuaishou.com";
    }

    public static String b() {
        DevelopMangerPlugin.DevelopValue developValueA = ((DevelopMangerPlugin) com.kwad.sdk.plugin.f.a(DevelopMangerPlugin.class)).a("KEY_HOST_EC_SSP");
        if (developValueA == null) {
            return "https://s.e.kuaishou.com";
        }
        String string = developValueA.getValue().toString();
        return !TextUtils.isEmpty(string) ? string : "https://s.e.kuaishou.com";
    }

    public static String c() {
        return a() + "/rest/e/v3/open/univ";
    }

    public static String d() {
        return a() + "/rest/e/v3/open/logBatch";
    }

    public static String e() {
        return a() + "/rest/e/v3/open/config";
    }

    public static String f() {
        return a() + "/rest/e/v3/open/callback";
    }

    public static String g() {
        return a() + "/rest/e/v3/open/mediaPlayerLog";
    }

    public static String h() {
        return "/rest/sdk/log/upload";
    }

    public static String i() {
        return a() + "/rest/e/v3/open/crashLog";
    }

    public static String j() {
        return a() + "/rest/e/v3/open/sdk/js";
    }

    public static String k() {
        return a() + "/rest/e/v3/open/sdk/js2";
    }

    public static String l() {
        return a() + "/rest/e/v3/open/appCheck";
    }

    public static String m() {
        return a() + "/rest/e/v3/open/collect";
    }
}

package com.mbridge.msdk.b;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.db.r;
import com.mbridge.msdk.foundation.download.download.H5DownLoadManager;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.o;
import com.mbridge.msdk.foundation.tools.n;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.y;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SettingManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    public static final String a = "b";
    private static b b;
    private static HashMap<String, d> c = new HashMap<>();
    private static a d = null;

    private b() {
    }

    public static b a() {
        if (b == null) {
            synchronized (b.class) {
                if (b == null) {
                    b = new b();
                }
            }
        }
        return b;
    }

    public final boolean a(String str) {
        a aVarB = b(str);
        if (aVarB != null) {
            long jP = aVarB.P() * 1000;
            long jCurrentTimeMillis = System.currentTimeMillis();
            long jS = aVarB.S() + jP;
            if (jS > jCurrentTimeMillis) {
                s.b(a, "app setting nexttime is not ready  [settingNextRequestTime= " + jS + " currentTime = " + jCurrentTimeMillis + "]");
                return false;
            }
        }
        s.b(a, "app setting timeout or not exists");
        return true;
    }

    public final boolean a(String str, int i, String str2) {
        try {
            Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
            String str3 = str + "_" + i + "_" + str2;
            long jCurrentTimeMillis = System.currentTimeMillis();
            long j = 0;
            long jLongValue = ((Long) y.b(contextJ, str3, 0L)).longValue();
            a aVarB = b(str);
            if (aVarB == null) {
                aVarB = a().b();
            } else {
                j = jLongValue;
            }
            if (j + (aVarB.D() * 1000) > jCurrentTimeMillis) {
                return false;
            }
            y.a(contextJ, str3, Long.valueOf(jCurrentTimeMillis));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public final boolean a(String str, String str2) {
        a aVarB = b(str2);
        long jH = h(str2, str);
        if (aVarB != null) {
            long jM = aVarB.M() * 1000;
            long jCurrentTimeMillis = System.currentTimeMillis();
            long j = jH + jM;
            if (j > jCurrentTimeMillis) {
                s.b(a, "app WallSetting nexttime is not ready  [settingNextRequestTime= " + j + " currentTime = " + jCurrentTimeMillis + "]");
                return false;
            }
        }
        s.b(a, "app WallSetting timeout or not exists");
        return true;
    }

    private long h(String str, String str2) {
        List<String> listB;
        String str3 = "wall_style_" + str + "_" + str2;
        if (TextUtils.isEmpty(str2) && (listB = com.mbridge.msdk.foundation.a.a.a.a().b()) != null && listB.size() > 0) {
            int i = 0;
            while (true) {
                if (i >= listB.size()) {
                    break;
                }
                String str4 = listB.get(i);
                if (str4.contains(str3.replace("null", ""))) {
                    str3 = str4;
                    break;
                }
                i++;
            }
        }
        String strB = com.mbridge.msdk.foundation.a.a.a.a().b(str3);
        try {
            if (TextUtils.isEmpty(strB)) {
                return 0L;
            }
            long jOptLong = new JSONObject(strB).optLong("current_time");
            s.b(a, "lastGetTime" + jOptLong);
            return jOptLong;
        } catch (JSONException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public final boolean b(String str, String str2) {
        a aVarB = b(str2);
        if (a(str2) && a(str2, 1, str)) {
            new c().a(com.mbridge.msdk.foundation.controller.a.f().j(), str2, com.mbridge.msdk.foundation.controller.a.f().l());
        }
        d dVarF = f(str2, str);
        if (aVarB != null && dVarF != null) {
            long J = aVarB.J() * 1000;
            long jCurrentTimeMillis = System.currentTimeMillis();
            long jW = dVarF.w() + J;
            if (jW > jCurrentTimeMillis) {
                s.b(a, "unit setting  nexttime is not ready  [settingNextRequestTime= " + jW + " currentTime = " + jCurrentTimeMillis + "]");
                return false;
            }
        }
        s.b(a, "unit setting timeout or not exists");
        return true;
    }

    public final void c(String str, String str2) throws PackageManager.NameNotFoundException {
        com.mbridge.msdk.foundation.a.a.a.a().a(str, str2);
        d = a.e(str2);
        if (d != null) {
            d.ab();
        }
        a aVar = d;
        try {
            Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
            PackageManager packageManager = contextJ != null ? contextJ.getPackageManager() : null;
            if (aVar == null || packageManager == null) {
                return;
            }
            if (aVar.p() > 0) {
                List<CampaignEx> listR = aVar.r();
                if (listR == null || listR.size() <= 0 || aVar.s() != 1) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < listR.size(); i++) {
                    CampaignEx campaignEx = listR.get(i);
                    String packageName = campaignEx.getPackageName();
                    try {
                        PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
                        String installerPackageName = packageManager.getInstallerPackageName(packageName);
                        campaignEx.setPackageName("");
                        campaignEx.setIex(1);
                        campaignEx.setTs(packageInfo.lastUpdateTime);
                        campaignEx.setLabel("is");
                        campaignEx.setPkgSource(installerPackageName);
                        campaignEx.setcVersionCode(packageInfo.versionCode);
                    } catch (PackageManager.NameNotFoundException unused) {
                        campaignEx.setPackageName("");
                        campaignEx.setIex(1);
                        campaignEx.setTs(0L);
                        campaignEx.setLabel("ni");
                        campaignEx.setPkgSource("");
                    }
                    arrayList.add(campaignEx);
                }
                a(arrayList, 1);
                return;
            }
            r.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j())).b();
        } catch (Exception e) {
            if (MBridgeConstans.DEBUG) {
                e.printStackTrace();
            }
        }
    }

    private o a(List<CampaignEx> list, int i) {
        o oVar = new o();
        try {
            oVar.b(b(list, i));
            oVar.a(i + "");
            if (r.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j())).a() > 500) {
                r.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j())).b(PushConstants.PUSH_TYPE_NOTIFY);
            }
            r.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j())).a(oVar);
        } catch (Exception unused) {
        }
        return oVar;
    }

    private String b(List<CampaignEx> list, int i) {
        String str = "key=2000079&iex=" + i + "&cal=";
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator<CampaignEx> it = list.iterator();
            while (it.hasNext()) {
                jSONArray.put(a(it.next()));
            }
            return str + n.a(jSONArray.toString());
        } catch (Exception e) {
            if (MBridgeConstans.DEBUG) {
                e.printStackTrace();
            }
            return str;
        }
    }

    private JSONObject a(CampaignEx campaignEx) throws JSONException {
        JSONObject jSONObject;
        if (campaignEx == null) {
            return null;
        }
        try {
            jSONObject = new JSONObject();
        } catch (Exception e) {
            e = e;
            jSONObject = null;
        }
        try {
            jSONObject.put("campaignid", campaignEx.getId());
            jSONObject.put("ts", campaignEx.getTs());
            jSONObject.put("label", campaignEx.getLabel());
            jSONObject.put("vc", campaignEx.getcVersionCode());
            String pkgSource = campaignEx.getPkgSource();
            if (pkgSource == null) {
                pkgSource = "";
            }
            jSONObject.put("pkg_source", pkgSource);
            return jSONObject;
        } catch (Exception e2) {
            e = e2;
            if (!MBridgeConstans.DEBUG) {
                return jSONObject;
            }
            e.printStackTrace();
            return jSONObject;
        }
    }

    public final a b(String str) {
        if (d == null) {
            try {
                d = a.e(com.mbridge.msdk.foundation.a.a.a.a().b(str));
                if (d != null) {
                    d.ab();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return d;
    }

    public final String c(String str) {
        if (str == null) {
            return "";
        }
        try {
            String strB = com.mbridge.msdk.foundation.a.a.a.a().b(str);
            return strB == null ? "" : strB;
        } catch (Exception e) {
            if (!MBridgeConstans.DEBUG) {
                return "";
            }
            e.printStackTrace();
            return "";
        }
    }

    public static void a(Context context, String str) {
        try {
            Map<String, ?> all = context.getSharedPreferences("mbridge", 0).getAll();
            for (String str2 : all.keySet()) {
                if (str2.startsWith(str + "_")) {
                    c.put(str2, d.b((String) all.get(str2)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final d d(String str, String str2) {
        d dVarF = f(str, str2);
        return dVarF == null ? d.n() : dVarF;
    }

    public final d f(String str, String str2) {
        d dVarI = i(str, str2);
        if (dVarI != null && dVarI.k() == 0) {
            dVarI.c(1);
        }
        return dVarI;
    }

    private d i(String str, String str2) {
        d dVar;
        d dVarB;
        if (TextUtils.isEmpty(str)) {
            str = com.mbridge.msdk.foundation.controller.a.f().k();
        }
        String str3 = str + "_" + str2;
        d dVar2 = null;
        if (c.containsKey(str3)) {
            dVar = c.get(str3);
        } else {
            try {
                dVarB = d.b(com.mbridge.msdk.foundation.a.a.a.a().b(str3));
            } catch (Exception e) {
                e = e;
            }
            try {
                c.put(str3, dVarB);
                dVar = dVarB;
            } catch (Exception e2) {
                e = e2;
                dVar2 = dVarB;
                e.printStackTrace();
                dVar = dVar2;
                if (dVar != null) {
                    j(str2, d.a);
                }
                return dVar;
            }
        }
        if (dVar != null && !TextUtils.isEmpty(d.a)) {
            j(str2, d.a);
        }
        return dVar;
    }

    public final d g(String str, String str2) {
        return i(str, str2);
    }

    public final void a(String str, String str2, String str3) {
        String str4 = str + "_" + str2;
        com.mbridge.msdk.foundation.a.a.a.a().a(str4, str3);
        c.put(str4, d.b(str3));
        j(str2, d.a);
    }

    private void j(String str, String str2) {
        if (TextUtils.isEmpty(str2) || !TextUtils.isEmpty(H5DownLoadManager.getInstance().getH5ResAddress(str2))) {
            return;
        }
        k(str2, str);
    }

    private void k(final String str, final String str2) {
        if (TextUtils.isEmpty(str)) {
            com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j(), str, str2, 2, "alert url is exception ,url:" + str);
            return;
        }
        H5DownLoadManager.getInstance().downloadH5Res(str, new H5DownLoadManager.ZipDownloadListener() { // from class: com.mbridge.msdk.b.b.1
            @Override // com.mbridge.msdk.foundation.download.download.H5DownLoadManager.H5ResDownloadListerInter
            public final void onSuccess(String str3) {
                com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j(), str, str2, 1, "");
            }

            @Override // com.mbridge.msdk.foundation.download.download.H5DownLoadManager.H5ResDownloadListerInter
            public final void onFailed(String str3, String str4) {
                com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j(), str, str2, 2, str4);
            }
        });
    }

    public final String d(String str) {
        return com.mbridge.msdk.foundation.a.a.a.a().b("ivreward_" + str);
    }

    public final void e(String str) {
        com.mbridge.msdk.foundation.a.a.a.a().c("ivreward_" + str);
    }

    public final a b() {
        a aVar = new a();
        aVar.h(86400L);
        aVar.G(1);
        aVar.H(1);
        aVar.c(true);
        aVar.d(true);
        aVar.f(3600L);
        aVar.g(0L);
        aVar.b(false);
        aVar.a(false);
        aVar.b(7200L);
        aVar.d(20L);
        aVar.e(10L);
        aVar.A(1);
        aVar.B(1);
        aVar.d("正在下载中，请去通知栏查看下载进度");
        aVar.b("mbridge");
        aVar.m(1);
        aVar.c(1800L);
        aVar.z(100);
        aVar.y(43200);
        aVar.x(1);
        aVar.a(604800L);
        aVar.F(1);
        aVar.C(3);
        aVar.n(1);
        aVar.w(1);
        aVar.u(1);
        aVar.v(RemoteMessageConst.DEFAULT_TTL);
        aVar.D(259200);
        aVar.o(1);
        aVar.p(900);
        aVar.q(20);
        aVar.c("pid");
        aVar.r(1);
        aVar.s(1);
        aVar.t(1);
        aVar.E(1);
        aVar.h(1);
        aVar.g(1);
        aVar.i(1);
        aVar.j(0);
        aVar.k(1);
        aVar.l(-1);
        aVar.a(com.mbridge.msdk.b.a.b.a);
        aVar.f(2);
        aVar.e(RemoteMessageConst.DEFAULT_TTL);
        aVar.c(0);
        aVar.f("https://cdn-adn-https.rayjump.com/cdn-adn/v2/portal/19/08/20/11/06/5d5b63cb457e2.js");
        aVar.b(120);
        aVar.a(10);
        aVar.e(false);
        aVar.I(0);
        aVar.J(120);
        aVar.K(1);
        return aVar;
    }

    public final d e(String str, String str2) {
        d dVarI = i(str, str2);
        return dVarI == null ? d.n() : dVarI;
    }
}

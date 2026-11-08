package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.os.Build;
import android.text.TextUtils;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.bytedance.msdk.adapter.config.DefaultAdapterClasses;
import com.bytedance.msdk.adapter.config.ITTAdapterConfiguration;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.api.v2.GMMediationAdSdk;
import com.bytedance.msdk.api.v2.GMSettingConfigCallback;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static final GMSettingConfigCallback f1969a = new C0067a();

    /* renamed from: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.a$a, reason: collision with other inner class name */
    static class C0067a implements GMSettingConfigCallback {
        C0067a() {
        }

        @Override // com.bytedance.msdk.api.v2.GMSettingConfigCallback
        public void configLoad() {
            try {
                a.e();
            } finally {
                try {
                } finally {
                }
            }
        }
    }

    public static void c() {
        try {
            if (Logger.isDebug() && GMMediationAdSdk.configLoadSuccess()) {
                e();
            }
        } catch (Throwable unused) {
        }
    }

    private static Map<String, String> d() {
        HashMap map = new HashMap();
        map.put("pangle", "");
        map.put("gdt", "");
        map.put("admob", "");
        map.put("unity", "");
        map.put("baidu", "");
        map.put("sigmob", "");
        map.put("klevin", "");
        map.put("mintegral", "");
        map.put("ks", "");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e() {
        Map<String, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.a> mapC;
        List<String> listA;
        Context contextD = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d();
        if (contextD == null || (mapC = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().c()) == null) {
            return;
        }
        int i = contextD.getApplicationInfo().targetSdkVersion;
        if ((Build.VERSION.SDK_INT >= 24 || i >= 24) && (listA = a(contextD)) != null) {
            for (String str : mapC.keySet()) {
                if (!"unity".equals(str)) {
                    if (listA.contains(str)) {
                        Logger.d("TTMediationSDK_InitChecker", "已按要求接入三方广告sdk【" + str + "】");
                    } else {
                        Logger.e("TTMediationSDK_InitChecker", "未按要求接入三方广告sdk【" + str + "】，请检查接入配置");
                    }
                }
            }
        }
        Logger.d("TTMediationSDK_InitChecker", "当前接入的GroMore SDK版本是：3.1.0.1");
        Map<String, ITTAdapterConfiguration> mapE = c.e();
        Map<String, String> mapD = d();
        if (mapE == null || mapD == null) {
            return;
        }
        for (String str2 : mapD.keySet()) {
            try {
                ITTAdapterConfiguration iTTAdapterConfiguration = mapE.get(DefaultAdapterClasses.getClassNameByAdnName(str2));
                String str3 = mapD.get(str2);
                if (iTTAdapterConfiguration != null && str3 != null) {
                    iTTAdapterConfiguration.checkVersion();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private static int a(String str, String str2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return 0;
        }
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (TextUtils.isEmpty(str2)) {
            return 1;
        }
        if (str.startsWith(DispatchConstants.VERSION) || str.startsWith("V")) {
            str = str.substring(1);
        }
        if (str2.startsWith(DispatchConstants.VERSION) || str2.startsWith("V")) {
            str2 = str2.substring(1);
        }
        String[] strArrSplit = str.split("\\.");
        String[] strArrSplit2 = str2.split("\\.");
        int iMin = Math.min(strArrSplit.length, strArrSplit2.length);
        for (int i = 0; i < iMin; i++) {
            if (strArrSplit[i].length() != strArrSplit2[i].length()) {
                return strArrSplit[i].length() > strArrSplit2[i].length() ? 1 : -1;
            }
            int iCompareTo = strArrSplit[i].compareTo(strArrSplit2[i]);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        if (strArrSplit.length == strArrSplit2.length) {
            return 0;
        }
        return strArrSplit.length > strArrSplit2.length ? 1 : -1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0072  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean b(java.lang.String r4, java.lang.String r5) {
        /*
            Method dump skipped, instructions count: 750
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.a.b(java.lang.String, java.lang.String):boolean");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0071  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean c(java.lang.String r4, java.lang.String r5) {
        /*
            Method dump skipped, instructions count: 676
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.a.c(java.lang.String, java.lang.String):boolean");
    }

    private static List<String> a(Context context) {
        List<ProviderInfo> listQueryContentProviders = context.getPackageManager().queryContentProviders(context.getApplicationInfo().processName, context.getApplicationInfo().uid, 131072);
        if (listQueryContentProviders == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        context.getPackageName();
        Iterator<ProviderInfo> it = listQueryContentProviders.iterator();
        while (it.hasNext()) {
            switch (it.next().name) {
                case "com.bytedance.sdk.openadsdk.TTFileProvider":
                    arrayList.add("pangle");
                    break;
                case "com.qq.e.comm.GDTFileProvider":
                    arrayList.add("gdt");
                    break;
                case "com.baidu.mobads.sdk.api.BdFileProvider":
                    arrayList.add("baidu");
                    break;
                case "com.sigmob.sdk.SigmobFileProvider":
                    arrayList.add("sigmob");
                    break;
                case "com.tencent.klevin.utils.FileProvider":
                    arrayList.add("klevin");
                    break;
                case "com.google.android.gms.ads.MobileAdsInitProvider":
                    arrayList.add("admob");
                    break;
                case "com.kwad.sdk.api.proxy.app.AdSdkFileProvider":
                    arrayList.add("ks");
                    break;
                case "com.mbridge.msdk.foundation.tools.MBFileProvider":
                    arrayList.add("mintegral");
                    break;
                case "com.unity":
                    arrayList.add("unity");
                    break;
            }
        }
        return arrayList;
    }
}

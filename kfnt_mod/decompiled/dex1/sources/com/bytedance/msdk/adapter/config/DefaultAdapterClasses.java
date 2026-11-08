package com.bytedance.msdk.adapter.config;

import android.text.TextUtils;
import com.bytedance.msdk.adapter.util.Logger;
import java.util.HashSet;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class DefaultAdapterClasses {

    /* renamed from: a, reason: collision with root package name */
    private static final Set<String> f3966a = new HashSet();

    static {
        f3966a.add("com.bytedance.msdk.adapter.pangle.PangleAdapterConfiguration");
        f3966a.add("com.bytedance.msdk.adapter.unity.UnityAdapterConfiguration");
        f3966a.add("com.bytedance.msdk.adapter.baidu.BaiduAdapterConfiguration");
        f3966a.add("com.bytedance.msdk.adapter.sigmob.SigmobAdapterConfiguration");
        f3966a.add("com.bytedance.msdk.adapter.admob.AdmobAdapterConfiguration");
        f3966a.add("com.bytedance.msdk.adapter.gdt.GdtAdapterConfiguration");
        f3966a.add("com.bytedance.msdk.adapter.ks.KsAdapterConfiguration");
        f3966a.add("com.bytedance.msdk.adapter.mintegral.MintegralAdapterConfiguration");
        f3966a.add("com.bytedance.msdk.adapter.klevin.KlevinAdapterConfiguration");
    }

    public static Class<?> getClass(String str) {
        String classNameByAdnName = getClassNameByAdnName(str);
        if (!TextUtils.isEmpty(classNameByAdnName)) {
            try {
                return Class.forName(classNameByAdnName);
            } catch (Throwable th) {
                th.printStackTrace();
                Logger.e("TTMediationSDK_ADAPTER", "DefaultAdapterClasses#getClass error:" + th.toString());
            }
        }
        return null;
    }

    public static String getClassNameByAdnName(String str) {
        return "pangle".equals(str) ? "com.bytedance.msdk.adapter.pangle.PangleAdapterConfiguration" : "unity".equals(str) ? "com.bytedance.msdk.adapter.unity.UnityAdapterConfiguration" : "baidu".equals(str) ? "com.bytedance.msdk.adapter.baidu.BaiduAdapterConfiguration" : "admob".equals(str) ? "com.bytedance.msdk.adapter.admob.AdmobAdapterConfiguration" : "gdt".equals(str) ? "com.bytedance.msdk.adapter.gdt.GdtAdapterConfiguration" : "ks".equals(str) ? "com.bytedance.msdk.adapter.ks.KsAdapterConfiguration" : "sigmob".equals(str) ? "com.bytedance.msdk.adapter.sigmob.SigmobAdapterConfiguration" : "mintegral".equals(str) ? "com.bytedance.msdk.adapter.mintegral.MintegralAdapterConfiguration" : "klevin".equals(str) ? "com.bytedance.msdk.adapter.klevin.KlevinAdapterConfiguration" : "";
    }

    public static Set<String> getClassNamesSet() {
        return f3966a;
    }
}

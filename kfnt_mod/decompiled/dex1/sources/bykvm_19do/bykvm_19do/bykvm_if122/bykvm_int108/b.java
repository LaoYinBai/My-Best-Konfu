package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108;

import android.content.Context;
import android.text.TextUtils;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.ThreadHelper;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdSlot;
import com.bytedance.msdk.api.v2.ad.custom.bean.GMCustomInitConfig;
import com.bytedance.msdk.api.v2.ad.custom.init.GMCustomAdapterConfiguration;
import com.bytedance.msdk.base.TTBaseAd;
import java.lang.reflect.Method;
import java.util.HashSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ GMCustomAdapterConfiguration f1970a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f1971b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ GMCustomInitConfig f1972c;

        a(GMCustomAdapterConfiguration gMCustomAdapterConfiguration, Context context, GMCustomInitConfig gMCustomInitConfig) {
            this.f1970a = gMCustomAdapterConfiguration;
            this.f1971b = context;
            this.f1972c = gMCustomInitConfig;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f1970a.initializeInnerADN(this.f1971b, this.f1972c, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().g());
            } catch (Exception e) {
                g.a(this.f1972c.getADNName(), (TTBaseAd) null, (AdSlot) null, (i) null, new AdError(AdError.ERROR_CODE_CUSTOM_INIT, "调用自定义Adapter初始化方法出现异常 " + e.getClass().getName()));
                e.printStackTrace();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:74:0x011c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0018 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(android.content.Context r9) {
        /*
            Method dump skipped, instructions count: 436
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.b.a(android.content.Context):void");
    }

    public static String b(Class<?> cls, Class<?> cls2) {
        for (Method method : cls2.getMethods()) {
            HashSet hashSet = new HashSet();
            for (Method method2 : Object.class.getMethods()) {
                hashSet.add(method2.getName());
            }
            if (!hashSet.contains(method.getName())) {
                try {
                    cls.getMethod(method.getName(), method.getParameterTypes());
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                    return "自定义ADN校验API失败接入API不符合版本需求，不符合的API的方法名为 " + method.getName();
                }
            }
        }
        return "";
    }

    private static void a(Context context, GMCustomInitConfig gMCustomInitConfig, GMCustomAdapterConfiguration gMCustomAdapterConfiguration) {
        if (context == null) {
            g.a(gMCustomInitConfig.getADNName(), (TTBaseAd) null, (AdSlot) null, (i) null, new AdError(AdError.ERROR_CODE_CUSTOM_INIT, "context为null"));
            return;
        }
        String strA = a(gMCustomAdapterConfiguration);
        if (!TextUtils.isEmpty(strA)) {
            g.a(gMCustomInitConfig.getADNName(), (TTBaseAd) null, (AdSlot) null, (i) null, new AdError(AdError.ERROR_CODE_CUSTOM_INIT, strA));
        } else {
            ThreadHelper.runOnThreadPool(new a(gMCustomAdapterConfiguration, context.getApplicationContext(), gMCustomInitConfig));
        }
    }

    private static boolean a(Class<?> cls) {
        String strB;
        if (!a(cls, GMCustomAdapterConfiguration.class)) {
            strB = "自定义ADN初始化失败  ---------  初始化类 " + cls.getSimpleName() + " 需要继承自自定义ADN的初始化Adapter类 " + GMCustomAdapterConfiguration.class.getSimpleName();
        } else {
            strB = b(cls, GMCustomAdapterConfiguration.class);
        }
        if (TextUtils.isEmpty(strB)) {
            return true;
        }
        Logger.e("TTMediationSDK_SDK_Init", strB);
        g.a("", (TTBaseAd) null, (AdSlot) null, (i) null, new AdError(AdError.ERROR_CODE_CUSTOM_INIT, strB));
        return false;
    }

    public static boolean a(Class<?> cls, Class<?> cls2) {
        for (Class<? super Object> superclass = cls.getSuperclass(); superclass != null; superclass = superclass.getSuperclass()) {
            if (superclass == cls2) {
                return true;
            }
        }
        return false;
    }

    private static String a(GMCustomAdapterConfiguration gMCustomAdapterConfiguration) {
        return gMCustomAdapterConfiguration == null ? "customAdapterConfiguration 为null" : TextUtils.isEmpty(gMCustomAdapterConfiguration.getNetworkSdkVersion()) ? "自定义ADN 初始化失败 getNetworkSdkVersion()返字符串不能为空" : TextUtils.isEmpty(gMCustomAdapterConfiguration.getAdapterSdkVersion()) ? "自定义ADN 初始化失败 getAdapterSdkVersion()返字符串不能为空" : "";
    }
}

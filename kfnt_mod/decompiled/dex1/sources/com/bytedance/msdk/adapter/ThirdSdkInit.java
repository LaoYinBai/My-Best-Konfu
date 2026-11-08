package com.bytedance.msdk.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.a;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.c;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.n;
import com.bytedance.msdk.adapter.config.DefaultAdapterClasses;
import com.bytedance.msdk.adapter.config.ITTAdapterConfiguration;
import com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration;
import com.bytedance.msdk.adapter.config.TTOnNetworkInitializationFinishedListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.api.AdError;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ThirdSdkInit {

    public interface InitCallback {
        void fail(int i, String str);

        void success();
    }

    public static String getTTPangleAppId() {
        a aVarA = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().a("pangle");
        String strA = aVarA != null ? aVarA.a() : null;
        return TextUtils.isEmpty(strA) ? b.D().c() : strA;
    }

    public static void initTTBaiduSDK(Context context, String str) {
        Map<String, ITTAdapterConfiguration> mapE;
        ITTAdapterConfiguration iTTAdapterConfiguration;
        if (context == null) {
            return;
        }
        try {
            if (TextUtils.isEmpty(str) || (mapE = c.e()) == null || (iTTAdapterConfiguration = mapE.get(DefaultAdapterClasses.getClassNameByAdnName("baidu"))) == null) {
                return;
            }
            HashMap map = new HashMap();
            map.put("app_id", str);
            map.put(TTBaseAdapterConfiguration.BAIDU_HTTPS, Boolean.valueOf(b.D().w()));
            iTTAdapterConfiguration.initializeNetwork(context, map, new TTOnNetworkInitializationFinishedListener() { // from class: com.bytedance.msdk.adapter.ThirdSdkInit.4
                @Override // com.bytedance.msdk.adapter.config.TTOnNetworkInitializationFinishedListener
                public void onNetworkInitializationFinished(@NonNull Class<? extends ITTAdapterConfiguration> cls, @NonNull AdError adError) {
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
            Logger.e("TTMediationSDK_SDK_Init", "BAIDU SDK 初始化失败。。 e=" + th.toString());
        }
    }

    public static void initTTGDTSDK(Context context, String str) {
        Map<String, ITTAdapterConfiguration> mapE;
        ITTAdapterConfiguration iTTAdapterConfiguration;
        if (context == null) {
            return;
        }
        try {
            if (TextUtils.isEmpty(str) || (mapE = c.e()) == null || (iTTAdapterConfiguration = mapE.get(DefaultAdapterClasses.getClassNameByAdnName("gdt"))) == null) {
                return;
            }
            HashMap map = new HashMap();
            map.put("app_id", str);
            iTTAdapterConfiguration.initializeNetwork(context, map, new TTOnNetworkInitializationFinishedListener() { // from class: com.bytedance.msdk.adapter.ThirdSdkInit.2
                @Override // com.bytedance.msdk.adapter.config.TTOnNetworkInitializationFinishedListener
                public void onNetworkInitializationFinished(@NonNull Class<? extends ITTAdapterConfiguration> cls, @NonNull AdError adError) {
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
            Logger.e("TTMediationSDK_SDK_Init", "GDT SDK 初始化失败。。 e=" + th.toString());
        }
    }

    public static void initTTKSSDK(Context context, String str) {
        Map<String, ITTAdapterConfiguration> mapE;
        ITTAdapterConfiguration iTTAdapterConfiguration;
        if (context == null) {
            return;
        }
        try {
            if (TextUtils.isEmpty(str) || (mapE = c.e()) == null || (iTTAdapterConfiguration = mapE.get(DefaultAdapterClasses.getClassNameByAdnName("ks"))) == null) {
                return;
            }
            HashMap map = new HashMap();
            map.put("app_id", str);
            map.put(TTBaseAdapterConfiguration.APP_NAME_EXTRA_KEY, b.D().h());
            iTTAdapterConfiguration.initializeNetwork(context, map, new TTOnNetworkInitializationFinishedListener() { // from class: com.bytedance.msdk.adapter.ThirdSdkInit.3
                @Override // com.bytedance.msdk.adapter.config.TTOnNetworkInitializationFinishedListener
                public void onNetworkInitializationFinished(@NonNull Class<? extends ITTAdapterConfiguration> cls, @NonNull AdError adError) {
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
            Logger.e("TTMediationSDK_SDK_Init", "KS SDK 初始化失败。。 e=" + th.toString());
        }
    }

    public static void initTTKlevinSDK(Context context, String str) {
        Map<String, ITTAdapterConfiguration> mapE;
        ITTAdapterConfiguration iTTAdapterConfiguration;
        if (context == null) {
            return;
        }
        try {
            if (TextUtils.isEmpty(str) || (mapE = c.e()) == null || (iTTAdapterConfiguration = mapE.get(DefaultAdapterClasses.getClassNameByAdnName("klevin"))) == null) {
                return;
            }
            HashMap map = new HashMap();
            map.put("app_id", str);
            iTTAdapterConfiguration.initializeNetwork(context, map, new TTOnNetworkInitializationFinishedListener() { // from class: com.bytedance.msdk.adapter.ThirdSdkInit.7
                @Override // com.bytedance.msdk.adapter.config.TTOnNetworkInitializationFinishedListener
                public void onNetworkInitializationFinished(@NonNull Class<? extends ITTAdapterConfiguration> cls, @NonNull AdError adError) {
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
            Logger.e("TTMediationSDK_SDK_Init", "klevin SDK 初始化失败。。 e=" + th.toString());
        }
    }

    public static void initTTMintergralSDK(Context context, String str, String str2) {
        Map<String, ITTAdapterConfiguration> mapE;
        ITTAdapterConfiguration iTTAdapterConfiguration;
        if (context == null) {
            return;
        }
        try {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (mapE = c.e()) == null || (iTTAdapterConfiguration = mapE.get(DefaultAdapterClasses.getClassNameByAdnName("mintegral"))) == null) {
                return;
            }
            HashMap map = new HashMap();
            map.put("app_id", str);
            map.put("app_key", str2);
            iTTAdapterConfiguration.initializeNetwork(context, map, new TTOnNetworkInitializationFinishedListener() { // from class: com.bytedance.msdk.adapter.ThirdSdkInit.5
                @Override // com.bytedance.msdk.adapter.config.TTOnNetworkInitializationFinishedListener
                public void onNetworkInitializationFinished(@NonNull Class<? extends ITTAdapterConfiguration> cls, @NonNull AdError adError) {
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
            Logger.e("TTMediationSDK_SDK_Init", "Mintegral SDK 初始化失败。。 e=" + th.toString());
        }
    }

    public static void initTTPangleSDK(Context context) {
        Map<String, ITTAdapterConfiguration> mapE;
        ITTAdapterConfiguration iTTAdapterConfiguration;
        if (context == null) {
            return;
        }
        try {
            if (TextUtils.isEmpty(getTTPangleAppId()) || (mapE = c.e()) == null || (iTTAdapterConfiguration = mapE.get(DefaultAdapterClasses.getClassNameByAdnName("pangle"))) == null) {
                return;
            }
            HashMap map = new HashMap();
            map.put("app_id", getTTPangleAppId());
            map.put(TTBaseAdapterConfiguration.PANGLE_IS_PANGLE_USE_TEXTURE_VIEW, Boolean.valueOf(b.D().B()));
            map.put(TTBaseAdapterConfiguration.PANGLE_APP_NAME, b.D().h());
            map.put(TTBaseAdapterConfiguration.PANGLE_IS_PANGLE_PAID, Boolean.valueOf(b.D().A()));
            map.put(TTBaseAdapterConfiguration.PANGLE_TITLE_BAR_THEME, Integer.valueOf(b.D().n()));
            map.put(TTBaseAdapterConfiguration.PANGLE_IS_PANGLE_ALLOW_SHOW_NOTIFY, Boolean.valueOf(b.D().y()));
            map.put(TTBaseAdapterConfiguration.PANGLE_IS_PANGLE_ALLOW_SHOW_PAGE_WHEN_SCREEN_LOCK, Boolean.valueOf(b.D().z()));
            map.put(TTBaseAdapterConfiguration.PANGLE_DIRECT_DOWNLOAD_NETWORKTYPE, b.D().o());
            map.put(TTBaseAdapterConfiguration.PANGLE_DATA, b.D().j());
            map.put(TTBaseAdapterConfiguration.PANGLE_NEED_CLEAR_TASK_RESET, b.D().l());
            map.put(TTBaseAdapterConfiguration.PANGLE_KEYWORDS, b.D().k());
            map.put(TTBaseAdapterConfiguration.PANGLE_PLUGIN_UPDATE_CONFIG, Integer.valueOf(b.D().m()));
            iTTAdapterConfiguration.initializeNetwork(context, map, new TTOnNetworkInitializationFinishedListener() { // from class: com.bytedance.msdk.adapter.ThirdSdkInit.1
                @Override // com.bytedance.msdk.adapter.config.TTOnNetworkInitializationFinishedListener
                public void onNetworkInitializationFinished(@NonNull Class<? extends ITTAdapterConfiguration> cls, @NonNull AdError adError) {
                    n.d(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d());
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
            Logger.e("TTMediationSDK_SDK_Init", "Pangle SDK 初始化失败。。 e=" + th.toString());
        }
    }

    public static void initTTSigmobSDK(Context context, String str, String str2) {
        Map<String, ITTAdapterConfiguration> mapE;
        ITTAdapterConfiguration iTTAdapterConfiguration;
        if (context == null) {
            return;
        }
        try {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (mapE = c.e()) == null || (iTTAdapterConfiguration = mapE.get(DefaultAdapterClasses.getClassNameByAdnName("sigmob"))) == null) {
                return;
            }
            HashMap map = new HashMap();
            map.put("app_id", str);
            map.put("app_key", str2);
            iTTAdapterConfiguration.initializeNetwork(context, map, new TTOnNetworkInitializationFinishedListener() { // from class: com.bytedance.msdk.adapter.ThirdSdkInit.6
                @Override // com.bytedance.msdk.adapter.config.TTOnNetworkInitializationFinishedListener
                public void onNetworkInitializationFinished(@NonNull Class<? extends ITTAdapterConfiguration> cls, @NonNull AdError adError) {
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
            Logger.e("TTMediationSDK_SDK_Init", "Sigmob SDK 初始化失败。。 e=" + th.toString());
        }
    }

    public static void initUnitySDK(Activity activity) {
        try {
            Method method = Class.forName("com.bytedance.msdk.adapter.init.UnitySdkInit").getMethod("initUnitySDK", Activity.class, String.class);
            a aVarA = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().a("unity");
            if (aVarA != null) {
                method.invoke(null, activity, aVarA.a());
            } else {
                Logger.e("TTMediationSDK_SDK_Init", "unitySdkInit 初始化失败。。 配置信息为null");
            }
        } catch (Throwable th) {
            Logger.e("TTMediationSDK_SDK_Init", "unitySdkInit 初始化失败。。 e=" + th.toString());
        }
    }
}

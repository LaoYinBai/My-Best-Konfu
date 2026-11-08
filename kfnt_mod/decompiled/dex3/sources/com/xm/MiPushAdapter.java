package com.xm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.bytedance.push.i;
import com.bytedance.push.settings.SettingsManager;
import com.bytedance.push.third.IPushAdapter;
import com.bytedance.push.third.e;
import com.sigmob.sdk.common.Constants;
import com.xiaomi.MiPushSettings;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MiPushAdapter implements IPushAdapter {
    private static int MI_PUSH = -1;

    public boolean isPushAvailable(Context context, int i) {
        return true;
    }

    public boolean requestNotificationPermission(int i) {
        return false;
    }

    public void trackPush(Context context, int i, Object obj) {
    }

    public static int getMiPush() {
        if (MI_PUSH == -1) {
            MI_PUSH = e.a(com.ss.android.message.a.a()).a(MiPushAdapter.class.getName());
        }
        return MI_PUSH;
    }

    public void registerPush(Context context, int i) {
        String str = null;
        if (context == null || i != getMiPush()) {
            if (context == null) {
                str = "空 context";
            } else if (i != getMiPush()) {
                str = "通道注册错误";
            }
            i.o().a(i, 101, Constants.FAIL, str);
            return;
        }
        i.l().c("MiPush", "registerMiPush");
        try {
            Pair pairA = i.m().a(getMiPush());
            if (pairA == null) {
                i.o().a(i, 106, Constants.FAIL, "配置为空");
                return;
            }
            doUpgradeMiPush(context);
            MiPushClient.registerPush(context, (String) pairA.first, (String) pairA.second);
            MiPushClient.resumePush(context.getApplicationContext(), null);
            Logger.setLogger(context, new LoggerInterface() { // from class: com.xm.MiPushAdapter.1
                @Override // com.xiaomi.channel.commonutils.logger.LoggerInterface
                public void setTag(String str2) {
                }

                @Override // com.xiaomi.channel.commonutils.logger.LoggerInterface
                public void log(String str2, Throwable th) {
                    i.l().a("MiPush", str2, th);
                }

                @Override // com.xiaomi.channel.commonutils.logger.LoggerInterface
                public void log(String str2) {
                    i.l().c("MiPush", str2);
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @SuppressLint({"ApplySharedPref"})
    private void doUpgradeMiPush(Context context) {
        MiPushSettings miPushSettings = (MiPushSettings) SettingsManager.obtain(context, MiPushSettings.class);
        if (miPushSettings.a()) {
            return;
        }
        miPushSettings.a(true);
        context.getSharedPreferences("mipush_account", 0).edit().clear().commit();
        context.getSharedPreferences("mipush_extra", 0).edit().clear().commit();
        context.getSharedPreferences("mipush", 0).edit().clear().commit();
        context.getSharedPreferences("XMPushServiceConfig", 0).edit().clear().commit();
    }

    public void setAlias(Context context, String str, int i) {
        if (context == null || i != getMiPush()) {
            return;
        }
        i.l().c("MiPush", "setAlias");
        try {
            List<String> allAlias = MiPushClient.getAllAlias(context);
            if (allAlias != null) {
                for (String str2 : allAlias) {
                    if (!TextUtils.isEmpty(str2) && !str2.equals(str)) {
                        MiPushClient.unsetAlias(context, str2, null);
                    }
                }
            }
        } catch (Exception unused) {
        }
        try {
            MiPushClient.setAlias(context, str, null);
        } catch (Exception e) {
            i.o().a(i, 105, Constants.FAIL, Log.getStackTraceString(e));
            e.printStackTrace();
        }
    }

    public void unregisterPush(Context context, int i) {
        if (context == null || i != getMiPush()) {
            return;
        }
        i.l().c("MiPush", "unregisterMiPush");
        try {
            MiPushClient.pausePush(context.getApplicationContext(), null);
            MiPushClient.unregisterPush(context.getApplicationContext());
        } catch (Throwable unused) {
        }
    }

    public boolean checkThirdPushConfig(String str, Context context) throws Exception {
        return a.a(str, context);
    }
}

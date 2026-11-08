package com.ss.android.pushmanager.client;

import android.content.Context;
import com.bytedance.push.i;
import com.bytedance.push.settings.PushOnlineSettings;
import com.bytedance.push.settings.SettingsManager;
import com.ss.android.message.a;
import com.ss.android.pushmanager.setting.PushSetting;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PushSettingManager {
    private static volatile PushSettingManager sPushSettingManager;

    public static PushSettingManager getInstance() {
        if (sPushSettingManager == null) {
            synchronized (PushSettingManager.class) {
                if (sPushSettingManager == null) {
                    sPushSettingManager = new PushSettingManager();
                }
            }
        }
        return sPushSettingManager;
    }

    public boolean isPushNotifyEnable(Context context) {
        return PushSetting.getInstance().isPushNotifyEnable();
    }

    public void notifyPushEnableChange(Context context, boolean z) {
        PushSetting.getInstance().setPushNotifyEnable(z);
        if (z) {
            i.j().c().a(context);
        } else {
            i.j().c().b(context);
        }
        i.j().b().a(context, z);
    }

    public void notifyAllowNetwork(Context context, boolean z) {
        PushSetting.getInstance().setIsAllowNetwork(z);
    }

    public void notifyAllowSettingsNotifyEnable(Context context, boolean z) {
        boolean zIsAllowSettingsNotifyEnable = PushSetting.getInstance().isAllowSettingsNotifyEnable();
        PushSetting.getInstance().setAllowSettingsNotifyEnable(z);
        if (zIsAllowSettingsNotifyEnable || !z) {
            return;
        }
        i.j().c().a(context);
    }

    public void notifyShutPushOnStopService(Context context, boolean z) {
        PushSetting.getInstance().setIsShutPushOnStopService(z);
    }

    public void notifyAllowPushJobService(Context context, boolean z) {
        PushSetting.getInstance().setAllowPushJobService(z);
    }

    public void notifyUninstallQuestionUrl(Context context, String str) {
        PushSetting.getInstance().setUninstallQuestionUrl(str);
    }

    public void notifyAllowPushDaemonMonitor(Context context, boolean z) {
        PushSetting.getInstance().setAllowPushDaemonMonitor(z);
    }

    public void notifyAllowOffAlive(Context context, boolean z) {
        PushSetting.getInstance().setAllowOffAlive(z);
    }

    public void setRequestSenderInterval(Context context, long j) {
        ((PushOnlineSettings) SettingsManager.obtain(context, PushOnlineSettings.class)).a(j * 1000);
    }

    public void setUpdateTokenIntervalInSecond(long j) {
        ((PushOnlineSettings) SettingsManager.obtain(a.a(), PushOnlineSettings.class)).b(j * 1000);
    }
}

package com.ss.android.pushmanager.setting;

import android.app.Application;
import android.content.Context;
import com.bytedance.push.settings.AliveOnlineSettings;
import com.bytedance.push.settings.LocalSettings;
import com.bytedance.push.settings.PushOnlineSettings;
import com.bytedance.push.settings.SettingsManager;
import com.ss.android.message.a;
import com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PushSetting {
    public static final String ALI_PUSH_TYPE = "ali_push_type";
    public static final String ALLOW_NETWORK = "allow_network";
    public static final String ALLOW_PUSH_JOB_SERVICE = "allow_push_job_service";
    public static final String ALLOW_SELF_PUSH_ENABLE = "allow_self_push_enable";
    private static final String EXTRA_ALIAS_KEY = "key_alias";
    public static final String NEED_CONTROL_MIUI_FLARES = "need_control_miui_flares_v2";
    public static final String PUSH_NOTIFY_ENABLE = "push_notify_enable";
    public static final String SHUT_PUSH_ON_STOP_SERVICE = "shut_push_on_stop_service";
    public static final String TAG = "PushSetting";
    public static final String UNINSTALL_QUESTION_URL = "uninstall_question_url";
    private static PushSetting sPushSetting;
    private PushMultiProcessSharedProvider.MultiProcessShared multiProcessShared = PushMultiProcessSharedProvider.getMultiprocessShared(a.a());

    public static void init(Context context) {
        a.a((Application) context.getApplicationContext());
    }

    public static PushSetting getInstance() {
        if (sPushSetting == null) {
            synchronized (PushSetting.class) {
                if (sPushSetting == null) {
                    sPushSetting = new PushSetting();
                }
            }
        }
        return sPushSetting;
    }

    private PushSetting() {
    }

    public void setIsShutPushOnStopService(boolean z) {
        getPushOnLineSettings().b(z);
    }

    public boolean isShutPushOnStopService() {
        return getPushOnLineSettings().e();
    }

    public void setPushNotifyEnable(boolean z) {
        getLocalSettings().b(z);
    }

    public boolean isPushNotifyEnable() {
        return getLocalSettings().f() && isAllowSettingsNotifyEnable();
    }

    public void setIsAllowNetwork(boolean z) {
        getLocalSettings().a(z);
    }

    public boolean isAllowNetwork() {
        return getLocalSettings().a();
    }

    public void setUninstallQuestionUrl(String str) {
        getAliveSettings().a(str);
    }

    public String getUninstallQuestionUrl() {
        return getAliveSettings().b();
    }

    public void setAllowPushJobService(boolean z) {
        getAliveSettings().b(z);
    }

    public boolean isAllowPushJobService() {
        return getAliveSettings().c();
    }

    public void setAllowPushDaemonMonitor(boolean z) {
        getAliveSettings().c(z);
    }

    public boolean isAllowPushDaemonMonitor() {
        if (com.bytedance.common.push.b.a.a.a.a.a() && getAliveSettings().k()) {
            return false;
        }
        return getAliveSettings().d();
    }

    public void setPushDaemonMonitor(String str) {
        getLocalSettings().a(str);
    }

    public String getPushDaemonMonitor() {
        return getLocalSettings().b();
    }

    public void setPushDaemonMonitorResult(String str) {
        getLocalSettings().b(str);
    }

    public String getPushDaemonMonitorResult() {
        return getLocalSettings().c();
    }

    public void setAllowCloseBootReceiver(boolean z) {
        getAliveSettings().d(z);
    }

    public boolean isAllowCloseBootReceiver() {
        return getAliveSettings().e();
    }

    public boolean isShutPushNotifyEnable() {
        return !isPushNotifyEnable() && isShutPushOnStopService();
    }

    public void setIsCloseAlarmWakeup(boolean z) {
        getAliveSettings().e(z);
    }

    public boolean isCloseAlarmWakeUp() {
        return getAliveSettings().f();
    }

    public void setPushChannelsJsonArray(String str) {
        getLocalSettings().c(str);
    }

    public String getPushChannelsJsonArray() {
        return getLocalSettings().d();
    }

    public void saveSSIDs(Map<String, String> map) {
        PushCommonSetting.getInstance().saveSSIDs(map);
    }

    public String getSsids() {
        return PushCommonSetting.getInstance().getSsids();
    }

    public void getSSIDs(Map<String, String> map) {
        PushCommonSetting.getInstance().getSSIDs(map);
    }

    public String getDeviceId() {
        return PushCommonSetting.getInstance().getDeviceId();
    }

    public void setAllowOffAlive(boolean z) {
        getAliveSettings().a(z);
    }

    private AliveOnlineSettings getAliveSettings() {
        return (AliveOnlineSettings) SettingsManager.obtain(a.a(), AliveOnlineSettings.class);
    }

    private PushOnlineSettings getPushOnLineSettings() {
        return (PushOnlineSettings) SettingsManager.obtain(a.a(), PushOnlineSettings.class);
    }

    private LocalSettings getLocalSettings() {
        return (LocalSettings) SettingsManager.obtain(a.a(), LocalSettings.class);
    }

    public void setAliPushType(int i) {
        getLocalSettings().a(i);
    }

    public int getAliPushType() {
        return getLocalSettings().e();
    }

    public void setAllowSettingsNotifyEnable(boolean z) {
        getPushOnLineSettings().a(z);
    }

    public boolean isAllowSettingsNotifyEnable() {
        return getPushOnLineSettings().a();
    }

    public void setIsReceiverMessageWakeupScreen(boolean z) {
        getPushOnLineSettings().c(z);
    }

    public boolean isReceiverMessageWakeupScreen() {
        return getPushOnLineSettings().f();
    }

    public void setReceiverMessageWakeupScreenTime(int i) {
        getPushOnLineSettings().a(i);
    }

    public int getReceiverMessageWakeupScreenTime() {
        return getPushOnLineSettings().g();
    }

    public void setIsUseStartForegroundNotification(boolean z) {
        getAliveSettings().f(z);
    }

    public boolean isUseStartForegroundNotification() {
        return getAliveSettings().g();
    }

    public void setJobScheduleWakeUpIntervalSecond(int i) {
        getAliveSettings().a(i);
    }

    public int getJobScheduleWakeUpIntervalSecond() {
        return getAliveSettings().h();
    }

    public void setIsUseCNativeProcessKeepAlive(boolean z) {
        getAliveSettings().g(z);
    }

    public boolean isUseCNativeProcessKeepAlive() {
        return getAliveSettings().i();
    }

    public void setIsNotifyServiceStick(boolean z) {
        getAliveSettings().h(z);
    }

    public boolean isNotifyServiceStick() {
        return getAliveSettings().j();
    }

    @Deprecated
    public void saveMapToProvider(Map<String, ?> map) {
        try {
            PushMultiProcessSharedProvider.Editor editorEdit = this.multiProcessShared.edit();
            if (map != null) {
                for (Map.Entry<String, ?> entry : map.entrySet()) {
                    Object value = entry.getValue();
                    if (value instanceof Integer) {
                        editorEdit.putInt(entry.getKey(), ((Integer) value).intValue());
                    } else if (value instanceof Long) {
                        editorEdit.putLong(entry.getKey(), ((Long) value).longValue());
                    } else if (value instanceof Float) {
                        editorEdit.putFloat(entry.getKey(), ((Float) value).floatValue());
                    } else if (value instanceof Boolean) {
                        editorEdit.putBoolean(entry.getKey(), ((Boolean) value).booleanValue());
                    } else if (value instanceof String) {
                        editorEdit.putString(entry.getKey(), (String) value);
                    }
                }
                editorEdit.commit();
            }
        } catch (Throwable unused) {
        }
    }

    @Deprecated
    public int getProviderInt(String str, int i) {
        return this.multiProcessShared.getInt(str, i);
    }

    @Deprecated
    public long getProviderLong(String str, long j) {
        return this.multiProcessShared.getLong(str, j);
    }

    @Deprecated
    public String getProviderString(String str, String str2) {
        return this.multiProcessShared.getString(str, str2);
    }

    @Deprecated
    public Boolean getProviderBoolean(String str, Boolean bool) {
        return Boolean.valueOf(this.multiProcessShared.getBoolean(str, bool.booleanValue()));
    }

    @Deprecated
    public float getProviderFloat(String str, float f) {
        return this.multiProcessShared.getFloat(str, f);
    }

    public String getAlias() {
        return this.multiProcessShared.getString(EXTRA_ALIAS_KEY, "");
    }

    public void setAlias(String str) {
        this.multiProcessShared.edit().putString(EXTRA_ALIAS_KEY, str).apply();
    }
}

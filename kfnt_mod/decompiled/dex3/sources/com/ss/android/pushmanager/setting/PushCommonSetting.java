package com.ss.android.pushmanager.setting;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.bytedance.common.push.utility.i;
import com.ss.android.message.a;
import com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PushCommonSetting {
    private static final String SP_CONFIG_NAME = "push_multi_process_config";
    public static final String SSIDS = "ssids";
    private static PushCommonSetting sPushSetting;
    private SharedPreferences prefs = null;
    private PushMultiProcessSharedProvider.MultiProcessShared multiProcessShared = PushMultiProcessSharedProvider.getMultiprocessShared(a.a());

    public static synchronized void init(Context context) {
        a.a((Application) context.getApplicationContext());
    }

    public static synchronized PushCommonSetting getInstance() {
        if (sPushSetting == null) {
            synchronized (PushCommonSetting.class) {
                if (sPushSetting == null) {
                    sPushSetting = new PushCommonSetting();
                }
            }
        }
        return sPushSetting;
    }

    private PushCommonSetting() {
    }

    public void saveSSIDs(Map<String, String> map) {
        if (map == null) {
            return;
        }
        if (com.bytedance.common.push.utility.a.b()) {
            com.bytedance.common.push.utility.a.b("PushService", "saveSSIDs start");
        }
        try {
            this.multiProcessShared.edit().putString(SSIDS, i.a(map)).apply();
        } catch (Exception unused) {
        }
    }

    public String getSsids() {
        return this.multiProcessShared.getString(SSIDS, "");
    }

    public void getSSIDs(Map<String, String> map) {
        if (map == null) {
            return;
        }
        if (com.bytedance.common.push.utility.a.b()) {
            com.bytedance.common.push.utility.a.b("PushService", "getSSIDs start");
        }
        try {
            String ssids = getSsids();
            if (i.a(ssids)) {
                return;
            }
            i.a(ssids, map);
        } catch (Exception unused) {
        }
    }

    public String getDeviceId() {
        HashMap map = new HashMap();
        getSSIDs(map);
        return map.get("device_id");
    }

    private SharedPreferences getSp() {
        if (this.prefs == null) {
            this.prefs = a.a().getSharedPreferences("push_multi_process_config", 4);
        }
        return this.prefs;
    }

    @Deprecated
    public void setPref(String str, boolean z) {
        SharedPreferences.Editor editorEdit = getSp().edit();
        editorEdit.putBoolean(str, z);
        editorEdit.apply();
    }

    @Deprecated
    public void setPref(String str, String str2) {
        SharedPreferences.Editor editorEdit = getSp().edit();
        editorEdit.putString(str, str2);
        editorEdit.apply();
    }

    @Deprecated
    public void setPref(String str, Set<String> set) {
        SharedPreferences.Editor editorEdit = getSp().edit();
        editorEdit.putStringSet(str, set);
        editorEdit.apply();
    }

    @Deprecated
    public void setPref(String str, float f) {
        SharedPreferences.Editor editorEdit = getSp().edit();
        editorEdit.putFloat(str, f);
        editorEdit.apply();
    }

    @Deprecated
    public void setPref(String str, int i) {
        SharedPreferences.Editor editorEdit = getSp().edit();
        editorEdit.putInt(str, i);
        editorEdit.apply();
    }

    @Deprecated
    public void setPref(String str, long j) {
        SharedPreferences.Editor editorEdit = getSp().edit();
        editorEdit.putLong(str, j);
        editorEdit.apply();
    }

    @Deprecated
    public boolean getPref(String str, Boolean bool) {
        return getSp().getBoolean(str, bool.booleanValue());
    }

    @Deprecated
    public String getPref(String str, String str2) {
        return getSp().getString(str, str2);
    }

    @Deprecated
    public Set<String> getPref(String str, Set<String> set) {
        return getSp().getStringSet(str, set);
    }

    @Deprecated
    public int getPref(String str, int i) {
        return getSp().getInt(str, i);
    }

    @Deprecated
    public long getPref(String str, long j) {
        return getSp().getLong(str, j);
    }

    @Deprecated
    public float getPref(String str, float f) {
        return getSp().getFloat(str, f);
    }

    @Deprecated
    public boolean hasPrefWithKey(String str) {
        return getSp().contains(str);
    }

    @Deprecated
    public boolean removePref(String str) {
        SharedPreferences.Editor editorEdit = getSp().edit();
        editorEdit.remove(str);
        return editorEdit.commit();
    }

    @Deprecated
    public void clear() {
        SharedPreferences.Editor editorEdit = getSp().edit();
        editorEdit.clear();
        editorEdit.commit();
    }

    public boolean isApplicationActive() {
        return this.multiProcessShared.isApplicationActive();
    }

    public boolean isAppForeground() {
        return this.multiProcessShared.isApplicationForeground();
    }
}

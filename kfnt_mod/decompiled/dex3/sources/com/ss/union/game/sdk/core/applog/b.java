package com.ss.union.game.sdk.core.applog;

import android.content.Context;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface b {
    String getDid();

    String getIid();

    String getUserID();

    String getVersion();

    void init(Context context, String str, String str2, boolean z);

    void onEventV3(String str, String str2);

    void onEventV3(String str, JSONObject jSONObject);

    void registerAbConfigGetListener(AppLogAbConfigGetListener appLogAbConfigGetListener);

    void registerIdUpdateListener(AppLogIdUpdateListener appLogIdUpdateListener);

    void registerOaidListener(AppLogOaidListener appLogOaidListener);

    void unRegisterAbConfigGetListener(AppLogAbConfigGetListener appLogAbConfigGetListener);

    void unRegisterIdUpdateListener(AppLogIdUpdateListener appLogIdUpdateListener);

    void unRegisterOaidListener(AppLogOaidListener appLogOaidListener);

    void updateDeviceAdultInfo(boolean z, String str);

    void updateUserInfo(String str, String str2, boolean z, String str3);
}

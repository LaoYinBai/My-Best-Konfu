package com.xiaomi;

import com.bytedance.push.settings.ILocalSettings;
import com.bytedance.push.settings.a.a;
import com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider;
import com.xiaomi.clientreport.data.Config;

@a(a = PushMultiProcessSharedProvider.SP_CONFIG_NAME, b = Config.DEFAULT_EVENT_ENCRYPTED)
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface MiPushSettings extends ILocalSettings {
    void a(boolean z);

    boolean a();
}

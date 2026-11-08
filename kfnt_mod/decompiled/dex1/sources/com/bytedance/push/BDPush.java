package com.bytedance.push;

import android.app.Application;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class BDPush {
    private static IBDPushService sBDPushService = new c();

    public static void initOnApplication(Application application, int i, String str, String str2) {
        initOnApplication(application, i, str, false, str2);
    }

    public static void initOnApplication(Application application, final int i, final String str, boolean z, String str2) {
        initOnApplication(application, new PushContext(application) { // from class: com.bytedance.push.BDPush.1
            public int getAid() {
                return i;
            }

            @Override // com.bytedance.push.PushContext
            public String getTweakedChannel() {
                return str;
            }
        }, z, str2);
    }

    private static void initOnApplication(Application application, PushContext pushContext, boolean z, String str) {
        sBDPushService.init(application, pushContext, z, str);
    }

    public static IBDPushService getService() {
        return sBDPushService;
    }
}

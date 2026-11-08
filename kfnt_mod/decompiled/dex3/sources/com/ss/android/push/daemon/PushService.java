package com.ss.android.push.daemon;

import android.content.Intent;
import com.bytedance.push.alive.AlvService;
import com.ss.android.pushmanager.a.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PushService extends AlvService {
    public int onStartCommand(Intent intent, int i, int i2) {
        int iOnStartCommand = super.onStartCommand(intent, i, i2);
        if (((a.b) com.ss.android.b.a.b.a(a.b.class)).g()) {
            return 2;
        }
        return iOnStartCommand;
    }
}

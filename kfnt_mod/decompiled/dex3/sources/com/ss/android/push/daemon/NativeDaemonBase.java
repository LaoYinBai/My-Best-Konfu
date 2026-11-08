package com.ss.android.push.daemon;

import android.content.Context;
import com.ss.android.push.daemon.e;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NativeDaemonBase {
    protected Context mContext;

    public NativeDaemonBase(Context context) {
        this.mContext = context;
    }

    protected void onDaemonDead() {
        if (com.bytedance.common.push.utility.a.b()) {
            com.bytedance.common.push.utility.a.b("DaemonManager", "onWatchDaemonDaed");
        }
        e.a.a().a();
    }
}

package com.ss.android.push.daemon.nativ;

import android.content.Context;
import com.ss.android.push.daemon.NativeDaemonBase;
import com.ss.android.push.daemon.f;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NativeDaemonAPI extends NativeDaemonBase {
    public native void doDaemon(String str, String str2, String str3, String str4);

    public NativeDaemonAPI(Context context) {
        super(context);
    }

    static {
        try {
            f.a("daemon");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

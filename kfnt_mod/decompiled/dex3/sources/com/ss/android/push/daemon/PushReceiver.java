package com.ss.android.push.daemon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PushReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        com.bytedance.push.alive.a.a(context.getApplicationContext()).a();
    }
}

package com.taobao.agoo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AgooCommondReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            intent.setClassName(context, com.taobao.accs.client.a.a(context.getPackageName()));
            com.taobao.accs.a.a.a(context, intent);
        } catch (Throwable unused) {
        }
    }
}

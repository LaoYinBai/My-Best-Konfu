package com.bytedance.push.alive;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class h implements d {
    @Override // com.bytedance.push.alive.d
    public void a(Context context) {
        try {
            context.getApplicationContext().bindService(new Intent(context, (Class<?>) ForegroundService.class), new a(), 1);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.bytedance.push.alive.d
    public boolean b(Context context) {
        return Build.VERSION.SDK_INT < 24 && com.ss.android.message.a.b.e(context);
    }

    private static class a implements ServiceConnection {
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }

        private a() {
        }
    }
}

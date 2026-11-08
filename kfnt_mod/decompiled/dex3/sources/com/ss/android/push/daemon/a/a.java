package com.ss.android.push.daemon.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.ss.android.push.daemon.e;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a implements e {
    protected Context a;
    protected com.ss.android.push.daemon.b b;
    private ServiceConnection c = new ServiceConnection() { // from class: com.ss.android.push.daemon.a.a.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                if (com.bytedance.common.push.utility.a.b()) {
                    com.bytedance.common.push.utility.a.b("DaemonStrategy", "bind service = " + componentName.getClassName());
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            if (a.this.a == null || a.this.b == null) {
                return;
            }
            try {
                if (com.bytedance.common.push.utility.a.b()) {
                    com.bytedance.common.push.utility.a.b("DaemonStrategy", "unbind service = " + componentName.getClassName());
                }
                a.this.a();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    };

    @Override // com.ss.android.push.daemon.e
    public void a() {
    }

    @Override // com.ss.android.push.daemon.e
    public void a(Context context, com.ss.android.push.daemon.b bVar) {
        this.a = context.getApplicationContext();
        this.b = bVar;
        try {
            Intent intent = new Intent(this.a, Class.forName(bVar.b.b));
            intent.setAction("start_by_daemon_action");
            this.a.startService(intent);
            this.a.bindService(intent, this.c, 1);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.ss.android.push.daemon.e
    public void b(Context context, com.ss.android.push.daemon.b bVar) {
        this.a = context.getApplicationContext();
        this.b = bVar;
        try {
            Intent intent = new Intent(this.a, Class.forName(bVar.a.b));
            intent.setAction("start_by_daemon_action");
            this.a.startService(intent);
            this.a.bindService(intent, this.c, 1);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}

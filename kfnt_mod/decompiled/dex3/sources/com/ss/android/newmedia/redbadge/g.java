package com.ss.android.newmedia.redbadge;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import com.ss.android.socialbase.appdownloader.constants.Constants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class g implements ServiceConnection {
    private Intent a;
    private boolean b;
    private Context c;

    g(Intent intent, boolean z, Context context) {
        this.a = intent;
        this.b = z;
        this.c = context;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Messenger messenger = new Messenger(iBinder);
        Message message = new Message();
        try {
            try {
                try {
                    message.what = 101;
                    message.getData().putParcelable(Constants.INTENT, this.a);
                    messenger.send(message);
                    com.bytedance.common.push.utility.a.b("RedbadgeServiceConnection", "unbindService");
                    if (this.b) {
                        this.c.unbindService(this);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    com.bytedance.common.push.utility.a.b("RedbadgeServiceConnection", "unbindService");
                    if (this.b) {
                        this.c.unbindService(this);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } catch (Throwable th2) {
            com.bytedance.common.push.utility.a.b("RedbadgeServiceConnection", "unbindService");
            try {
                if (this.b) {
                    this.c.unbindService(this);
                }
            } catch (Throwable th3) {
                th3.printStackTrace();
            }
            throw th2;
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        try {
            this.c.unbindService(this);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}

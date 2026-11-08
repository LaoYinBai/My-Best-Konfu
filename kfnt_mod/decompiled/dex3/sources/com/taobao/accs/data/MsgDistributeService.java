package com.taobao.accs.data;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;
import android.text.TextUtils;
import com.taobao.accs.common.Constants;
import com.taobao.accs.common.ThreadPoolExecutorFactory;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.t;
import com.taobao.accs.utl.w;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MsgDistributeService extends Service {
    private static boolean a;
    private Messenger b = new Messenger(new j(this));

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (t.c() && w.a(this) && !a) {
            a = true;
            getApplicationContext().bindService(new Intent(this, getClass()), new k(this), 1);
        }
        return this.b.getBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        try {
            ALog.i("MsgDistributeService", "onStartCommand", "action", intent.getAction());
            if (!TextUtils.isEmpty(intent.getAction()) && TextUtils.equals(intent.getAction(), Constants.ACTION_SEND)) {
                ThreadPoolExecutorFactory.getScheduledExecutor().execute(new l(this, intent));
            } else {
                ALog.i("MsgDistributeService", "onStartCommand distribute message", new Object[0]);
                g.a(getApplicationContext(), intent);
            }
        } catch (Throwable th) {
            ALog.e("MsgDistributeService", "onStartCommand", th, new Object[0]);
        }
        return 2;
    }
}

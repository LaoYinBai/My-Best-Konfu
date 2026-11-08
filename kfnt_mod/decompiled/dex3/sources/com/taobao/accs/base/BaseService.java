package com.taobao.accs.base;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import com.ss.android.socialbase.appdownloader.constants.Constants;
import com.taobao.accs.common.ThreadPoolExecutorFactory;
import com.taobao.accs.internal.ServiceImpl;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.w;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class BaseService extends Service {
    private static final String TAG = "BaseService";
    private static boolean isBinded;
    IBaseService mBaseService = null;
    private Messenger messenger = new Messenger(new Handler() { // from class: com.taobao.accs.base.BaseService.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message != null) {
                ALog.i(BaseService.TAG, "handleMessage on receive msg", "msg", message.toString());
                Intent intent = (Intent) message.getData().getParcelable(Constants.INTENT);
                if (intent != null) {
                    ALog.i(BaseService.TAG, "handleMessage get intent success", Constants.INTENT, intent.toString());
                    BaseService.this.onStartCommand(intent, 0, 0);
                }
            }
        }
    });

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        ThreadPoolExecutorFactory.execute(new Runnable() { // from class: com.taobao.accs.base.BaseService.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    BaseService.this.mBaseService = new ServiceImpl(BaseService.this);
                    BaseService.this.mBaseService.onCreate();
                } catch (Exception e) {
                    ALog.e(BaseService.TAG, "create ServiceImpl error", e.getMessage());
                }
            }
        });
    }

    @Override // android.app.Service
    public int onStartCommand(final Intent intent, final int i, final int i2) {
        ThreadPoolExecutorFactory.execute(new Runnable() { // from class: com.taobao.accs.base.BaseService.3
            @Override // java.lang.Runnable
            public void run() {
                if (BaseService.this.mBaseService != null) {
                    BaseService.this.mBaseService.onStartCommand(intent, i, i2);
                } else {
                    BaseService.this.onCreate();
                    BaseService.this.onStartCommand(intent, i, i2);
                }
            }
        });
        return 1;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        ALog.d(TAG, "onBind", Constants.INTENT, intent);
        try {
            if (w.a(this) && !isBinded) {
                isBinded = true;
                ALog.i(TAG, "onBind bind service", new Object[0]);
                getApplicationContext().bindService(new Intent(this, getClass()), new ServiceConnection() { // from class: com.taobao.accs.base.BaseService.4
                    @Override // android.content.ServiceConnection
                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    }

                    @Override // android.content.ServiceConnection
                    public void onServiceDisconnected(ComponentName componentName) {
                    }
                }, 1);
            }
        } catch (Throwable th) {
            ALog.i(TAG, "onBind bind service with exception", th.toString());
        }
        return this.messenger.getBinder();
    }

    @Override // android.app.Service
    public void onDestroy() {
        ThreadPoolExecutorFactory.execute(new Runnable() { // from class: com.taobao.accs.base.BaseService.5
            @Override // java.lang.Runnable
            public void run() {
                if (BaseService.this.mBaseService != null) {
                    BaseService.this.mBaseService.onDestroy();
                    BaseService.this.mBaseService = null;
                }
            }
        });
        super.onDestroy();
    }
}

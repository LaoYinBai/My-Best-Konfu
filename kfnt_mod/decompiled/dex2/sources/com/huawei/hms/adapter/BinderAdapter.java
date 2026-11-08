package com.huawei.hms.adapter;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.hms.activity.BridgeActivity;
import com.huawei.hms.api.BindingFailedResolution;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.Util;
import com.mbridge.msdk.playercommon.exoplayer2.DefaultRenderersFactory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class BinderAdapter implements ServiceConnection {
    private static final Object e = new Object();
    private Context a;
    private String b;
    private String c;
    private BinderCallBack d;
    private Handler f = null;

    public interface BinderCallBack {
        void onBinderFailed(int i);

        void onBinderFailed(int i, PendingIntent pendingIntent);

        void onServiceConnected(ComponentName componentName, IBinder iBinder);

        void onServiceDisconnected(ComponentName componentName);
    }

    public BinderAdapter(Context context, String str, String str2) {
        this.a = context;
        this.b = str;
        this.c = str2;
    }

    public void unBind() {
        Util.unBindServiceCatchException(this.a, this);
    }

    public void binder(BinderCallBack binderCallBack) {
        if (binderCallBack == null) {
            return;
        }
        this.d = binderCallBack;
        c();
    }

    private void a() {
        if (this.f != null) {
            this.f.removeMessages(1001);
        } else {
            this.f = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.huawei.hms.adapter.BinderAdapter.1
                @Override // android.os.Handler.Callback
                public boolean handleMessage(Message message) {
                    if (message == null || message.what != 1001) {
                        return false;
                    }
                    HMSLog.e("BinderAdapter", "In connect, bind core service time out");
                    BinderAdapter.this.b();
                    return true;
                }
            });
        }
        this.f.sendEmptyMessageDelayed(1001, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        BinderCallBack binderCallBackF = f();
        if (binderCallBackF != null) {
            binderCallBackF.onBinderFailed(-1);
        }
    }

    private void c() {
        if (TextUtils.isEmpty(this.b) || TextUtils.isEmpty(this.c)) {
            d();
        }
        Intent intent = new Intent(this.b);
        intent.setPackage(this.c);
        synchronized (e) {
            if (this.a.bindService(intent, this, 1)) {
                a();
            } else {
                d();
            }
        }
    }

    private void d() {
        HMSLog.e("BinderAdapter", "In connect, bind core service fail");
        ComponentName componentName = new ComponentName(this.a.getApplicationInfo().packageName, "com.huawei.hms.activity.BridgeActivity");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra(BridgeActivity.EXTRA_DELEGATE_CLASS_NAME, BindingFailedResolution.class.getName());
        this.d.onBinderFailed(-1, PendingIntent.getActivity(this.a, 11, intent, 134217728));
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        HMSLog.i("BinderAdapter", "Enter onServiceConnected.");
        e();
        BinderCallBack binderCallBackF = f();
        if (binderCallBackF != null) {
            binderCallBackF.onServiceConnected(componentName, iBinder);
        }
    }

    private void e() {
        synchronized (e) {
            if (this.f != null) {
                this.f.removeMessages(1001);
                this.f = null;
            }
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        HMSLog.i("BinderAdapter", "Enter onServiceDisconnected.");
        BinderCallBack binderCallBackF = f();
        if (binderCallBackF != null) {
            binderCallBackF.onServiceDisconnected(componentName);
        }
    }

    private BinderCallBack f() {
        return this.d;
    }
}

package com.heytap.mcssdk;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import com.heytap.mcssdk.utils.LogUtil;
import com.mcs.aidl.IMcsSdkService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class d implements ServiceConnection {
    final /* synthetic */ Intent a;
    final /* synthetic */ PushManager b;

    d(PushManager pushManager, Intent intent) {
        this.b = pushManager;
        this.a = intent;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Bundle bundle = new Bundle();
        bundle.putAll(this.a.getExtras());
        try {
            IMcsSdkService.Stub.asInterface(iBinder).process(bundle);
        } catch (Exception e) {
            LogUtil.d("bindMcsService exception:" + e);
        }
        this.b.mContext.unbindService(this);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
    }
}

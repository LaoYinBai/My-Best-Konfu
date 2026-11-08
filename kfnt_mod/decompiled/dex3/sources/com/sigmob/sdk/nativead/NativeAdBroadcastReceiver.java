package com.sigmob.sdk.nativead;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sigmob.sdk.base.common.BaseBroadcastReceiver;
import com.sigmob.sdk.base.models.IntentActions;
import com.sigmob.sdk.common.utils.m;
import com.sigmob.sdk.nativead.c;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NativeAdBroadcastReceiver extends BaseBroadcastReceiver {
    private static IntentFilter a;
    private c.a b;

    public NativeAdBroadcastReceiver(c.a aVar, String str) {
        super(str);
        this.b = aVar;
        a();
    }

    @Override // com.sigmob.sdk.base.common.BaseBroadcastReceiver
    public IntentFilter a() {
        if (a == null) {
            a = new IntentFilter();
            a.addAction(IntentActions.ACTION_NATIVE_TEMPLIE_SHOW);
            a.addAction(IntentActions.ACTION_NATIVE_TEMPLE_CLICK);
            a.addAction(IntentActions.ACTION_NATIVE_TEMPLE_DISMISS);
            a.addAction(IntentActions.ACTION_LANDPAGE_SHOW);
            a.addAction(IntentActions.ACTION_LANDPAGE_DISMISS);
        }
        return a;
    }

    @Override // com.sigmob.sdk.base.common.BaseBroadcastReceiver
    public void a(BroadcastReceiver broadcastReceiver) {
        super.a(broadcastReceiver);
        this.b = null;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        m.a.a(context);
        m.a.a(intent);
        if (this.b != null && a(intent)) {
            switch (intent.getAction()) {
                case "com.sigmob.action.native.temple.show":
                    this.b.c();
                    break;
                case "com.sigmob.action.native.temple.click":
                    this.b.d();
                    break;
                case "com.sigmob.action.native.temple.dismiss":
                    this.b.e();
                    break;
                case "com.sigmob.action.loadpage.show":
                    this.b.a();
                    break;
                case "com.sigmob.action.loadpage.dismiss":
                    this.b.b();
                    break;
            }
        }
    }
}

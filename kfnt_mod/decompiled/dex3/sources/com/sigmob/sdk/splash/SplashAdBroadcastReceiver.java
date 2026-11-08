package com.sigmob.sdk.splash;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sigmob.sdk.base.common.BaseBroadcastReceiver;
import com.sigmob.sdk.base.models.IntentActions;
import com.sigmob.sdk.common.utils.m;
import com.sigmob.sdk.splash.f;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SplashAdBroadcastReceiver extends BaseBroadcastReceiver {
    private static IntentFilter a;
    private f.a b;

    public SplashAdBroadcastReceiver(f.a aVar, String str) {
        super(str);
        this.b = aVar;
        a();
    }

    @Override // com.sigmob.sdk.base.common.BaseBroadcastReceiver
    public IntentFilter a() {
        if (a == null) {
            a = new IntentFilter();
            a.addAction(IntentActions.ACTION_SPLAH_STOP_TIME);
            a.addAction(IntentActions.ACTION_SPLAH_PLAYFAIL);
            a.addAction(IntentActions.ACTION_SPLAH_SKIP);
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
                case "com.sigmob.action.splash.play":
                    this.b.e();
                    break;
                case "com.sigmob.action.splash.playFail":
                    this.b.c();
                    break;
                case "com.sigmob.action.splash.skip":
                    this.b.d();
                    break;
                case "com.sigmob.action.splash.stoptime":
                    this.b.f();
                    break;
                case "com.sigmob.action.loadpage.show":
                case "com.sigmob.action.loadpage.dismiss":
                    this.b.b();
                    break;
            }
        }
    }
}

package com.sigmob.sdk.base.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sigmob.sdk.base.common.k;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.base.models.IntentActions;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class EventForwardingBroadcastReceiver extends BaseBroadcastReceiver {
    private static IntentFilter b;
    public BaseAdUnit a;
    private k.b c;

    public EventForwardingBroadcastReceiver(BaseAdUnit baseAdUnit, k.b bVar, String str) {
        super(str);
        this.a = baseAdUnit;
        this.c = bVar;
        a();
    }

    @Override // com.sigmob.sdk.base.common.BaseBroadcastReceiver
    public IntentFilter a() {
        if (b == null) {
            b = new IntentFilter();
            b.addAction(IntentActions.ACTION_INTERSTITIAL_FAIL);
            b.addAction(IntentActions.ACTION_INTERSTITIAL_SHOW);
            b.addAction(IntentActions.ACTION_INTERSTITIAL_DISMISS);
            b.addAction(IntentActions.ACTION_INTERSTITIAL_CLICK);
        }
        return b;
    }

    @Override // com.sigmob.sdk.base.common.BaseBroadcastReceiver
    public void a(BroadcastReceiver broadcastReceiver) {
        super.a(broadcastReceiver);
        this.c = null;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (this.c == null || this.a == null || !a(intent)) {
        }
        String action = intent.getAction();
        char c = 65535;
        int iHashCode = action.hashCode();
        if (iHashCode != -2078178004) {
            if (iHashCode != -1590979846) {
                if (iHashCode != -1590585639) {
                    if (iHashCode == 841881902 && action.equals(IntentActions.ACTION_INTERSTITIAL_DISMISS)) {
                        c = 2;
                    }
                } else if (action.equals(IntentActions.ACTION_INTERSTITIAL_SHOW)) {
                    c = 1;
                }
            } else if (action.equals(IntentActions.ACTION_INTERSTITIAL_FAIL)) {
                c = 0;
            }
        } else if (action.equals(IntentActions.ACTION_INTERSTITIAL_CLICK)) {
            c = 3;
        }
        switch (c) {
            case 0:
                this.c.a(this.a, intent.getStringExtra("error"));
                break;
            case 1:
                this.c.c(this.a);
                break;
            case 2:
                this.c.e(this.a);
                a(this);
                this.a = null;
                break;
            case 3:
                this.c.d(this.a);
                break;
        }
    }
}

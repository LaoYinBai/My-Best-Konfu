package com.sigmob.sdk.nativead;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sigmob.sdk.base.common.BaseBroadcastReceiver;
import com.sigmob.sdk.base.common.k;
import com.sigmob.sdk.base.models.IntentActions;
import com.sigmob.sdk.common.utils.m;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class APKStatusBroadcastReceiver extends BaseBroadcastReceiver {
    k.a a;
    IntentFilter b;

    public APKStatusBroadcastReceiver(k.a aVar, String str) {
        super(str);
        this.a = aVar;
    }

    @Override // com.sigmob.sdk.base.common.BaseBroadcastReceiver
    public IntentFilter a() {
        if (this.b == null) {
            this.b = new IntentFilter();
            this.b.addAction(IntentActions.ACTION_INTERSTITIAL_DOWNLOAD_START);
            this.b.addAction(IntentActions.ACTION_INTERSTITIAL_DOWNLOAD_END);
            this.b.addAction(IntentActions.ACTION_INTERSTITIAL_INSTALL_START);
            this.b.addAction(IntentActions.ACTION_INTERSTITIAL_INSTALL_END);
        }
        return this.b;
    }

    @Override // com.sigmob.sdk.base.common.BaseBroadcastReceiver
    public void a(BroadcastReceiver broadcastReceiver) {
        super.a(broadcastReceiver);
        this.a = null;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        m.a.a(context);
        m.a.a(intent);
        if (this.a != null && a(intent)) {
            String action = intent.getAction();
            boolean zEqualsIgnoreCase = "1".equalsIgnoreCase(intent.getStringExtra("result"));
            char c = 65535;
            int iHashCode = action.hashCode();
            if (iHashCode != -1616853332) {
                if (iHashCode != -845837224) {
                    if (iHashCode != 535418449) {
                        if (iHashCode == 995217907 && action.equals(IntentActions.ACTION_INTERSTITIAL_INSTALL_START)) {
                            c = 2;
                        }
                    } else if (action.equals(IntentActions.ACTION_INTERSTITIAL_DOWNLOAD_END)) {
                        c = 1;
                    }
                } else if (action.equals(IntentActions.ACTION_INTERSTITIAL_DOWNLOAD_START)) {
                    c = 0;
                }
            } else if (action.equals(IntentActions.ACTION_INTERSTITIAL_INSTALL_END)) {
                c = 3;
            }
            switch (c) {
                case 0:
                    this.a.a(zEqualsIgnoreCase);
                    break;
                case 1:
                    this.a.b(zEqualsIgnoreCase);
                    break;
                case 2:
                    this.a.c(zEqualsIgnoreCase);
                    break;
                case 3:
                    this.a.d(zEqualsIgnoreCase);
                    break;
            }
        }
    }
}

package com.sigmob.sdk.rewardVideoAd;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sigmob.sdk.base.common.BaseBroadcastReceiver;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.base.models.IntentActions;
import com.sigmob.sdk.common.utils.m;
import com.sigmob.sdk.rewardVideoAd.f;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RewardVideoAdBroadcastReceiver extends BaseBroadcastReceiver {
    private static IntentFilter a;
    private f.a b;
    private BaseAdUnit c;

    public RewardVideoAdBroadcastReceiver(BaseAdUnit baseAdUnit, f.a aVar, String str) {
        super(str);
        this.c = baseAdUnit;
        this.b = aVar;
        a();
    }

    @Override // com.sigmob.sdk.base.common.BaseBroadcastReceiver
    public IntentFilter a() {
        if (a == null) {
            a = new IntentFilter();
            a.addAction(IntentActions.ACTION_REWARDED_VIDEO_PLAY);
            a.addAction(IntentActions.ACTION_REWARDED_VIDEO_SKIP);
            a.addAction(IntentActions.ACTION_REWARDED_VIDEO_PLAYFAIL);
            a.addAction(IntentActions.ACTION_REWARDED_VIDEO_COMPLETE);
            a.addAction(IntentActions.ACTION_REWARDED_VIDEO_CLOSE);
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
        if (this.b == null || this.c == null || !a(intent)) {
            return;
        }
        switch (intent.getAction()) {
            case "com.sigmob.action.rewardedvideo.play":
                this.b.g(this.c);
                return;
            case "com.sigmob.action.rewardedvideo.skip":
                this.b.h(this.c);
                return;
            case "com.sigmob.action.rewardedvideo.playFail":
                this.b.b(this.c, intent.getStringExtra("error"));
                break;
            case "com.sigmob.action.rewardedvideo.complete":
                this.b.f(this.c);
                return;
            case "com.sigmob.action.rewardedvideo.Close":
                this.b.i(this.c);
                break;
            default:
                return;
        }
        a(this);
        this.c = null;
    }
}

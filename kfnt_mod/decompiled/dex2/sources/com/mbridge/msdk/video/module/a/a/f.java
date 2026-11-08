package com.mbridge.msdk.video.module.a.a;

import com.mbridge.msdk.video.module.MBridgeClickMiniCardView;
import org.json.JSONException;

/* compiled from: MiniCardProxyNotifyListener.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class f extends h {
    private MBridgeClickMiniCardView a;

    public f(MBridgeClickMiniCardView mBridgeClickMiniCardView, com.mbridge.msdk.video.module.a.a aVar) {
        super(aVar);
        this.a = mBridgeClickMiniCardView;
    }

    @Override // com.mbridge.msdk.video.module.a.a.h, com.mbridge.msdk.video.module.a.a.e, com.mbridge.msdk.video.module.a.a
    public final void a(int i, Object obj) throws JSONException {
        boolean z = false;
        switch (i) {
            case 100:
                if (this.a != null) {
                    this.a.webviewshow();
                    this.a.onSelfConfigurationChanged(this.a.getResources().getConfiguration());
                    break;
                }
                break;
            case 101:
            case 102:
                z = true;
                break;
            case 103:
                i = 107;
                break;
        }
        if (z) {
            return;
        }
        super.a(i, obj);
    }
}

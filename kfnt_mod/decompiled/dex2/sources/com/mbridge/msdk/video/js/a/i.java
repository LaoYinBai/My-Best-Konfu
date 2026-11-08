package com.mbridge.msdk.video.js.a;

import android.app.Activity;
import com.mbridge.msdk.video.bt.module.MBridgeBTContainer;

/* compiled from: JSBTModule.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class i extends b {
    private Activity a;
    private MBridgeBTContainer b;

    public i(Activity activity, MBridgeBTContainer mBridgeBTContainer) {
        this.a = activity;
        this.b = mBridgeBTContainer;
    }

    @Override // com.mbridge.msdk.video.js.a.b, com.mbridge.msdk.video.js.b
    public final void reactDeveloper(Object obj, String str) {
        super.reactDeveloper(obj, str);
        if (this.b != null) {
            this.b.reactDeveloper(obj, str);
        }
    }

    @Override // com.mbridge.msdk.video.js.a.b, com.mbridge.msdk.video.js.d
    public final void handlerH5Exception(int i, String str) {
        super.handlerH5Exception(i, str);
        if (this.b != null) {
            this.b.handlerH5Exception(i, str);
        }
    }

    @Override // com.mbridge.msdk.video.js.a.b, com.mbridge.msdk.video.js.d
    public final void click(int i, String str) {
        super.click(i, str);
        if (this.b != null) {
            this.b.click(i, str);
        }
    }
}

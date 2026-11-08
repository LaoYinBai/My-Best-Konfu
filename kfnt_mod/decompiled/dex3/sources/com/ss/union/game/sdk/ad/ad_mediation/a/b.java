package com.ss.union.game.sdk.ad.ad_mediation.a;

import com.bytedance.sdk.openadsdk.TTImage;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    private TTImage a;

    public b(TTImage tTImage) {
        this.a = tTImage;
    }

    public int a() {
        if (this.a == null) {
            return 0;
        }
        return this.a.getHeight();
    }

    public int b() {
        if (this.a == null) {
            return 0;
        }
        return this.a.getWidth();
    }

    public String c() {
        return this.a == null ? "" : this.a.getImageUrl();
    }

    public boolean d() {
        if (this.a == null) {
            return false;
        }
        return this.a.isValid();
    }
}

package com.ss.union.game.sdk.core.vapp.buoy;

import android.content.Context;
import com.ss.union.game.sdk.core.vapp.callback.LGAdVolumeRewardCallBack;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static a a = new a();
    private WeakReference<d> b;

    public static a a() {
        return a;
    }

    public void a(d dVar) {
        this.b = new WeakReference<>(dVar);
    }

    public void b() {
        this.b = null;
    }

    public void a(Context context, LGAdVolumeRewardCallBack lGAdVolumeRewardCallBack) {
        if (this.b == null || this.b.get() == null) {
            return;
        }
        this.b.get().a(context, lGAdVolumeRewardCallBack);
    }

    public void c() {
        if (this.b == null || this.b.get() == null) {
            return;
        }
        this.b.get().f();
    }
}

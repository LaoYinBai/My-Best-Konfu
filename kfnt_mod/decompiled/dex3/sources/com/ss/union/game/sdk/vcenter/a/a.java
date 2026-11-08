package com.ss.union.game.sdk.vcenter.a;

import com.ss.union.game.sdk.core.base.utils.DanJuanUtils;
import com.ss.union.game.sdk.vcenter.d;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a implements b {
    private a() {
    }

    public static a a() {
        return C0102a.a;
    }

    @Override // com.ss.union.game.sdk.vcenter.a.b
    public boolean b() {
        return c() && d.a().b();
    }

    @Override // com.ss.union.game.sdk.vcenter.a.b
    public boolean c() {
        return DanJuanUtils.isRunningDanJuanVirtual();
    }

    /* renamed from: com.ss.union.game.sdk.vcenter.a.a$a, reason: collision with other inner class name */
    private static class C0102a {
        private static final a a = new a();

        private C0102a() {
        }
    }
}

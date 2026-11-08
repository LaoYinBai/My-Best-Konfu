package com.ss.union.game.sdk.core.browser;

import android.content.Context;
import android.content.Intent;
import com.ss.union.game.sdk.common.dialog.BaseFragment;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c implements b<BaseFragment> {
    private BaseFragment a;

    @Override // com.ss.union.game.sdk.core.browser.b
    public void a() {
    }

    @Override // com.ss.union.game.sdk.core.browser.b
    public void a(int i) {
    }

    @Override // com.ss.union.game.sdk.core.browser.b
    public void a(boolean z) {
    }

    @Override // com.ss.union.game.sdk.core.browser.b
    public void b(boolean z) {
    }

    @Override // com.ss.union.game.sdk.core.browser.b
    public void a(BaseFragment baseFragment) {
        this.a = baseFragment;
    }

    @Override // com.ss.union.game.sdk.core.browser.b
    public void b(BaseFragment baseFragment) {
        this.a = null;
    }

    @Override // com.ss.union.game.sdk.core.browser.b
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public BaseFragment d() {
        return this.a;
    }

    @Override // com.ss.union.game.sdk.core.browser.b
    public void a(Intent intent, int i) {
        if (this.a != null) {
            this.a.startActivityForResult(intent, i);
        }
    }

    @Override // com.ss.union.game.sdk.core.browser.b
    public void b() {
        this.a.close();
    }

    @Override // com.ss.union.game.sdk.core.browser.b
    public Context c() {
        if (this.a != null) {
            return this.a.getContext();
        }
        return null;
    }
}

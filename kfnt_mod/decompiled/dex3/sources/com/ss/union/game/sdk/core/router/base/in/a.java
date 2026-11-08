package com.ss.union.game.sdk.core.router.base.in;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class a<T> implements IBaseRouter<T> {
    protected T a;

    @Override // com.ss.union.game.sdk.core.router.base.in.IBaseRouter
    public void register(T t) {
        this.a = t;
    }

    @Override // com.ss.union.game.sdk.core.router.base.in.IBaseRouter
    public void unRegister(T t) {
        this.a = null;
    }
}

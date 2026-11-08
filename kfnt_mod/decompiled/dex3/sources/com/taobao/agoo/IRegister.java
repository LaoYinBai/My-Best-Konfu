package com.taobao.agoo;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class IRegister extends ICallback {
    @Override // com.taobao.agoo.ICallback
    public abstract void onFailure(String str, String str2);

    @Override // com.taobao.agoo.ICallback
    public void onSuccess() {
    }

    public abstract void onSuccess(String str);
}

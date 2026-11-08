package com.ss.android.common.applog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IAnticheat {
    void onUpdateEstrFromApiFail(Exception exc);

    void onUpdateEstrFromApiSuccess(EstrBean estrBean);

    void updateEstr(String str);
}

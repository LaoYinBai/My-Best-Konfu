package com.yxcorp.kuaishou.addfp;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class a implements Runnable {
    private /* synthetic */ KWEGIDDFP a;

    a(KWEGIDDFP kwegiddfp) {
        this.a = kwegiddfp;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            if (this.a.mParamContext == null) {
                this.a.mCallBack.onFailed(-3, "parameter error");
                return;
            }
            this.a.mPkgName = this.a.mParamContext.getPackageName();
            com.yxcorp.kuaishou.addfp.android.a.a.a().a(this.a.mPkgName);
            this.a.getEGid(this.a.mCallBack);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}

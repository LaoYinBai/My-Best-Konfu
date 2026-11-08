package com.taobao.accs.init;

import com.taobao.accs.ACCSManager;
import com.taobao.accs.utl.l;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class c implements Runnable {
    final /* synthetic */ Launcher_InitAccs a;

    c(Launcher_InitAccs launcher_InitAccs) {
        this.a = launcher_InitAccs;
    }

    @Override // java.lang.Runnable
    public void run() {
        ACCSManager.bindApp(Launcher_InitAccs.mContext, Launcher_InitAccs.mAppkey, Launcher_InitAccs.mTtid, Launcher_InitAccs.mAppReceiver);
        Launcher_InitAccs.mIsInited = true;
        l.a().b();
    }
}

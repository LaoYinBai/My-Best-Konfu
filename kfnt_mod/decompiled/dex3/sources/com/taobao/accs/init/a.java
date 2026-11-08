package com.taobao.accs.init;

import com.taobao.accs.ACCSManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class a implements Runnable {
    final /* synthetic */ Launcher_CrossActivityStarted a;

    a(Launcher_CrossActivityStarted launcher_CrossActivityStarted) {
        this.a = launcher_CrossActivityStarted;
    }

    @Override // java.lang.Runnable
    public void run() {
        ACCSManager.bindApp(Launcher_InitAccs.mContext, Launcher_InitAccs.mAppkey, Launcher_InitAccs.mTtid, Launcher_InitAccs.mAppReceiver);
    }
}

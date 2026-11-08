package com.taobao.accs.init;

import com.taobao.accs.ACCSManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class b implements Runnable {
    final /* synthetic */ Launcher_CrossActivityStopped a;

    b(Launcher_CrossActivityStopped launcher_CrossActivityStopped) {
        this.a = launcher_CrossActivityStopped;
    }

    @Override // java.lang.Runnable
    public void run() {
        ACCSManager.bindApp(Launcher_InitAccs.mContext, Launcher_InitAccs.mAppkey, Launcher_InitAccs.mTtid, Launcher_InitAccs.mAppReceiver);
    }
}

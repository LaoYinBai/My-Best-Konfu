package com.taobao.accs.init;

import android.app.Application;
import android.text.TextUtils;
import anet.channel.util.AppLifecycle;
import com.taobao.accs.common.ThreadPoolExecutorFactory;
import com.taobao.accs.utl.ALog;
import java.io.Serializable;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Launcher_CrossActivityStopped implements Serializable {
    public void init(Application application, HashMap<String, Object> map) {
        try {
            if (ALog.isPrintLog(ALog.Level.I)) {
                ALog.i("Launcher_CrossActivityStopped", "onStopped", new Object[0]);
            }
            AppLifecycle.onBackground();
            if (!TextUtils.isEmpty(Launcher_InitAccs.mAppkey) && Launcher_InitAccs.mContext != null) {
                if (Launcher_InitAccs.mIsInited) {
                    ThreadPoolExecutorFactory.execute(new b(this));
                    return;
                }
                return;
            }
            ALog.e("Launcher_CrossActivityStopped", "params null!!!", "appkey", Launcher_InitAccs.mAppkey, "context", Launcher_InitAccs.mContext);
        } catch (Throwable th) {
            ALog.e("Launcher_CrossActivityStopped", "onStoped", th, new Object[0]);
            th.printStackTrace();
        }
    }
}

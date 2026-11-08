package com.taobao.accs.init;

import android.app.Application;
import com.taobao.accs.common.ThreadPoolExecutorFactory;
import com.taobao.accs.utl.ALog;
import java.io.Serializable;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Launcher_Login implements Serializable {
    public void init(Application application, HashMap<String, Object> map) {
        ALog.i("Launcher_Login", com.ss.union.game.sdk.account.a.a.ae, new Object[0]);
        ThreadPoolExecutorFactory.execute(new e(this, map, application));
    }
}

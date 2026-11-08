package com.taobao.accs.init;

import android.app.Application;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Launcher_InitAccsHTao extends Launcher_InitAccs {
    @Override // com.taobao.accs.init.Launcher_InitAccs
    public void init(Application application, HashMap<String, Object> map) {
        defaultAppkey = "";
        super.init(application, map);
        com.taobao.accs.client.a.b = "com.taobao.taobao.TaobaoIntentService";
    }
}

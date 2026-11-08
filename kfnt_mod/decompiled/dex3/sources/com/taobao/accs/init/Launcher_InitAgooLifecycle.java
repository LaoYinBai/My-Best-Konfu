package com.taobao.accs.init;

import android.app.Application;
import com.sigmob.sdk.common.mta.PointCategory;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.l;
import com.taobao.accs.utl.t;
import com.taobao.aranger.ARanger;
import java.io.Serializable;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Launcher_InitAgooLifecycle implements Serializable {
    private static final String a = "Launcher_InitAgooLifecycle";

    public void init(Application application, HashMap<String, Object> map) {
        ALog.i(a, PointCategory.INIT, new Object[0]);
        GlobalClientInfo.a = application.getApplicationContext();
        l.a().a(application);
        if (t.g()) {
            Constants.SDK_VERSION_CODE = 400;
            ARanger.init(application.getApplicationContext());
        }
    }
}

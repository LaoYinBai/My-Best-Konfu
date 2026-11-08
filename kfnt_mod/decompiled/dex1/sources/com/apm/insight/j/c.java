package com.apm.insight.j;

import android.os.Handler;
import android.text.TextUtils;
import com.apm.insight.h;
import com.apm.insight.l.q;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c extends a {
    c(Handler handler, long j, long j2) {
        super(handler, j, j2);
    }

    @Override // java.lang.Runnable
    public void run() {
        String str;
        if (h.c().b()) {
            q.a((Object) "[DeviceIdTask] did is done, stop check.");
            return;
        }
        String strD = h.a().d();
        if (TextUtils.isEmpty(strD) || OnekeyLoginConstants.CU_RESULT_SUCCESS.equals(strD)) {
            a(c());
            str = "[DeviceIdTask] did is null, continue check.";
        } else {
            h.c().a(strD);
            str = "[DeviceIdTask] did is " + strD;
        }
        q.a((Object) str);
    }
}

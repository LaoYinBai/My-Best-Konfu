package com.taobao.accs.ut.a;

import com.qq.e.comm.constants.Constants;
import com.ss.android.pushmanager.PushCommonConstants;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UTMini;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    public String a;
    public String b;
    public boolean c;
    public String d;
    public String e;
    private final String f = "BindUser";
    private boolean g = false;

    public void a(String str) {
        this.d = str;
    }

    public void a(int i) {
        if (i != 200) {
            if (i != 300) {
                switch (i) {
                    case -4:
                        a("msg too large");
                        break;
                    case -3:
                        a("service not available");
                        break;
                    case -2:
                        a("param error");
                        break;
                    case -1:
                        a("network fail");
                        break;
                    default:
                        a(String.valueOf(i));
                        break;
                }
            }
            a("app not bind");
        }
    }

    public void a() {
        b("BindUser");
    }

    private void b(String str) {
        String str2;
        String strValueOf;
        if (this.g) {
            return;
        }
        this.g = true;
        HashMap map = new HashMap();
        try {
            str2 = this.a;
            try {
                strValueOf = String.valueOf(Constants.SDK_VERSION_CODE);
                try {
                    map.put("device_id", this.a);
                    map.put("bind_date", this.b);
                    map.put(Constants.KEYS.RET, this.c ? "y" : "n");
                    map.put("fail_reasons", this.d);
                    map.put(PushCommonConstants.KEY_USER_ID, this.e);
                    if (ALog.isPrintLog(ALog.Level.D)) {
                        ALog.d("accs.BindUserStatistic", UTMini.getCommitInfo(66001, str2, (String) null, strValueOf, map), new Object[0]);
                    }
                    UTMini.getInstance().commitEvent(66001, str, str2, (Object) null, strValueOf, map);
                } catch (Throwable th) {
                    th = th;
                    ALog.d("accs.BindUserStatistic", UTMini.getCommitInfo(66001, str2, (String) null, strValueOf, map) + " " + th.toString(), new Object[0]);
                }
            } catch (Throwable th2) {
                th = th2;
                strValueOf = null;
            }
        } catch (Throwable th3) {
            th = th3;
            str2 = null;
            strValueOf = null;
        }
    }
}

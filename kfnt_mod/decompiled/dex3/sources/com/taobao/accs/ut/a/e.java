package com.taobao.accs.ut.a;

import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UTMini;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class e {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    private final String g = "sendAck";
    private boolean h = false;

    public void a() {
        String str;
        String strValueOf;
        if (this.h) {
            return;
        }
        this.h = true;
        HashMap map = new HashMap();
        try {
            str = this.a;
            try {
                strValueOf = String.valueOf(Constants.SDK_VERSION_CODE);
                try {
                    map.put("device_id", this.a);
                    map.put("session_id", this.b);
                    map.put("data_id", this.c);
                    map.put("ack_date", this.d);
                    map.put("service_id", this.e);
                    map.put("fail_reasons", this.f);
                    UTMini.getInstance().commitEvent(66001, "sendAck", str, (Object) null, strValueOf, map);
                } catch (Throwable th) {
                    th = th;
                    ALog.d("accs.SendAckStatistic", UTMini.getCommitInfo(66001, str, (String) null, strValueOf, map) + " " + th.toString(), new Object[0]);
                }
            } catch (Throwable th2) {
                th = th2;
                strValueOf = null;
            }
        } catch (Throwable th3) {
            th = th3;
            str = null;
            strValueOf = null;
        }
    }
}

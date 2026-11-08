package com.taobao.accs.ut.a;

import com.ss.android.pushmanager.PushCommonConstants;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UTMini;
import com.umeng.message.MsgConstant;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String i;
    private final String j = "receiveMessage";
    public boolean h = false;
    private boolean k = false;

    public void a() {
        String str;
        String strValueOf;
        if (this.k) {
            return;
        }
        this.k = true;
        HashMap map = new HashMap();
        try {
            str = this.a;
            try {
                strValueOf = String.valueOf(Constants.SDK_VERSION_CODE);
                try {
                    map.put("device_id", this.a);
                    map.put("data_id", this.b);
                    map.put("receive_date", this.c);
                    map.put("to_bz_date", this.d);
                    map.put("service_id", this.e);
                    map.put("data_length", this.f);
                    map.put(MsgConstant.INAPP_MSG_TYPE, this.g);
                    map.put("repeat", this.h ? "y" : "n");
                    map.put(PushCommonConstants.KEY_USER_ID, this.i);
                    UTMini.getInstance().commitEvent(66001, "receiveMessage", str, (Object) null, strValueOf, map);
                } catch (Throwable th) {
                    th = th;
                    ALog.d("ReceiveMessage", UTMini.getCommitInfo(66001, str, (String) null, strValueOf, map) + " " + th.toString(), new Object[0]);
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

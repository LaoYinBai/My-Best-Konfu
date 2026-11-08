package com.taobao.agoo;

import android.content.Intent;
import com.taobao.accs.utl.ALog;
import com.taobao.agoo.BaseNotifyClickActivity;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class f implements BaseNotifyClickActivity.INotifyListener {
    @Override // com.taobao.agoo.BaseNotifyClickActivity.INotifyListener
    public String getMsgSource() {
        return "vivo";
    }

    @Override // com.taobao.agoo.BaseNotifyClickActivity.INotifyListener
    public String parseMsgFromIntent(Intent intent) {
        Throwable th;
        String stringExtra;
        if (intent == null) {
            ALog.e("DefaultVivoMsgParseImpl", "parseMsgFromIntent null", new Object[0]);
            return null;
        }
        try {
            stringExtra = intent.getStringExtra("vivo_payload");
            try {
                ALog.i("DefaultVivoMsgParseImpl", "parseMsgFromIntent", "msg", stringExtra);
            } catch (Throwable th2) {
                th = th2;
                ALog.e("DefaultVivoMsgParseImpl", "parseMsgFromIntent", th, new Object[0]);
                return stringExtra;
            }
        } catch (Throwable th3) {
            th = th3;
            stringExtra = null;
        }
        return stringExtra;
    }
}

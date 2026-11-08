package org.android.agoo.control;

import android.text.TextUtils;
import com.taobao.accs.utl.ALog;
import org.android.agoo.common.AgooConstants;
import org.android.agoo.common.Config;
import org.android.agoo.message.MessageService;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
class d implements Runnable {
    final /* synthetic */ byte[] a;
    final /* synthetic */ boolean b;
    final /* synthetic */ AgooFactory c;

    d(AgooFactory agooFactory, byte[] bArr, boolean z) {
        this.c = agooFactory;
        this.a = bArr;
        this.b = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            String str = new String(this.a, "utf-8");
            if (TextUtils.isEmpty(str)) {
                com.taobao.accs.utl.k.a(AgooConstants.MESSAGE_SOURCE_ACCS, "agoo_fail_ack", "msg==null", 0.0d);
                return;
            }
            ALog.i("AgooFactory", "message = " + str, new Object[0]);
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("api");
            String string2 = jSONObject.getString(AgooConstants.MESSAGE_ID);
            String string3 = TextUtils.equals(string, "agooReport") ? jSONObject.getString("status") : null;
            if (TextUtils.equals(string, AgooConstants.AGOO_SERVICE_AGOOACK)) {
                com.taobao.accs.utl.k.a(AgooConstants.MESSAGE_SOURCE_ACCS, "agoo_success_ack", "handlerACKMessage", 0.0d);
            }
            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string3)) {
                if (ALog.isPrintLog(ALog.Level.I)) {
                    ALog.i("AgooFactory", "updateMsg data begin,api=" + string + ",id=" + string2 + ",status=" + string3 + ",reportTimes=" + Config.e(AgooFactory.mContext), new Object[0]);
                }
                if (TextUtils.equals(string, "agooReport")) {
                    if (TextUtils.equals(string3, MessageService.MSG_ACCS_READY_REPORT) && this.b) {
                        this.c.messageService.a(string2, MessageService.MSG_DB_NOTIFY_REACHED);
                    } else if ((TextUtils.equals(string3, MessageService.MSG_ACCS_NOTIFY_CLICK) || TextUtils.equals(string3, MessageService.MSG_ACCS_NOTIFY_DISMISS)) && this.b) {
                        this.c.messageService.a(string2, MessageService.MSG_DB_COMPLETE);
                    }
                    com.taobao.accs.utl.k.a(AgooConstants.MESSAGE_SOURCE_ACCS, "agoo_success_ack", string3, 0.0d);
                    return;
                }
                return;
            }
            com.taobao.accs.utl.k.a(AgooConstants.MESSAGE_SOURCE_ACCS, "agoo_fail_ack", "json key null", 0.0d);
        } catch (Throwable th) {
            ALog.e("AgooFactory", "updateMsg get data error,e=" + th, new Object[0]);
            com.taobao.accs.utl.k.a(AgooConstants.MESSAGE_SOURCE_ACCS, "agoo_fail_ack", "json exception", 0.0d);
        }
    }
}

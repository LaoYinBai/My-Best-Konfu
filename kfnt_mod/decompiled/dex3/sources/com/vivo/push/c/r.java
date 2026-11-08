package com.vivo.push.c;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.vivo.push.b.x;
import com.vivo.push.util.NotifyAdapterUtil;
import com.vivo.push.util.y;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class r extends o {
    public r(com.vivo.push.g gVar) {
        super(gVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(com.vivo.push.g gVar) throws PackageManager.NameNotFoundException {
        com.vivo.push.b.u uVar = (com.vivo.push.b.u) gVar;
        if (com.vivo.push.d.a().f) {
            if (!a(y.d(this.a), uVar.c != -1 ? String.valueOf(uVar.c) : null, uVar.d)) {
                com.vivo.push.util.n.d("OnUndoMsgTask", " vertify msg is error ");
                x xVar = new x(1021L);
                HashMap<String, String> map = new HashMap<>();
                map.put("messageID", String.valueOf(uVar.e));
                String strB = y.b(this.a, this.a.getPackageName());
                if (!TextUtils.isEmpty(strB)) {
                    map.put("remoteAppId", strB);
                }
                xVar.c = map;
                com.vivo.push.d.a().a(xVar);
                return;
            }
        }
        boolean zRepealNotifyById = NotifyAdapterUtil.repealNotifyById(this.a, uVar.c);
        com.vivo.push.util.n.d("OnUndoMsgTask", "undo message " + uVar.c + ", " + zRepealNotifyById);
        if (zRepealNotifyById) {
            com.vivo.push.util.n.b(this.a, "回收client通知成功, 上报埋点 1031, messageId = " + uVar.c);
            Context context = this.a;
            long j = uVar.c;
            com.vivo.push.util.n.d("ClientReportUtil", "report message: " + j + ", reportType: 1031");
            x xVar2 = new x(1031L);
            HashMap<String, String> map2 = new HashMap<>();
            map2.put("messageID", String.valueOf(j));
            String strB2 = y.b(context, context.getPackageName());
            if (!TextUtils.isEmpty(strB2)) {
                map2.put("remoteAppId", strB2);
            }
            xVar2.c = map2;
            com.vivo.push.d.a().a(xVar2);
            return;
        }
        com.vivo.push.util.n.d("OnUndoMsgTask", "undo message fail，messageId = " + uVar.c);
        com.vivo.push.util.n.c(this.a, "回收client通知失败，messageId = " + uVar.c);
    }
}

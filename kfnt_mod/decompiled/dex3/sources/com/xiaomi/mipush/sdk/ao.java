package com.xiaomi.mipush.sdk;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class ao extends Handler {
    final /* synthetic */ an a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ao(an anVar, Looper looper) {
        super(looper);
        this.a = anVar;
    }

    @Override // android.os.Handler
    public void dispatchMessage(Message message) {
        an anVar;
        at atVar;
        HashMap<String, String> mapA;
        an anVar2;
        at atVar2;
        if (message.what != 19) {
            return;
        }
        String str = (String) message.obj;
        int i = message.arg1;
        synchronized (ae.class) {
            if (ae.a(this.a.f67a).m43a(str)) {
                if (ae.a(this.a.f67a).a(str) < 10) {
                    if (at.DISABLE_PUSH.ordinal() == i && "syncing".equals(ae.a(this.a.f67a).a(at.DISABLE_PUSH))) {
                        anVar2 = this.a;
                        atVar2 = at.DISABLE_PUSH;
                    } else if (at.ENABLE_PUSH.ordinal() == i && "syncing".equals(ae.a(this.a.f67a).a(at.ENABLE_PUSH))) {
                        anVar2 = this.a;
                        atVar2 = at.ENABLE_PUSH;
                    } else {
                        if (at.UPLOAD_HUAWEI_TOKEN.ordinal() == i && "syncing".equals(ae.a(this.a.f67a).a(at.UPLOAD_HUAWEI_TOKEN))) {
                            anVar = this.a;
                            atVar = at.UPLOAD_HUAWEI_TOKEN;
                            mapA = i.a(this.a.f67a, e.ASSEMBLE_PUSH_HUAWEI);
                        } else if (at.UPLOAD_FCM_TOKEN.ordinal() == i && "syncing".equals(ae.a(this.a.f67a).a(at.UPLOAD_FCM_TOKEN))) {
                            anVar = this.a;
                            atVar = at.UPLOAD_FCM_TOKEN;
                            mapA = i.a(this.a.f67a, e.ASSEMBLE_PUSH_FCM);
                        } else if (at.UPLOAD_COS_TOKEN.ordinal() == i && "syncing".equals(ae.a(this.a.f67a).a(at.UPLOAD_COS_TOKEN))) {
                            anVar = this.a;
                            atVar = at.UPLOAD_COS_TOKEN;
                            mapA = i.a(this.a.f67a, e.ASSEMBLE_PUSH_COS);
                        } else {
                            if (at.UPLOAD_FTOS_TOKEN.ordinal() == i && "syncing".equals(ae.a(this.a.f67a).a(at.UPLOAD_FTOS_TOKEN))) {
                                anVar = this.a;
                                atVar = at.UPLOAD_FTOS_TOKEN;
                                mapA = i.a(this.a.f67a, e.ASSEMBLE_PUSH_FTOS);
                            }
                            ae.a(this.a.f67a).b(str);
                        }
                        anVar.a(str, atVar, false, (HashMap<String, String>) mapA);
                        ae.a(this.a.f67a).b(str);
                    }
                    anVar2.a(str, atVar2, true, (HashMap<String, String>) null);
                    ae.a(this.a.f67a).b(str);
                } else {
                    ae.a(this.a.f67a).c(str);
                }
            }
        }
    }
}

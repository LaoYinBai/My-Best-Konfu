package com.vivo.push.c;

import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.vivo.push.b.x;
import com.vivo.push.cache.ClientConfigManagerImpl;
import com.vivo.push.model.UnvarnishedMessage;
import com.vivo.push.util.y;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class k extends o {
    public k(com.vivo.push.g gVar) {
        super(gVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(com.vivo.push.g gVar) throws PackageManager.NameNotFoundException {
        com.vivo.push.b.o oVar = (com.vivo.push.b.o) gVar;
        com.vivo.push.d.a().a(new com.vivo.push.b.h(String.valueOf(oVar.e)));
        if (!ClientConfigManagerImpl.getInstance(this.a).isEnablePush()) {
            com.vivo.push.util.n.d("OnMessageTask", "command  " + gVar + " is ignore by disable push ");
            x xVar = new x(1020L);
            HashMap<String, String> map = new HashMap<>();
            map.put("messageID", String.valueOf(oVar.e));
            String strB = y.b(this.a, this.a.getPackageName());
            if (!TextUtils.isEmpty(strB)) {
                map.put("remoteAppId", strB);
            }
            xVar.c = map;
            com.vivo.push.d.a().a(xVar);
            return;
        }
        if (com.vivo.push.d.a().f && !a(y.d(this.a), oVar.b(), oVar.d)) {
            x xVar2 = new x(1021L);
            HashMap<String, String> map2 = new HashMap<>();
            map2.put("messageID", String.valueOf(oVar.e));
            String strB2 = y.b(this.a, this.a.getPackageName());
            if (!TextUtils.isEmpty(strB2)) {
                map2.put("remoteAppId", strB2);
            }
            xVar2.c = map2;
            com.vivo.push.d.a().a(xVar2);
            return;
        }
        final UnvarnishedMessage unvarnishedMessageC = oVar.c();
        if (unvarnishedMessageC != null) {
            com.vivo.push.util.n.d("OnMessageTask", "tragetType is " + unvarnishedMessageC.getTargetType() + " ; target is " + unvarnishedMessageC.getTragetContent());
            com.vivo.push.f.b(new Runnable() { // from class: com.vivo.push.c.k.1
                @Override // java.lang.Runnable
                public final void run() {
                    k.this.c.onTransmissionMessage(k.this.a, unvarnishedMessageC);
                }
            });
            return;
        }
        com.vivo.push.util.n.a("OnMessageTask", " message is null");
    }
}

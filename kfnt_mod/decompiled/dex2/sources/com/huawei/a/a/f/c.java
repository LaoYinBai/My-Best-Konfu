package com.huawei.a.a.f;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.a.a.b.c.a.e;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.util.LinkedHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    public e a;
    private String b;

    public c(String str) {
        this.b = str;
        this.a = new e(str);
        com.huawei.a.a.b.c.a.a.a().a(this.b, this.a);
    }

    private com.huawei.a.a.b.c.a.c b(int i) {
        switch (i) {
            case 0:
                return this.a.b();
            case 1:
                return this.a.a();
            case 2:
                return this.a.c();
            case 3:
                return this.a.d();
            default:
                return null;
        }
    }

    private boolean c(int i) {
        if (i == 2) {
            if ("_default_config_tag".equals(this.b)) {
                return true;
            }
            com.huawei.a.a.b.e.a.c("hmsSdk", "verifyURL(): type: preins. Only default config can report Pre-install data.");
            return false;
        }
        com.huawei.a.a.b.c.a.c cVarB = b(i);
        if (cVarB != null && !TextUtils.isEmpty(cVarB.h())) {
            return true;
        }
        com.huawei.a.a.b.e.a.c("hmsSdk", "verifyURL(): URL check failed. type: " + i);
        return false;
    }

    public void a(int i) {
        com.huawei.a.a.b.e.a.a("hmsSdk", "HiAnalyticsInstance.onReport() is execute.TAG: %s,TYPE: %d", this.b, Integer.valueOf(i));
        b.a().a(this.b, i);
    }

    public void a(int i, com.huawei.a.a.b.c.a.c cVar) {
        com.huawei.a.a.b.c.a.c cVar2;
        if (cVar == null) {
            com.huawei.a.a.b.e.a.a("hmsSdk", "HiAnalyticsInstanceImpl.refresh(). Parameter config is null.TAG : %s , TYPE : %d", this.b, Integer.valueOf(i));
            cVar2 = null;
        } else {
            cVar2 = new com.huawei.a.a.b.c.a.c(cVar);
        }
        com.huawei.a.a.b.e.a.a("hmsSdk", "HiAnalyticsInstanceImpl.refresh() is executed.TAG : %s , TYPE : %d", this.b, Integer.valueOf(i));
        switch (i) {
            case 0:
                c(cVar2);
                com.huawei.a.a.b.h.a.a().a(this.b);
                break;
            case 1:
                a(cVar2);
                break;
            case 2:
                d(cVar2);
                break;
            case 3:
                b(cVar2);
                break;
            default:
                com.huawei.a.a.b.e.a.c("hmsSdk", "refresh(): HiAnalyticsType can only be OPERATION ,MAINTAIN or DIFF_PRIVACY.");
                break;
        }
    }

    public void a(int i, String str, LinkedHashMap<String, String> linkedHashMap) {
        com.huawei.a.a.b.e.a.a("hmsSdk", "HiAnalyticsInstance.onEvent(int type, String eventId, Map<String, String> mapValue) is execute.TAG: %s,TYPE: %d", this.b, Integer.valueOf(i));
        if (com.huawei.a.a.b.k.c.a(str) || !c(i)) {
            com.huawei.a.a.b.e.a.c("hmsSdk", "onEvent() parameters check fail. Nothing will be recorded.TAG: " + this.b + ", TYPE: " + i);
            return;
        }
        if (!com.huawei.a.a.b.k.c.a(linkedHashMap)) {
            com.huawei.a.a.b.e.a.c("hmsSdk", "onEvent() parameter mapValue will be cleared.TAG: " + this.b + ", TYPE: " + i);
            linkedHashMap = null;
        }
        b.a().a(this.b, i, str, linkedHashMap);
    }

    public void a(Context context, String str, String str2) {
        com.huawei.a.a.b.e.a.b("hmsSdk", "HiAnalyticsInstance.onEvent(eventId, mapValue) is execute.TAG : " + this.b);
        if (context == null) {
            com.huawei.a.a.b.e.a.c("hmsSdk", "context is null in onevent ");
            return;
        }
        if (com.huawei.a.a.b.k.c.a(str) || !c(0)) {
            com.huawei.a.a.b.e.a.c("hmsSdk", "onEvent() parameters check fail. Nothing will be recorded.TAG: " + this.b);
            return;
        }
        if (!com.huawei.a.a.b.k.c.a("value", str2, C.DEFAULT_BUFFER_SEGMENT_SIZE)) {
            com.huawei.a.a.b.e.a.c("hmsSdk", "onEvent() parameter VALUE is overlong, content will be cleared.TAG: " + this.b);
            str2 = "";
        }
        b.a().a(this.b, context, str, str2);
    }

    public void a(com.huawei.a.a.b.c.a.c cVar) {
        com.huawei.a.a.b.e.a.b("hmsSdk", "HiAnalyticsInstanceImpl.setMaintConf() is executed.TAG : " + this.b);
        if (cVar != null) {
            this.a.a(cVar);
        } else {
            com.huawei.a.a.b.e.a.c("hmsSdk", "HiAnalyticsInstanceImpl.setMaintConf(): config for maint is null!");
            this.a.a((com.huawei.a.a.b.c.a.c) null);
        }
    }

    public void b(com.huawei.a.a.b.c.a.c cVar) {
        com.huawei.a.a.b.e.a.b("hmsSdk", "HiAnalyticsInstanceImpl.setDiffConf() is executed.TAG : " + this.b);
        if (cVar != null) {
            this.a.d(cVar);
        } else {
            com.huawei.a.a.b.e.a.c("hmsSdk", "HiAnalyticsInstanceImpl.setDiffConf(): config for diffPrivacy is null!");
            this.a.d(null);
        }
    }

    public void c(com.huawei.a.a.b.c.a.c cVar) {
        com.huawei.a.a.b.e.a.b("hmsSdk", "HiAnalyticsInstanceImpl.setOperConf() is executed.TAG: " + this.b);
        if (cVar != null) {
            this.a.b(cVar);
        } else {
            this.a.b(null);
            com.huawei.a.a.b.e.a.c("hmsSdk", "HiAnalyticsInstanceImpl.setOperConf(): config for oper is null!");
        }
    }

    public void d(com.huawei.a.a.b.c.a.c cVar) {
        com.huawei.a.a.b.e.a.b("hmsSdk", "HiAnalyticsInstanceImpl.setPreInstallConf() is executed.TAG: " + this.b);
        if (cVar != null) {
            this.a.c(cVar);
        } else {
            com.huawei.a.a.b.e.a.c("hmsSdk", "HiAnalyticsInstanceImpl.setPreInstallConf(): config for PRE-INSTALL is null!");
            this.a.c(null);
        }
    }
}

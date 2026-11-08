package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.ho;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class f implements AbstractPushManager {
    private static volatile f a;

    /* renamed from: a, reason: collision with other field name */
    private Context f89a;

    /* renamed from: a, reason: collision with other field name */
    private PushConfiguration f90a;

    /* renamed from: a, reason: collision with other field name */
    private boolean f92a = false;

    /* renamed from: a, reason: collision with other field name */
    private Map<e, AbstractPushManager> f91a = new HashMap();

    private f(Context context) {
        this.f89a = context.getApplicationContext();
    }

    public static f a(Context context) {
        if (a == null) {
            synchronized (f.class) {
                if (a == null) {
                    a = new f(context);
                }
            }
        }
        return a;
    }

    private void a() {
        AbstractPushManager abstractPushManagerA;
        AbstractPushManager abstractPushManagerA2;
        AbstractPushManager abstractPushManagerA3;
        AbstractPushManager abstractPushManagerA4;
        if (this.f90a != null) {
            if (this.f90a.getOpenHmsPush()) {
                StringBuilder sb = new StringBuilder();
                sb.append("ASSEMBLE_PUSH : ");
                sb.append(" HW user switch : " + this.f90a.getOpenHmsPush() + " HW online switch : " + i.m84a(this.f89a, e.ASSEMBLE_PUSH_HUAWEI) + " HW isSupport : " + af.HUAWEI.equals(n.a(this.f89a)));
                com.xiaomi.channel.commonutils.logger.b.m22a(sb.toString());
            }
            if (this.f90a.getOpenHmsPush() && i.m84a(this.f89a, e.ASSEMBLE_PUSH_HUAWEI) && af.HUAWEI.equals(n.a(this.f89a))) {
                if (!m80a(e.ASSEMBLE_PUSH_HUAWEI)) {
                    a(e.ASSEMBLE_PUSH_HUAWEI, aj.a(this.f89a, e.ASSEMBLE_PUSH_HUAWEI));
                }
                com.xiaomi.channel.commonutils.logger.b.c("hw manager add to list");
            } else if (m80a(e.ASSEMBLE_PUSH_HUAWEI) && (abstractPushManagerA = a(e.ASSEMBLE_PUSH_HUAWEI)) != null) {
                m79a(e.ASSEMBLE_PUSH_HUAWEI);
                abstractPushManagerA.unregister();
            }
            if (this.f90a.getOpenFCMPush()) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("ASSEMBLE_PUSH : ");
                sb2.append(" FCM user switch : " + this.f90a.getOpenFCMPush() + " FCM online switch : " + i.m84a(this.f89a, e.ASSEMBLE_PUSH_FCM) + " FCM isSupport : " + n.m87a(this.f89a));
                com.xiaomi.channel.commonutils.logger.b.m22a(sb2.toString());
            }
            if (this.f90a.getOpenFCMPush() && i.m84a(this.f89a, e.ASSEMBLE_PUSH_FCM) && n.m87a(this.f89a)) {
                if (!m80a(e.ASSEMBLE_PUSH_FCM)) {
                    a(e.ASSEMBLE_PUSH_FCM, aj.a(this.f89a, e.ASSEMBLE_PUSH_FCM));
                }
                com.xiaomi.channel.commonutils.logger.b.c("fcm manager add to list");
            } else if (m80a(e.ASSEMBLE_PUSH_FCM) && (abstractPushManagerA2 = a(e.ASSEMBLE_PUSH_FCM)) != null) {
                m79a(e.ASSEMBLE_PUSH_FCM);
                abstractPushManagerA2.unregister();
            }
            if (this.f90a.getOpenCOSPush()) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("ASSEMBLE_PUSH : ");
                sb3.append(" COS user switch : " + this.f90a.getOpenCOSPush() + " COS online switch : " + i.m84a(this.f89a, e.ASSEMBLE_PUSH_COS) + " COS isSupport : " + n.b(this.f89a));
                com.xiaomi.channel.commonutils.logger.b.m22a(sb3.toString());
            }
            if (this.f90a.getOpenCOSPush() && i.m84a(this.f89a, e.ASSEMBLE_PUSH_COS) && n.b(this.f89a)) {
                a(e.ASSEMBLE_PUSH_COS, aj.a(this.f89a, e.ASSEMBLE_PUSH_COS));
            } else if (m80a(e.ASSEMBLE_PUSH_COS) && (abstractPushManagerA3 = a(e.ASSEMBLE_PUSH_COS)) != null) {
                m79a(e.ASSEMBLE_PUSH_COS);
                abstractPushManagerA3.unregister();
            }
            if (this.f90a.getOpenFTOSPush() && i.m84a(this.f89a, e.ASSEMBLE_PUSH_FTOS) && n.c(this.f89a)) {
                a(e.ASSEMBLE_PUSH_FTOS, aj.a(this.f89a, e.ASSEMBLE_PUSH_FTOS));
            } else {
                if (!m80a(e.ASSEMBLE_PUSH_FTOS) || (abstractPushManagerA4 = a(e.ASSEMBLE_PUSH_FTOS)) == null) {
                    return;
                }
                m79a(e.ASSEMBLE_PUSH_FTOS);
                abstractPushManagerA4.unregister();
            }
        }
    }

    public AbstractPushManager a(e eVar) {
        return this.f91a.get(eVar);
    }

    public void a(PushConfiguration pushConfiguration) {
        this.f90a = pushConfiguration;
        this.f92a = com.xiaomi.push.service.aq.a(this.f89a).a(ho.AggregatePushSwitch.a(), true);
        if (this.f90a.getOpenHmsPush() || this.f90a.getOpenFCMPush() || this.f90a.getOpenCOSPush()) {
            com.xiaomi.push.service.aq.a(this.f89a).a(new g(this, 101, "assemblePush"));
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m79a(e eVar) {
        this.f91a.remove(eVar);
    }

    public void a(e eVar, AbstractPushManager abstractPushManager) {
        if (abstractPushManager != null) {
            if (this.f91a.containsKey(eVar)) {
                this.f91a.remove(eVar);
            }
            this.f91a.put(eVar, abstractPushManager);
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m80a(e eVar) {
        return this.f91a.containsKey(eVar);
    }

    public boolean b(e eVar) {
        boolean openCOSPush = false;
        switch (h.a[eVar.ordinal()]) {
            case 1:
                if (this.f90a != null) {
                    return this.f90a.getOpenHmsPush();
                }
                return false;
            case 2:
                if (this.f90a != null) {
                    return this.f90a.getOpenFCMPush();
                }
                return false;
            case 3:
                if (this.f90a != null) {
                    openCOSPush = this.f90a.getOpenCOSPush();
                    break;
                }
                break;
            case 4:
                break;
            default:
                return false;
        }
        return this.f90a != null ? this.f90a.getOpenFTOSPush() : openCOSPush;
    }

    @Override // com.xiaomi.mipush.sdk.AbstractPushManager
    public void register() {
        com.xiaomi.channel.commonutils.logger.b.m22a("ASSEMBLE_PUSH : assemble push register");
        if (this.f91a.size() <= 0) {
            a();
        }
        if (this.f91a.size() > 0) {
            for (AbstractPushManager abstractPushManager : this.f91a.values()) {
                if (abstractPushManager != null) {
                    abstractPushManager.register();
                }
            }
            i.m81a(this.f89a);
        }
    }

    @Override // com.xiaomi.mipush.sdk.AbstractPushManager
    public void unregister() {
        com.xiaomi.channel.commonutils.logger.b.m22a("ASSEMBLE_PUSH : assemble push unregister");
        for (AbstractPushManager abstractPushManager : this.f91a.values()) {
            if (abstractPushManager != null) {
                abstractPushManager.unregister();
            }
        }
        this.f91a.clear();
    }
}

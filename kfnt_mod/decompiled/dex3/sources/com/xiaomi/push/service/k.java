package com.xiaomi.push.service;

import android.text.TextUtils;
import com.xiaomi.push.Cif;
import com.xiaomi.push.hj;
import com.xiaomi.push.hw;
import com.xiaomi.push.ii;
import com.xiaomi.push.it;
import com.xiaomi.push.service.XMPushService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class k extends XMPushService.i {
    final /* synthetic */ j a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ String f958a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ List f959a;
    final /* synthetic */ String b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    k(j jVar, int i, String str, List list, String str2) {
        super(i);
        this.a = jVar;
        this.f958a = str;
        this.f959a = list;
        this.b = str2;
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    public String a() {
        return "Send tiny data.";
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    /* renamed from: a */
    public void mo342a() throws NumberFormatException {
        String strA = this.a.a(this.f958a);
        ArrayList<ii> arrayListA = bo.a(this.f959a, this.f958a, strA, 32768);
        if (arrayListA == null) {
            com.xiaomi.channel.commonutils.logger.b.d("TinyData LongConnUploader.upload Get a null XmPushActionNotification list when TinyDataHelper.pack() in XMPushService.");
            return;
        }
        Iterator<ii> it = arrayListA.iterator();
        while (it.hasNext()) {
            ii next = it.next();
            next.a("uploadWay", "longXMPushService");
            Cif cifA = y.a(this.f958a, strA, next, hj.Notification);
            if (!TextUtils.isEmpty(this.b) && !TextUtils.equals(this.f958a, this.b)) {
                if (cifA.m461a() == null) {
                    hw hwVar = new hw();
                    hwVar.a("-1");
                    cifA.a(hwVar);
                }
                cifA.m461a().b("ext_traffic_source_pkg", this.b);
            }
            this.a.a.a(this.f958a, it.a(cifA), true);
        }
    }
}

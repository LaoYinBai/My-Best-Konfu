package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.hj;
import com.xiaomi.push.ht;
import com.xiaomi.push.hw;
import com.xiaomi.push.ii;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MiPushClient4VR {
    public static void uploadData(Context context, String str) {
        ii iiVar = new ii();
        iiVar.c(ht.VRUpload.f509a);
        iiVar.b(b.m63a(context).m64a());
        iiVar.d(context.getPackageName());
        iiVar.a("data", str);
        iiVar.a(com.xiaomi.push.service.at.a());
        an.a(context).a((an) iiVar, hj.Notification, (hw) null);
    }
}

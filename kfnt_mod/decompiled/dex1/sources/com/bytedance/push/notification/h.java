package com.bytedance.push.notification;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import com.bytedance.push.PushBody;
import com.bytedance.push.c.m;
import com.bytedance.push.notification.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class h extends a {

    /* renamed from: a, reason: collision with root package name */
    private final m f4907a;

    /* renamed from: b, reason: collision with root package name */
    private final b f4908b;

    public h(m mVar, com.bytedance.push.b.a aVar) {
        this.f4907a = mVar;
        this.f4908b = new b(aVar);
    }

    @Override // com.bytedance.push.notification.a
    void a(Context context, int i, PushBody pushBody) {
        if (this.f4907a != null ? this.f4907a.a(context, i, pushBody) : false) {
            return;
        }
        super.a(context, i, pushBody);
    }

    @Override // com.bytedance.push.notification.a
    public void a(String str, a.InterfaceC0147a interfaceC0147a) {
        this.f4908b.a(new com.bytedance.push.b.c(Uri.parse(str), 0, 0, null), interfaceC0147a);
    }

    @Override // com.bytedance.push.notification.a
    public Intent b(Context context, int i, PushBody pushBody) {
        Intent intent = new Intent(context.getApplicationContext(), (Class<?>) PushActivity.class);
        intent.putExtra("push_body", pushBody.getOriginData());
        intent.putExtra("from_notification", true);
        intent.putExtra("message_from", i);
        intent.addFlags(268435456);
        return intent;
    }

    @Override // com.bytedance.push.notification.a
    protected Notification a(Context context, int i, PushBody pushBody, Bitmap bitmap) throws PackageManager.NameNotFoundException {
        Notification notificationA = super.a(context, i, pushBody, bitmap);
        if (this.f4907a != null) {
            this.f4907a.a(context, i, pushBody, bitmap, notificationA);
        }
        return notificationA;
    }
}

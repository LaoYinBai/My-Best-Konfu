package com.bytedance.push.third;

import android.content.Context;
import android.content.Intent;
import com.ss.android.message.b;
import com.ss.android.pushmanager.IMessageContext;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f implements com.ss.android.message.b {

    /* renamed from: a, reason: collision with root package name */
    private static volatile f f4977a;

    /* renamed from: b, reason: collision with root package name */
    private List<com.ss.android.message.b> f4978b = Collections.emptyList();

    /* renamed from: c, reason: collision with root package name */
    private b.a f4979c;

    public void a(b.a aVar) {
    }

    public static f a() {
        if (f4977a == null) {
            synchronized (f.class) {
                if (f4977a == null) {
                    f4977a = new f();
                }
            }
        }
        return f4977a;
    }

    private f() {
    }

    public void a(Context context, IMessageContext iMessageContext) {
        this.f4979c = new b.a() { // from class: com.bytedance.push.third.f.1
        };
        if (this.f4978b != null) {
            for (com.ss.android.message.b bVar : this.f4978b) {
                try {
                    bVar.a(this.f4979c);
                    bVar.a(context, iMessageContext);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    public void a(Context context, Map<String, String> map) {
        if (this.f4978b != null) {
            Iterator<com.ss.android.message.b> it = this.f4978b.iterator();
            while (it.hasNext()) {
                try {
                    it.next().a(context, map);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    public void onNotifyServiceCreate(Context context) {
        if (this.f4978b != null) {
            Iterator<com.ss.android.message.b> it = this.f4978b.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onNotifyServiceCreate(context);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    public void onNotifyServiceStart(Intent intent) {
        if (this.f4978b != null) {
            Iterator<com.ss.android.message.b> it = this.f4978b.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onNotifyServiceStart(intent);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    public void onNotifyDestroy() {
        if (this.f4978b != null) {
            Iterator<com.ss.android.message.b> it = this.f4978b.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onNotifyDestroy();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    public void a(List<com.ss.android.message.b> list) {
        this.f4978b = list;
    }
}

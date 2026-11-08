package com.xiaomi.push;

import android.content.Context;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class av implements ar {
    private Context a;

    /* renamed from: a, reason: collision with other field name */
    private Class<?> f134a;

    /* renamed from: a, reason: collision with other field name */
    private Object f135a;

    /* renamed from: a, reason: collision with other field name */
    private Method f136a = null;
    private Method b = null;
    private Method c = null;
    private Method d = null;

    public av(Context context) {
        this.a = context;
        a(context);
    }

    private String a(Context context, Method method) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (this.f135a == null || method == null) {
            return null;
        }
        try {
            Object objInvoke = method.invoke(this.f135a, context);
            if (objInvoke != null) {
                return (String) objInvoke;
            }
            return null;
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.a("miui invoke error", e);
            return null;
        }
    }

    private void a(Context context) {
        try {
            this.f134a = t.a(context, "com.android.id.impl.IdProviderImpl");
            this.f135a = this.f134a.newInstance();
            this.f136a = this.f134a.getMethod("getUDID", Context.class);
            this.b = this.f134a.getMethod("getOAID", Context.class);
            this.c = this.f134a.getMethod("getVAID", Context.class);
            this.d = this.f134a.getMethod("getAAID", Context.class);
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.a("miui load class error", e);
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public static boolean m104a(Context context) {
        return "com.xiaomi.xmsf".equals(context.getPackageName());
    }

    @Override // com.xiaomi.push.ar
    /* renamed from: a */
    public String mo100a() {
        return null;
    }

    @Override // com.xiaomi.push.ar
    /* renamed from: a */
    public boolean mo101a() {
        return (this.f134a == null || this.f135a == null) ? false : true;
    }

    @Override // com.xiaomi.push.ar
    /* renamed from: b */
    public String mo102b() {
        return a(this.a, this.b);
    }

    @Override // com.xiaomi.push.ar
    public String c() {
        return a(this.a, this.c);
    }

    @Override // com.xiaomi.push.ar
    public String d() {
        return a(this.a, this.d);
    }
}

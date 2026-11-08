package b.b.b;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import b.b.b.p;
import b.b.b.x;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class c<SERVICE> implements p {

    /* renamed from: a, reason: collision with root package name */
    public final String f1158a;

    /* renamed from: b, reason: collision with root package name */
    public b<Boolean> f1159b = new a();

    public class a extends b<Boolean> {
        public a() {
        }

        @Override // b.b.b.b
        public Boolean a(Object[] objArr) {
            return Boolean.valueOf(b.b.a.a.b.a((Context) objArr[0], c.this.f1158a));
        }
    }

    public c(String str) {
        this.f1158a = str;
    }

    public abstract Intent a(Context context);

    public abstract x.b<SERVICE, String> a();

    @Override // b.b.b.p
    public boolean b(Context context) {
        if (context == null) {
            return false;
        }
        return this.f1159b.b(context).booleanValue();
    }

    @Override // b.b.b.p
    public p.a c(Context context) {
        String str = (String) new x(context, a(context), a()).a();
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        p.a aVar = new p.a();
        aVar.f1176b = str;
        return aVar;
    }
}

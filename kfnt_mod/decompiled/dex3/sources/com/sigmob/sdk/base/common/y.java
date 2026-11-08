package com.sigmob.sdk.base.common;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.common.z;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.common.utils.m;
import java.util.EnumSet;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class y {
    private static final b a = new b() { // from class: com.sigmob.sdk.base.common.y.1
        @Override // com.sigmob.sdk.base.common.y.b
        public void a(String str, x xVar) {
        }

        @Override // com.sigmob.sdk.base.common.y.b
        public void b(String str, x xVar) {
        }

        @Override // com.sigmob.sdk.base.common.y.b
        public void c(String str, x xVar) {
        }

        @Override // com.sigmob.sdk.base.common.y.b
        public void d(String str, x xVar) {
        }
    };
    private static final c b = new c() { // from class: com.sigmob.sdk.base.common.y.2
        @Override // com.sigmob.sdk.base.common.y.c
        public void a() {
        }

        @Override // com.sigmob.sdk.base.common.y.c
        public void b() {
        }

        @Override // com.sigmob.sdk.base.common.y.c
        public void c() {
        }
    };
    private final EnumSet<x> c;
    private final b d;
    private final b e;
    private final c f;
    private final BaseAdUnit g;
    private final boolean h;
    private boolean i;
    private boolean j;
    private boolean k;

    public static class a {
        private EnumSet<x> a = EnumSet.of(x.NOOP);
        private b b = y.a;
        private b c = y.a;
        private c d = y.b;
        private boolean e = false;
        private boolean f = false;
        private BaseAdUnit g;

        public a a(x xVar, x... xVarArr) {
            this.a = EnumSet.of(xVar, xVarArr);
            return this;
        }

        public a a(b bVar) {
            this.b = bVar;
            return this;
        }

        public a a(BaseAdUnit baseAdUnit) {
            this.g = baseAdUnit;
            return this;
        }

        public a a(boolean z) {
            this.e = z;
            return this;
        }

        public y a() {
            return new y(this.a, this.b, this.c, this.d, this.e, this.g, this.f);
        }

        public a b(boolean z) {
            this.f = z;
            return this;
        }
    }

    public interface b {
        void a(String str, x xVar);

        void b(String str, x xVar);

        void c(String str, x xVar);

        void d(String str, x xVar);
    }

    public interface c {
        void a();

        void b();

        void c();
    }

    private y(EnumSet<x> enumSet, b bVar, b bVar2, c cVar, boolean z, BaseAdUnit baseAdUnit, boolean z2) {
        this.c = EnumSet.copyOf((EnumSet) enumSet);
        this.d = bVar;
        this.e = bVar2;
        this.f = cVar;
        this.h = z;
        this.g = baseAdUnit;
        this.i = false;
        this.j = false;
        this.k = z2;
    }

    private void a(Context context, String str, boolean z) {
        m.a.a(context);
        a(context, str, z, (Iterable<String>) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, x xVar, String str2, Throwable th) {
        m.a.a(str2);
        if (xVar == null) {
            xVar = x.NOOP;
        }
        SigmobLog.d(str2, th);
        this.d.d(str, xVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(Context context, String str, boolean z, Iterable<String> iterable) {
        if (TextUtils.isEmpty(str)) {
            a(str, (x) null, "Attempted to handle empty url.", (Throwable) null);
            return false;
        }
        x xVar = x.NOOP;
        Uri uri = Uri.parse(str);
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            x xVar2 = (x) it.next();
            if (xVar2.a(uri)) {
                try {
                    xVar2.a(this, context, uri, z, this.g);
                    if (!this.i && !this.j && !x.IGNORE_ABOUT_SCHEME.equals(xVar2)) {
                        this.d.c(uri.toString(), xVar2);
                        this.i = true;
                    }
                    return true;
                } catch (Throwable th) {
                    SigmobLog.d(th.getMessage(), th);
                    xVar = xVar2;
                }
            }
        }
        try {
            a(str, xVar, "Link ignored. Unable to handle url: " + str, (Throwable) null);
        } catch (Throwable th2) {
            SigmobLog.e("handleResolvedUrl eroor", th2);
        }
        return false;
    }

    c a() {
        return this.f;
    }

    public void a(Context context, String str) {
        m.a.a(context);
        a(context, str, true);
    }

    public void a(Context context, String str, String str2) {
        m.a.a(context);
        if (!TextUtils.isEmpty(str)) {
            Uri uri = Uri.parse(str);
            x xVar = x.FOLLOW_DEEP_LINK;
            if (xVar.a(uri)) {
                try {
                    xVar.a(this, context, uri, true, this.g);
                    if (this.i || this.j || x.IGNORE_ABOUT_SCHEME.equals(xVar)) {
                        return;
                    }
                    this.d.a(uri.toString(), xVar);
                    this.i = true;
                    return;
                } catch (com.sigmob.sdk.common.c.b e) {
                    e.printStackTrace();
                    if (TextUtils.isEmpty(str2)) {
                        this.d.d(str, xVar);
                    } else {
                        this.d.b(uri.toString(), xVar);
                    }
                }
            }
        }
        a(context, str2, true);
    }

    void a(final Context context, final String str, final boolean z, final Iterable<String> iterable) {
        m.a.a(context);
        if (TextUtils.isEmpty(str)) {
            a(str, (x) null, "Attempted to handle empty url.", (Throwable) null);
            return;
        }
        z.a aVar = new z.a() { // from class: com.sigmob.sdk.base.common.y.3
            @Override // com.sigmob.sdk.base.common.z.a
            public void a(String str2) {
                y.this.j = false;
                y.this.b(context, str2, z, iterable);
            }

            @Override // com.sigmob.sdk.base.common.z.a
            public void a(String str2, Throwable th) {
                y.this.j = false;
                y.this.a(str, (x) null, str2, th);
            }
        };
        if (this.k) {
            aVar.a(str);
        } else {
            z.a(str, aVar);
        }
        this.j = true;
    }

    boolean b() {
        return this.h;
    }
}

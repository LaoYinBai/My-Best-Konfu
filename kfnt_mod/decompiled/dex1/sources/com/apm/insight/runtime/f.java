package com.apm.insight.runtime;

import androidx.annotation.Nullable;
import com.apm.insight.entity.Header;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f {

    /* renamed from: b, reason: collision with root package name */
    private static final f f3032b = new f() { // from class: com.apm.insight.runtime.f.1

        /* renamed from: a, reason: collision with root package name */
        Header f3034a = null;

        @Override // com.apm.insight.runtime.f
        @Nullable
        public Object b(String str) {
            if (this.f3034a == null) {
                this.f3034a = Header.b(com.apm.insight.h.g());
            }
            return this.f3034a.f().opt(str);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private f f3033a;

    f() {
        this(f3032b);
    }

    f(f fVar) {
        this.f3033a = null;
        this.f3033a = fVar;
    }

    @Nullable
    public Object a(String str) {
        if (this.f3033a != null) {
            return this.f3033a.a(str);
        }
        return null;
    }

    @Nullable
    public Object b(String str) {
        if (this.f3033a != null) {
            return this.f3033a.b(str);
        }
        return null;
    }
}

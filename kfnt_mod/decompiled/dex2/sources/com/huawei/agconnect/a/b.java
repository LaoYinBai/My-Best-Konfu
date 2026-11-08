package com.huawei.agconnect.a;

import android.content.Context;
import com.huawei.agconnect.a.a.h;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class b {
    private final Context a;
    private InputStream b;

    public abstract InputStream a(Context context);

    public b(Context context) {
        this.a = context;
    }

    public final void a() throws IOException {
        h.a(this.b);
    }

    public InputStream b() {
        if (this.b == null) {
            this.b = a(this.a);
        }
        return this.b;
    }
}

package com.huawei.agconnect.a.a;

import android.content.Context;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends com.huawei.agconnect.a.a {
    private final Context a;
    private com.huawei.agconnect.a.b b;
    private volatile b c;
    private final Object d = new Object();

    public a(Context context) {
        this.a = context;
    }

    @Override // com.huawei.agconnect.a.a
    public String a(String str) {
        return a(str, (String) null);
    }

    public String a(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("path must not be null.");
        }
        if (this.c == null) {
            synchronized (this.d) {
                if (this.c == null) {
                    if (this.b != null) {
                        this.c = new d(this.b.b());
                        this.b.a();
                        this.b = null;
                    } else {
                        this.c = new g(this.a);
                    }
                }
            }
        }
        return this.c.a(b(str), str2);
    }

    @Override // com.huawei.agconnect.a.a
    public void a(InputStream inputStream) {
        a(a(this.a, inputStream));
    }

    private static com.huawei.agconnect.a.b a(Context context, final InputStream inputStream) {
        return new com.huawei.agconnect.a.b(context) { // from class: com.huawei.agconnect.a.a.a.1
            @Override // com.huawei.agconnect.a.b
            public InputStream a(Context context2) {
                return inputStream;
            }
        };
    }

    public void a(com.huawei.agconnect.a.b bVar) {
        this.b = bVar;
    }

    private static String b(String str) {
        int i = 0;
        if (str.length() > 0) {
            while (str.charAt(i) == '/') {
                i++;
            }
        }
        return '/' + str.substring(i);
    }
}

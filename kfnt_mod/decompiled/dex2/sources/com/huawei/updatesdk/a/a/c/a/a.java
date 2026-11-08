package com.huawei.updatesdk.a.a.c.a;

import android.content.Context;
import com.huawei.updatesdk.a.a.c.e;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends com.huawei.updatesdk.a.b.d.a.b {
    private String abis_;
    private String deviceFeatures_;
    private int dpi_;
    private String preferLan_;

    /* renamed from: com.huawei.updatesdk.a.a.c.a.a$a, reason: collision with other inner class name */
    public static class C0011a {
        private final Context a;
        private boolean b;
        private Set<String> c;
        private String[] d;
        private boolean e;

        public C0011a(Context context) {
            this.a = context;
        }

        private String b() {
            List arrayList = new ArrayList(b.c(this.a));
            if (this.c != null) {
                for (String str : this.c) {
                    if (!arrayList.contains(str)) {
                        arrayList.add(str);
                    }
                }
            }
            if (this.e) {
                arrayList = b.a((List<String>) arrayList, this.d);
            }
            return e.a((List<String>) arrayList, ",");
        }

        public C0011a a(boolean z) {
            this.b = z;
            return this;
        }

        public a a() {
            a aVar = new a();
            aVar.abis_ = e.a(b.c(), ",");
            aVar.dpi_ = Integer.parseInt(b.a(this.a));
            aVar.preferLan_ = b();
            if (this.b) {
                aVar.deviceFeatures_ = b.b(this.a);
            }
            return aVar;
        }
    }

    private a() {
    }
}

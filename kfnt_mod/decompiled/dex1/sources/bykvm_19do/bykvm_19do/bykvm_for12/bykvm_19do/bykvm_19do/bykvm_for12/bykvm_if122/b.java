package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_if122;

import android.text.TextUtils;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.v;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.y;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class b {

    /* renamed from: a, reason: collision with root package name */
    protected v f1442a;

    /* renamed from: b, reason: collision with root package name */
    protected String f1443b = null;

    /* renamed from: c, reason: collision with root package name */
    protected final Map<String, String> f1444c = new HashMap();
    protected String d = null;

    public b(v vVar) {
        this.f1442a = vVar;
        a(UUID.randomUUID().toString());
    }

    public void a(String str, String str2) {
        this.f1444c.put(str, str2);
    }

    public void b(String str) {
        this.d = str;
    }

    public void a(String str) {
        this.f1443b = str;
    }

    public String a() {
        return this.f1443b;
    }

    protected void a(y.a aVar) {
        if (aVar != null && this.f1444c.size() > 0) {
            for (Map.Entry<String, String> entry : this.f1444c.entrySet()) {
                String key = entry.getKey();
                if (!TextUtils.isEmpty(key)) {
                    String value = entry.getValue();
                    if (value == null) {
                        value = "";
                    }
                    aVar.a(key, value);
                }
            }
        }
    }
}

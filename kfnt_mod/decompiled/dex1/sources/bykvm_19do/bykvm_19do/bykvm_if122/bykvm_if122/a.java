package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122;

import android.content.Context;
import android.location.Address;
import anet.channel.strategy.dispatch.DispatchConstants;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.n;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.u;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_for12.b {

    /* renamed from: a, reason: collision with root package name */
    private Context f1964a;

    /* renamed from: c, reason: collision with root package name */
    String f1966c = "sp_multi_ttmadnet_config";

    /* renamed from: b, reason: collision with root package name */
    private final u f1965b = u.a(this.f1966c, g());

    public a(Context context) {
        this.f1964a = context;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_for12.b
    public Address a(Context context) {
        return null;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_for12.b
    public String a(Context context, String str, String str2) {
        return this.f1965b.a(str, str2);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_for12.b
    public String b() {
        return DispatchConstants.ANDROID;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_for12.b
    public int c() {
        return 4741;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_for12.b
    public String d() {
        return "msdk";
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_for12.b
    public int e() {
        return com.bytedance.msdk.base.b.f4462a;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_for12.b
    public String f() {
        return n.d(this.f1964a);
    }

    public Context g() {
        return this.f1964a;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_for12.b
    public void a(Context context, Map<String, ?> map) {
        if (map != null) {
            try {
                for (Map.Entry<String, ?> entry : map.entrySet()) {
                    Object value = entry.getValue();
                    if (value instanceof Integer) {
                        this.f1965b.b(entry.getKey(), ((Integer) value).intValue());
                    } else if (value instanceof Long) {
                        this.f1965b.b(entry.getKey(), ((Long) value).longValue());
                    } else if (value instanceof Float) {
                        this.f1965b.b(entry.getKey(), ((Float) value).floatValue());
                    } else if (value instanceof Boolean) {
                        this.f1965b.b(entry.getKey(), ((Boolean) value).booleanValue());
                    } else if (value instanceof String) {
                        this.f1965b.b(entry.getKey(), (String) value);
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_for12.b
    public String[] a() {
        return new String[]{"dm.toutiao.com", "dm.bytedance.com", "dm.pstatp.com"};
    }
}

package b.b.a.k;

import com.bytedance.sdk.account.execute.ApiRequest;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class u {

    /* renamed from: a, reason: collision with root package name */
    public Object f1148a;

    public u() {
        try {
            this.f1148a = Class.forName("android.os.SystemProperties").newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (InstantiationException e3) {
            e3.printStackTrace();
        }
    }

    public String a(String str) {
        try {
            return (String) this.f1148a.getClass().getMethod(ApiRequest.METHOD_GET, String.class).invoke(this.f1148a, str);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception unused) {
            return "";
        }
    }
}

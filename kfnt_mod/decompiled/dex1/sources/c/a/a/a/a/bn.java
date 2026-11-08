package c.a.a.a.a;

import android.content.Context;
import android.util.Log;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class bn extends aw {

    /* renamed from: a, reason: collision with root package name */
    private Context f2090a;

    public bn(Context context, String str) {
        super(context, str);
        this.f2090a = null;
        this.f2090a = context;
        this.f2073c = at.a() + "/v2/s" + a();
    }

    public String a() {
        String str = "";
        try {
            str = this.f2090a.getPackageManager().getPackageInfo(this.f2090a.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str2 = "?os=0&ver=1.6.1&m=2&app_ver=" + str;
        String country = "";
        try {
            country = this.f2090a.getResources().getConfiguration().locale.getCountry();
        } catch (Throwable unused) {
        }
        return ((str2 + "&region=" + country) + "&aid=" + com.ss.sys.ces.b.f) + "&did=" + com.ss.sys.ces.b.j;
    }

    @Override // c.a.a.a.a.aw
    public boolean a(int i, byte[] bArr) {
        String str;
        if (i == 200 && bArr != null) {
            try {
                if (bArr.length > 0 && (str = (String) com.ss.sys.ces.a.meta(200, this.f2090a, bArr)) != null && str.length() > 0) {
                    if (str.equalsIgnoreCase("AYE")) {
                        Log.d("CZL_META", "[+] Make it happen");
                        com.ss.sys.ces.b.l = true;
                        return true;
                    }
                    Log.e("CZL_META", "[-] Abandon ship!");
                    com.ss.sys.ces.b.l = false;
                }
            } catch (Throwable unused) {
                com.ss.sys.ces.b.l = false;
                Log.e("CZL_META", "[-] It's a trap!");
            }
        }
        return false;
    }
}

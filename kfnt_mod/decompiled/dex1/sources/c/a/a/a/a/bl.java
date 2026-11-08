package c.a.a.a.a;

import android.content.Context;
import android.util.Log;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class bl extends aw {

    /* renamed from: a, reason: collision with root package name */
    private Context f2089a;

    public bl(Context context, String str) {
        super(context, str);
        this.f2089a = null;
        this.f2089a = context;
        this.f2073c = at.a() + "/v2/r" + a();
    }

    public String a() {
        String str = "";
        try {
            str = this.f2089a.getPackageManager().getPackageInfo(this.f2089a.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str2 = "?os=0&ver=1.6.1&m=2&app_ver=" + str;
        String country = "";
        try {
            country = this.f2089a.getResources().getConfiguration().locale.getCountry();
        } catch (Throwable unused) {
        }
        return ((str2 + "&region=" + country) + "&aid=" + com.ss.sys.ces.b.f) + "&did=" + com.ss.sys.ces.b.j;
    }

    @Override // c.a.a.a.a.aw
    public boolean a(int i, byte[] bArr) {
        if (i == 200 && bArr != null) {
            try {
                if (bArr.length > 0 && bArr != null && bArr.length > 0) {
                    if (bArr.length == 37) {
                        Log.d("CZL_META", "[+] Go Go Go");
                        com.ss.sys.ces.b.k = true;
                    } else {
                        Log.e("CZL_META", "[-] We need some help here!");
                    }
                }
            } catch (Throwable unused) {
                com.ss.sys.ces.b.l = false;
                Log.e("CZL_META", "[-] you want some pissed of me , boy?");
            }
        }
        return true;
    }
}

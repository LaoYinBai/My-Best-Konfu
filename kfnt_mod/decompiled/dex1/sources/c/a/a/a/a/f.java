package c.a.a.a.a;

import android.content.Context;
import android.util.Log;
import anet.channel.util.HttpConstant;
import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.Callback;
import com.bytedance.retrofit2.SsResponse;
import com.bytedance.retrofit2.client.Header;
import com.bytedance.retrofit2.mime.TypedInput;
import com.bytedance.ttnet.utils.RetrofitUtils;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    public String f2099a;

    /* renamed from: b, reason: collision with root package name */
    public Context f2100b;

    /* renamed from: c, reason: collision with root package name */
    public String f2101c;

    class a implements Callback<TypedInput> {
        a() {
        }

        public void a(Call<TypedInput> call, SsResponse<TypedInput> ssResponse) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[1024];
                InputStream inputStreamIn = ((TypedInput) ssResponse.body()).in();
                while (true) {
                    int i = inputStreamIn.read(bArr, 0, 1024);
                    if (i <= 0) {
                        break;
                    } else {
                        byteArrayOutputStream.write(bArr, 0, i);
                    }
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                if (byteArray != null) {
                    try {
                        String str = (String) com.ss.sys.ces.a.meta(200, f.this.f2100b, byteArray);
                        if (str != null && str.length() > 0) {
                            if (str.equalsIgnoreCase("AYE")) {
                                Log.d("CZL_META", "[+] Make it happen");
                                com.ss.sys.ces.b.l = true;
                            } else {
                                Log.e("CZL_META", "[-] Abandon ship!");
                                com.ss.sys.ces.b.l = false;
                            }
                        }
                    } catch (Throwable unused) {
                        com.ss.sys.ces.b.l = false;
                        Log.e("CZL_META", "[-] It's a trap!");
                    }
                }
            } catch (Throwable th) {
                com.ss.sys.ces.b.l = false;
                th.printStackTrace();
            }
        }

        public void a(Call<TypedInput> call, Throwable th) {
            Log.e("CZL_META", "[-] insufficient energy.");
        }
    }

    public f(Context context, String str) {
        this.f2099a = "";
        this.f2100b = null;
        this.f2101c = "";
        this.f2099a = (str == null || str.length() <= 0) ? "" : str;
        this.f2100b = context;
        this.f2101c = at.a() + "/v2/s" + a();
    }

    public String a() {
        String str = "";
        try {
            str = "" + this.f2100b.getPackageManager().getPackageInfo(this.f2100b.getPackageName(), 0).versionName;
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return ((("?os=0&ver=1.6.1&m=2&app_ver=" + str) + "&region=" + this.f2100b.getResources().getConfiguration().locale.getCountry()) + "&aid=" + com.ss.sys.ces.b.f) + "&did=" + com.ss.sys.ces.b.j;
    }

    public void b() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Header(HttpConstant.COOKIE, "sessionid=" + this.f2099a));
        ((bp) RetrofitUtils.getSsRetrofit(this.f2101c).create(bp.class)).a(this.f2101c, arrayList).enqueue(new a());
    }
}

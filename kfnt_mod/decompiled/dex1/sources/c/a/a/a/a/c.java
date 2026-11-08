package c.a.a.a.a;

import android.content.Context;
import android.util.Log;
import anet.channel.util.HttpConstant;
import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.Callback;
import com.bytedance.retrofit2.SsResponse;
import com.bytedance.retrofit2.client.Header;
import com.bytedance.retrofit2.mime.TypedByteArray;
import com.bytedance.retrofit2.mime.TypedInput;
import com.bytedance.sdk.b.d.a;
import com.bytedance.ttnet.utils.RetrofitUtils;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    public String f2094a;

    /* renamed from: b, reason: collision with root package name */
    public Context f2095b;

    /* renamed from: c, reason: collision with root package name */
    public String f2096c;

    class a implements Callback<TypedInput> {
        a(c cVar) {
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
                    if (byteArray.length != 37) {
                        Log.e("CZL_META", "[-] We need some help here!");
                    } else {
                        Log.d("CZL_META", "[+] Go Go Go");
                        com.ss.sys.ces.b.k = true;
                    }
                }
            } catch (Throwable unused) {
                Log.e("CZL_META", "[-] you want some pissed of me , boy?");
            }
        }

        public void a(Call<TypedInput> call, Throwable th) {
            Log.e("CZL_META", "[-] Ahhhhh...");
        }
    }

    public c(Context context, String str) {
        this.f2094a = "";
        this.f2095b = null;
        this.f2096c = "";
        this.f2094a = (str == null || str.length() <= 0) ? "" : str;
        this.f2095b = context;
        this.f2096c = at.a() + "/v2/r" + a();
    }

    public String a() {
        String str = "";
        try {
            str = "" + this.f2095b.getPackageManager().getPackageInfo(this.f2095b.getPackageName(), 0).versionName;
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return ((("?os=0&ver=1.6.1&m=2&app_ver=" + str) + "&region=" + this.f2095b.getResources().getConfiguration().locale.getCountry()) + "&aid=" + com.ss.sys.ces.b.f) + "&did=" + com.ss.sys.ces.b.j;
    }

    public void a(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Header(HttpConstant.COOKIE, "sessionid=" + this.f2094a));
        ((bp) RetrofitUtils.getSsRetrofit(this.f2096c).create(bp.class)).a(this.f2096c, arrayList, new TypedByteArray(a.f.f5151a, bArr, new String[0])).enqueue(new a(this));
    }
}

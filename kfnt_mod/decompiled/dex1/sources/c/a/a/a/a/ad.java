package c.a.a.a.a;

import android.content.Context;
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
public class ad {

    /* renamed from: a, reason: collision with root package name */
    public String f2048a;

    /* renamed from: b, reason: collision with root package name */
    public String f2049b;

    class a implements Callback<TypedInput> {
        a(ad adVar) {
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
                    com.ss.sys.ck.b.a(new String(byteArray));
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        public void a(Call<TypedInput> call, Throwable th) {
        }
    }

    public ad(Context context, String str, String str2) {
        this.f2048a = "";
        this.f2049b = "";
        if (str == null || str.length() <= 0) {
            this.f2048a = "";
        } else {
            this.f2048a = str;
        }
        this.f2049b = at.a() + "/v.s" + ((str2 == null || str2.length() <= 0) ? "" : str2);
    }

    public void a() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Header(HttpConstant.COOKIE, "sessionid=" + this.f2048a));
        ((x) RetrofitUtils.getSsRetrofit(this.f2049b).create(x.class)).a(this.f2049b, arrayList).enqueue(new a(this));
    }
}

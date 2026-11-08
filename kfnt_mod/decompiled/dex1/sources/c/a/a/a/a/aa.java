package c.a.a.a.a;

import android.content.Context;
import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.Callback;
import com.bytedance.retrofit2.SsResponse;
import com.bytedance.retrofit2.mime.TypedByteArray;
import com.bytedance.retrofit2.mime.TypedInput;
import com.bytedance.ttnet.utils.RetrofitUtils;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class aa {

    /* renamed from: a, reason: collision with root package name */
    public String f2044a;

    class a implements Callback<TypedInput> {
        a(aa aaVar) {
        }

        public void a(Call<TypedInput> call, SsResponse<TypedInput> ssResponse) {
            boolean z;
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[1024];
                InputStream inputStreamIn = ((TypedInput) ssResponse.body()).in();
                while (true) {
                    z = false;
                    int i = inputStreamIn.read(bArr, 0, 1024);
                    if (i <= 0) {
                        break;
                    } else {
                        byteArrayOutputStream.write(bArr, 0, i);
                    }
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                if (byteArray != null && new JSONObject(new String(byteArray)).getInt("code") == 200) {
                    z = true;
                }
                try {
                    u.a(r.f2113a, com.bytedance.bdturing.b.b.d);
                    if (z) {
                        u.a(r.f2114b, com.bytedance.bdturing.b.b.d);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }

        public void a(Call<TypedInput> call, Throwable th) {
        }
    }

    public aa(Context context) {
        this.f2044a = "";
        this.f2044a = at.b() + "/report";
    }

    public void a(byte[] bArr) {
        ((x) RetrofitUtils.getSsRetrofit(this.f2044a).create(x.class)).a(this.f2044a, new TypedByteArray(com.bytedance.b.a.a.c.e.f3610c, bArr, new String[0])).enqueue(new a(this));
    }
}

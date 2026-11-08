package c.a.a.a.a;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class i extends aw {
    public i(Context context, String str, String str2) {
        super(context, str);
        this.f2073c = at.a() + "/v.s" + ((str2 == null || str2.length() <= 0) ? "" : str2);
    }

    @Override // c.a.a.a.a.aw
    public boolean a(int i, byte[] bArr) {
        if (i == 200 && bArr != null) {
            try {
                if (bArr.length > 0) {
                    return com.ss.sys.ck.b.a(new String(bArr));
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return false;
    }
}

package a.a.b.b;

import android.content.Context;
import com.cgs.out.NativeLib;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a extends c {
    public a(Context context, String str) {
        super(context, str);
        this.f333a = "https://cgsap.snssdk.com/casual/aonline";
    }

    @Override // a.a.b.b.c
    public boolean a(int i, byte[] bArr) {
        boolean z = false;
        if (i != 200 || bArr == null) {
            return false;
        }
        try {
            if (bArr.length <= 0) {
                return false;
            }
            z = true;
            NativeLib.native_bridge(16777223, null, bArr);
            return true;
        } catch (Throwable unused) {
            return z;
        }
    }
}

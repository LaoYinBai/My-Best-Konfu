package com.bytedance.pangle.f;

import android.content.pm.Signature;
import android.os.Build;
import androidx.annotation.RequiresApi;
import com.bytedance.pangle.f.c;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.cert.Certificate;

@RequiresApi(api = 21)
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class d {
    public static Signature[] a(Certificate[][] certificateArr) {
        Signature[] signatureArr = new Signature[certificateArr.length];
        for (int i = 0; i < certificateArr.length; i++) {
            if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT <= 28) {
                Constructor constructorA = com.bytedance.pangle.a.b.a.a((Class<?>) Signature.class, (Class<?>[]) new Class[]{Certificate[].class});
                if (constructorA != null) {
                    constructorA.setAccessible(true);
                }
                if (constructorA != null && constructorA.isAccessible()) {
                    try {
                        signatureArr[i] = (Signature) constructorA.newInstance(certificateArr[i]);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e2) {
                        e2.printStackTrace();
                    } catch (InvocationTargetException e3) {
                        e3.printStackTrace();
                    }
                }
            } else {
                signatureArr[i] = new Signature(certificateArr[i][0].getEncoded());
            }
        }
        return signatureArr;
    }

    public static o a(String str) throws q {
        int[] iArr;
        try {
            try {
                c.C0142c c0142cA = c.a(str);
                Signature[] signatureArrA = a(new Certificate[][]{c0142cA.f4606a});
                Signature[] signatureArr = null;
                if (c0142cA.f4607b != null) {
                    signatureArr = new Signature[c0142cA.f4607b.f4604a.size()];
                    iArr = new int[c0142cA.f4607b.f4605b.size()];
                    for (int i = 0; i < signatureArr.length; i++) {
                        signatureArr[i] = new Signature(c0142cA.f4607b.f4604a.get(i).getEncoded());
                        iArr[i] = c0142cA.f4607b.f4605b.get(i).intValue();
                    }
                } else {
                    iArr = null;
                }
                return new o(signatureArrA, 3, signatureArr, iArr);
            } catch (n unused) {
                return new o(a(b.a(str).f4602a));
            } catch (Exception e) {
                throw new q(4, "Failed to collect certificates from " + str + " using APK Signature Scheme v3", e);
            }
        } catch (n unused2) {
            return a.a(str);
        } catch (Exception e2) {
            throw new q(4, "Failed to collect certificates from " + str + " using APK Signature Scheme v2", e2);
        }
    }
}

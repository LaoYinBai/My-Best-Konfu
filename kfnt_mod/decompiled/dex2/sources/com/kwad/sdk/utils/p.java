package com.kwad.sdk.utils;

import android.content.Context;
import android.text.TextUtils;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.api.loader.Loader;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class p {
    private static String a = "";
    private static String b = "";
    private static String c = "";

    public static String a(int i) throws IOException {
        String str;
        String str2;
        Context context = KsAdSDKImpl.get().getContext();
        if (context == null) {
            com.kwad.sdk.core.d.a.e("EncryptUtils", "EncryptUtils getKey context is null");
            return "";
        }
        switch (i) {
            case 0:
                str = "aes_key";
                str2 = a;
                break;
            case 1:
                str = "rsa_public_key";
                str2 = b;
                break;
            case 2:
                str = "rsa_private_key";
                str2 = c;
                break;
            default:
                str2 = "";
                str = "";
                break;
        }
        if (!TextUtils.isEmpty(str2)) {
            return str2;
        }
        if (TextUtils.isEmpty(str)) {
            com.kwad.sdk.core.d.a.e("EncryptUtils", "EncryptUtils getKey get id is error ");
        }
        InputStream inputStreamOpen = null;
        try {
            try {
                inputStreamOpen = Loader.get().getExternalResource().getAssets().open("ksad_common_encrypt_image.png");
            } catch (Throwable th) {
                com.kwad.sdk.core.d.a.e("EncryptUtils", "EncryptUtils getKey get InputStream from loader is null,  e: " + th);
            }
            if (inputStreamOpen == null) {
                inputStreamOpen = context.getAssets().open("ksad_common_encrypt_image.png");
            }
            String strA = a(str, inputStreamOpen);
            if (TextUtils.isEmpty(strA)) {
                com.kwad.sdk.core.d.a.e("EncryptUtils", "EncryptUtils getKey get encryptedKey is invalid ");
            }
            switch (i) {
                case 0:
                    a = strA;
                    return strA;
                case 1:
                    b = strA;
                    return strA;
                case 2:
                    c = strA;
                    return strA;
                default:
                    return strA;
            }
        } catch (Throwable th2) {
            com.kwad.sdk.core.d.a.b(th2);
            return "";
        }
    }

    public static String a(String str, InputStream inputStream) {
        String strB;
        synchronized (p.class) {
            com.kwad.sdk.pngencrypt.o oVar = new com.kwad.sdk.pngencrypt.o(inputStream, true);
            oVar.c();
            strB = oVar.b().b(str);
            oVar.d();
        }
        return strB;
    }
}

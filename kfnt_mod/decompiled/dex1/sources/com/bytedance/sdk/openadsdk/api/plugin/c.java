package com.bytedance.sdk.openadsdk.api.plugin;

import android.text.TextUtils;
import android.util.Base64;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class c {

    /* renamed from: a, reason: collision with root package name */
    private PublicKey f5260a;

    public c() {
        try {
            this.f5260a = a(new String(Base64.decode("TUlJQklqQU5CZ2txaGtpRzl3MEJBUUVGQUFPQ0FROEFNSUlCQ2dLQ0FRRUF5ZXRudE1CRzJuU1JRb1lpT1NLegpCdkZ5cmdwQ1dlT1lnRVZxbG5rNWhLMGlydnNKdVhrZ2paVnFDc0VBRFVQQ0w5K2NlZng1NHoyRGw5dmpEemlwCjVYSGluRUdnZkR1ZzBkcmZyOHRKTElySHlnQlY5M2FTWFg5endBVGlnRnY0dzNrN1RuMStxS01CdUxhbkptSFMKQUttRGMrRCtZSmpYSGF0dWY3YlVhbXlEdDBOZ2pYM0F5d1JUSkErcldPR3RjMHRTWDdaNmlKd3o2bXdzTmZQYQpka3NwVEpmcjVja2dRdVlvWDQybGxteGhRTCtLK2F3NHE1SDgxZTFZUjN4TGhQaVdBTFNXVWlhYk5OQ3dyTGQvCk5uRVNaa0gzQVJqazVtYnhzU1lKcU8yUTRTL2lmNXl3WXYycG51ZW50d2ZQa3p3b0NkcFA3VVZ0MHhNNFgvZEcKYXdJREFRQUI=", 0)));
        } catch (Exception unused) {
            this.f5260a = null;
        }
    }

    protected boolean a(String str, File file) {
        if (TextUtils.isEmpty(str)) {
            com.bytedance.sdk.openadsdk.api.b.d.a("TTPluginChecker", "Verify false, empty sign: ");
            return false;
        }
        if (file == null || !file.exists()) {
            com.bytedance.sdk.openadsdk.api.b.d.a("TTPluginChecker", "Verify false, no file");
            return false;
        }
        if (this.f5260a == null) {
            com.bytedance.sdk.openadsdk.api.b.d.a("TTPluginChecker", "Verify false, no key ");
            return false;
        }
        try {
            return a(str, new FileInputStream(file));
        } catch (Exception e) {
            com.bytedance.sdk.openadsdk.api.b.d.a("TTPluginChecker", "Verify false, sign: " + str + ", error:" + e);
            return false;
        }
    }

    private boolean a(String str, InputStream inputStream) throws Exception {
        if (this.f5260a == null) {
            return false;
        }
        byte[] bArrDecode = Base64.decode(str, 0);
        try {
            try {
                Signature signature = Signature.getInstance("MD5withRSA");
                signature.initVerify(this.f5260a);
                byte[] bArr = new byte[8192];
                while (true) {
                    int i = inputStream.read(bArr);
                    if (i == -1) {
                        break;
                    }
                    signature.update(bArr, 0, i);
                }
                return signature.verify(bArrDecode);
            } catch (Throwable th) {
                throw new Exception("Verify sign failed", th);
            }
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static PublicKey a(String str) throws Exception {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
        } catch (Exception e) {
            throw new Exception("Init public key failed", e);
        }
    }
}

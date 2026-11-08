package com.vivo.push.c;

import android.text.TextUtils;
import android.util.Base64;
import com.vivo.push.sdk.PushMessageCallback;
import java.security.PublicKey;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class o extends com.vivo.push.e {
    protected PushMessageCallback c;

    o(com.vivo.push.g gVar) {
        super(gVar);
    }

    public final void a(PushMessageCallback pushMessageCallback) {
        this.c = pushMessageCallback;
    }

    public final boolean a(PublicKey publicKey, String str, String str2) {
        if (!com.vivo.push.d.a().c()) {
            com.vivo.push.util.n.d("OnVerifyCallBackCommand", "vertify is not support , vertify is ignore");
            return true;
        }
        if (publicKey == null) {
            com.vivo.push.util.n.d("OnVerifyCallBackCommand", "vertify key is null");
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            com.vivo.push.util.n.d("OnVerifyCallBackCommand", "contentTag is null");
            return false;
        }
        if (!TextUtils.isEmpty(str2)) {
            try {
                com.vivo.push.util.n.d("OnVerifyCallBackCommand", str.hashCode() + " = " + str2);
                if (com.vivo.push.util.s.a(str.getBytes("UTF-8"), publicKey, Base64.decode(str2, 2))) {
                    com.vivo.push.util.n.d("OnVerifyCallBackCommand", "vertify id is success");
                    return true;
                }
                com.vivo.push.util.n.d("OnVerifyCallBackCommand", "vertify fail srcDigest is " + str);
                com.vivo.push.util.n.c(this.a, "vertify fail srcDigest is " + str);
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                com.vivo.push.util.n.d("OnVerifyCallBackCommand", "vertify exception");
                return false;
            }
        }
        com.vivo.push.util.n.d("OnVerifyCallBackCommand", "vertify id is null");
        return false;
    }
}

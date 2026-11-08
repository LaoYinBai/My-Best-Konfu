package com.ss.union.game.sdk.mail;

import android.content.Context;
import android.text.TextUtils;
import com.ss.union.game.sdk.core.LGSDKCore;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.mail.callback.LGMailAckCallBack;
import com.ss.union.game.sdk.mail.callback.LGMailFetchCallBack;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b implements ILGMailService {
    private static volatile b a;

    private b() {
    }

    public static b a() {
        if (a == null) {
            synchronized (com.ss.union.game.sdk.feedback.b.a.class) {
                if (a == null) {
                    a = new b();
                }
            }
        }
        return a;
    }

    @Override // com.ss.union.game.sdk.mail.ILGMailService
    public void fetchMail(Context context, final LGMailFetchCallBack lGMailFetchCallBack) {
        if (lGMailFetchCallBack == null) {
            com.ss.union.game.sdk.c.a.e("LGMailFetchCallBack 为空");
            return;
        }
        if (!LGSDKCore.isSdkInitSuccess()) {
            lGMailFetchCallBack.onFail(-204, "SDK还未初始化完成");
        } else if (!LGAccountDataUtil.isLogined() || TextUtils.isEmpty(LGAccountDataUtil.getCurrentUserOpenId())) {
            lGMailFetchCallBack.onFail(0, "当前用户信息为空");
        } else {
            com.ss.union.game.sdk.mail.a.a.a(new LGMailFetchCallBack() { // from class: com.ss.union.game.sdk.mail.b.1
                @Override // com.ss.union.game.sdk.mail.callback.LGMailFetchCallBack
                public void onSuccess(String str) {
                    com.ss.union.game.sdk.c.a.e("fetchMail success: " + str);
                    lGMailFetchCallBack.onSuccess(str);
                }

                @Override // com.ss.union.game.sdk.mail.callback.LGMailFetchCallBack
                public void onFail(int i, String str) {
                    com.ss.union.game.sdk.c.a.e("fetchMail fail: code= " + i + " msg= " + str);
                    lGMailFetchCallBack.onFail(i, str);
                }
            });
        }
    }

    @Override // com.ss.union.game.sdk.mail.ILGMailService
    public void ackMail(Context context, int[] iArr, final LGMailAckCallBack lGMailAckCallBack) {
        if (lGMailAckCallBack == null) {
            com.ss.union.game.sdk.c.a.e("LGMailAckCallBack 为空");
            return;
        }
        if (!LGSDKCore.isSdkInitSuccess()) {
            lGMailAckCallBack.onFail(-204, "SDK还未初始化完成");
        } else if (!LGAccountDataUtil.isLogined() || TextUtils.isEmpty(LGAccountDataUtil.getCurrentUserOpenId())) {
            lGMailAckCallBack.onFail(0, "当前用户信息为空");
        } else {
            com.ss.union.game.sdk.mail.a.a.a(iArr, new LGMailAckCallBack() { // from class: com.ss.union.game.sdk.mail.b.2
                @Override // com.ss.union.game.sdk.mail.callback.LGMailAckCallBack
                public void onSuccess() {
                    com.ss.union.game.sdk.c.a.e("ackMail success");
                    lGMailAckCallBack.onSuccess();
                }

                @Override // com.ss.union.game.sdk.mail.callback.LGMailAckCallBack
                public void onFail(int i, String str) {
                    com.ss.union.game.sdk.c.a.e("ackMail fail: code= " + i + " msg= " + str);
                    lGMailAckCallBack.onFail(i, str);
                }
            });
        }
    }
}

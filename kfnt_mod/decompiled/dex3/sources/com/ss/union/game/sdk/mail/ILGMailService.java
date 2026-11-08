package com.ss.union.game.sdk.mail;

import android.content.Context;
import com.ss.union.game.sdk.mail.callback.LGMailAckCallBack;
import com.ss.union.game.sdk.mail.callback.LGMailFetchCallBack;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface ILGMailService {
    void ackMail(Context context, int[] iArr, LGMailAckCallBack lGMailAckCallBack);

    void fetchMail(Context context, LGMailFetchCallBack lGMailFetchCallBack);
}

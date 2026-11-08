package com.ss.union.game.sdk.pay.callback;

import androidx.annotation.NonNull;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface ILGPayService {
    String generateSign(@NonNull String str, @NonNull String str2, @NonNull String str3, String str4, @NonNull String str5, @NonNull String str6, String str7, long j, int i, String str8, String str9);

    void queryPaySupport(long j, LGPaySupportCallback lGPaySupportCallback);

    void tryPay(String str, LGPayCallback lGPayCallback);

    void tryPay(@NonNull String str, @NonNull String str2, long j, @NonNull String str3, @NonNull String str4, String str5, long j2, int i, String str6, LGPayCallback lGPayCallback);
}

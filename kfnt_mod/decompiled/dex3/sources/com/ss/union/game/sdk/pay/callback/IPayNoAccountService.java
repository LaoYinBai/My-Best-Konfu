package com.ss.union.game.sdk.pay.callback;

import androidx.annotation.NonNull;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IPayNoAccountService {
    String generateSignNoAccount(@NonNull String str, @NonNull String str2, @NonNull String str3, String str4, @NonNull String str5, @NonNull String str6, String str7, long j, int i, String str8, String str9);

    void tryPayNoAccount(String str, LGPayCallback lGPayCallback);

    void tryPayNoAccount(@NonNull String str, @NonNull String str2, @NonNull String str3, long j, @NonNull String str4, @NonNull String str5, String str6, long j2, int i, String str7, LGPayCallback lGPayCallback);
}

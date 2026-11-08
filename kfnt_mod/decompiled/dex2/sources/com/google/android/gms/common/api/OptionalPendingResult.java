package com.google.android.gms.common.api;

import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Result;

/* compiled from: com.google.android.gms:play-services-base@@18.1.0 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class OptionalPendingResult<R extends Result> extends PendingResult<R> {
    @NonNull
    public abstract R get();

    public abstract boolean isDone();
}

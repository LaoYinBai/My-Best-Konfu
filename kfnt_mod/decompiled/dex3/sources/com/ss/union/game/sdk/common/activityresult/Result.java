package com.ss.union.game.sdk.common.activityresult;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Result {

    @NonNull
    private final InlineActivityResult a;
    private final int b;
    private final int c;

    @Nullable
    private final Intent d;

    @Nullable
    private Throwable e;

    public Result(@NonNull InlineActivityResult inlineActivityResult, int i, int i2, @Nullable Intent intent) {
        this.a = inlineActivityResult;
        this.b = i;
        this.c = i2;
        this.d = intent;
    }

    public Result(@NonNull InlineActivityResult inlineActivityResult, @Nullable Throwable th) {
        this(inlineActivityResult, 0, 0, null);
        this.e = th;
    }

    public int getRequestCode() {
        return this.b;
    }

    public int getResultCode() {
        return this.c;
    }

    @Nullable
    public Intent getData() {
        return this.d;
    }

    @NonNull
    public InlineActivityResult getInlineActivityResult() {
        return this.a;
    }

    @Nullable
    public Throwable getCause() {
        return this.e;
    }
}

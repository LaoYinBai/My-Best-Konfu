package com.bytedance.applog;

import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IOaidObserver {

    public static final class Oaid {

        @Nullable
        public final String id;

        public Oaid(@Nullable String str) {
            this.id = str;
        }
    }

    @AnyThread
    void onOaidLoaded(@NonNull Oaid oaid);
}

package com.kuaishou.tachikoma.api.exception;

import androidx.annotation.Nullable;
import com.kuaishou.tachikoma.api.model.TKBundle;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public interface ITKExceptionHandler {
    void handleException(Throwable th, @Nullable TKBundle tKBundle);
}

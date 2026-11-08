package com.google.android.gms.common.internal;

import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;

/* compiled from: com.google.android.gms:play-services-base@@18.1.0 */
@KeepForSdk
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class TelemetryLogging {
    private TelemetryLogging() {
    }

    @NonNull
    @KeepForSdk
    public static TelemetryLoggingClient getClient(@NonNull Context context) {
        return getClient(context, TelemetryLoggingOptions.zaa);
    }

    @NonNull
    @KeepForSdk
    public static TelemetryLoggingClient getClient(@NonNull Context context, @NonNull TelemetryLoggingOptions telemetryLoggingOptions) {
        return new com.google.android.gms.common.internal.service.zao(context, telemetryLoggingOptions);
    }
}

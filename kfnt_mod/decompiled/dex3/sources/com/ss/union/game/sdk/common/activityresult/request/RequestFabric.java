package com.ss.union.game.sdk.common.activityresult.request;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RequestFabric {
    private RequestFabric() {
    }

    public static Request create(Intent intent) {
        return new RequestActivityForResult(intent, (Bundle) null);
    }

    public static Request create(Intent intent, @Nullable Bundle bundle) {
        return new RequestActivityForResult(intent, bundle);
    }
}

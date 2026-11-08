package com.huawei.hms.api;

import android.content.Intent;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class HuaweiServicesRepairableException extends UserRecoverableException {
    private final int a;

    public HuaweiServicesRepairableException(int i, String str, Intent intent) {
        super(str, intent);
        this.a = i;
    }

    public int getConnectionStatusCode() {
        return this.a;
    }
}

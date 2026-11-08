package com.huawei.hms.api;

import android.content.Intent;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class UserRecoverableException extends Exception {
    private final Intent a;

    public UserRecoverableException(String str, Intent intent) {
        super(str);
        this.a = intent;
    }

    public Intent getIntent() {
        return new Intent(this.a);
    }
}

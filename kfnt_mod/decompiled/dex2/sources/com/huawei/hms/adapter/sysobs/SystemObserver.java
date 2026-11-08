package com.huawei.hms.adapter.sysobs;

import android.content.Intent;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public interface SystemObserver {
    boolean onSolutionResult(Intent intent, String str);

    boolean onUpdateResult(int i);
}

package com.meizu.flyme.openidsdk;

import androidx.annotation.Keep;
import com.kwad.sdk.collector.AppStatusRules;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ValueData {

    @Keep
    public String a;

    @Keep
    public int b;

    @Keep
    public long c = System.currentTimeMillis() + AppStatusRules.DEFAULT_START_TIME;

    @Keep
    public ValueData(String str, int i) {
        this.a = str;
        this.b = i;
    }

    @Keep
    public native String toString();
}

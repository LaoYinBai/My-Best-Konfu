package com.xiaomi.push.service;

import com.xiaomi.push.hp;
import com.xiaomi.push.hq;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
/* synthetic */ class as {
    static final /* synthetic */ int[] a;
    static final /* synthetic */ int[] b = new int[hq.values().length];

    static {
        try {
            b[hq.INT.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            b[hq.LONG.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            b[hq.STRING.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            b[hq.BOOLEAN.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        a = new int[hp.values().length];
        try {
            a[hp.MISC_CONFIG.ordinal()] = 1;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            a[hp.PLUGIN_CONFIG.ordinal()] = 2;
        } catch (NoSuchFieldError unused6) {
        }
    }
}

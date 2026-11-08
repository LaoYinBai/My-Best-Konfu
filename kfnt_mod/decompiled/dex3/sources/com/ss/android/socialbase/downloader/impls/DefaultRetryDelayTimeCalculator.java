package com.ss.android.socialbase.downloader.impls;

import com.ss.android.socialbase.downloader.downloader.IRetryDelayTimeCalculator;
import com.umeng.commonsdk.proguard.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DefaultRetryDelayTimeCalculator implements IRetryDelayTimeCalculator {
    @Override // com.ss.android.socialbase.downloader.downloader.IRetryDelayTimeCalculator
    public long calculateRetryDelayTime(int i, int i2) {
        if (i == 1) {
            return 3000L;
        }
        if (i == 2) {
            return 15000L;
        }
        return i == 3 ? b.d : i > 3 ? 300000L : 0L;
    }
}

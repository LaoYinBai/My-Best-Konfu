package com.ss.android.socialbase.downloader.impls;

import com.ss.android.socialbase.downloader.downloader.IChunkAdjustCalculator;
import com.ss.android.socialbase.downloader.network.NetworkQuality;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DefaultChunkAdjustCalculator implements IChunkAdjustCalculator {
    @Override // com.ss.android.socialbase.downloader.downloader.IChunkAdjustCalculator
    public int calculateChunkCount(int i, NetworkQuality networkQuality) {
        if (networkQuality.ordinal() <= NetworkQuality.MODERATE.ordinal()) {
            return 1;
        }
        return networkQuality == NetworkQuality.GOOD ? i - 1 : i;
    }
}

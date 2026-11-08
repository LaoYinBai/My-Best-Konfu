package com.ss.android.socialbase.downloader.impls;

import com.ss.android.socialbase.downloader.downloader.IChunkCntCalculator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DefaultChunkCntCalculator implements IChunkCntCalculator {
    @Override // com.ss.android.socialbase.downloader.downloader.IChunkCntCalculator
    public int calculateChunkCount(long j) {
        return 1;
    }
}

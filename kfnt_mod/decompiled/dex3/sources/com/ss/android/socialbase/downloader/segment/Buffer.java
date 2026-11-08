package com.ss.android.socialbase.downloader.segment;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class Buffer {
    public final byte[] data;
    public Buffer next;
    IOutput output;
    public int size;

    public Buffer(int i) {
        this.data = new byte[i];
    }
}

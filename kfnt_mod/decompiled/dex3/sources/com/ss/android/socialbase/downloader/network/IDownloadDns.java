package com.ss.android.socialbase.downloader.network;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IDownloadDns {
    List<InetAddress> lookup(String str) throws UnknownHostException;
}

package com.ss.android.socialbase.downloader.impls;

import android.text.TextUtils;
import com.ss.android.socialbase.downloader.downloader.IDownloadIdGenerator;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.security.NoSuchAlgorithmException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DefaultIdGenerator implements IDownloadIdGenerator {
    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadIdGenerator
    public int generate(String str, String str2) throws NoSuchAlgorithmException {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return 0;
        }
        String strMd5Hex = DownloadUtils.md5Hex(String.format("%s_%s", str, str2));
        if (TextUtils.isEmpty(strMd5Hex)) {
            return 0;
        }
        return strMd5Hex.hashCode();
    }
}

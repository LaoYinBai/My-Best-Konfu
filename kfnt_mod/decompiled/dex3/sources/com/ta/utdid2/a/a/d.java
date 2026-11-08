package com.ta.utdid2.a.a;

import com.qq.e.comm.compliance.ApkDownloadComplianceInterface;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d {
    public static byte[] getBytes(int i) {
        byte[] bArr = {(byte) ((i >> 8) % ApkDownloadComplianceInterface.INSTALL_BITS), (byte) (i % ApkDownloadComplianceInterface.INSTALL_BITS), (byte) (i % ApkDownloadComplianceInterface.INSTALL_BITS), (byte) (i % ApkDownloadComplianceInterface.INSTALL_BITS)};
        int i2 = i >> 8;
        int i3 = i2 >> 8;
        return bArr;
    }
}

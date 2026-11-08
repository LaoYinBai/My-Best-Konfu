package com.kwad.sdk.api.loader;

import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.mbridge.msdk.foundation.tools.SameMD5;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.File;
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class p {
    private static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    static String a(File file) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(SameMD5.TAG);
            while (new DigestInputStream(new FileInputStream(file), messageDigest).read(new byte[1024]) != -1) {
            }
            byte[] bArrDigest = messageDigest.digest();
            StringBuilder sb = new StringBuilder(bArrDigest.length * 2);
            for (byte b : bArrDigest) {
                int i = b & FileDownloadStatus.error;
                if (i < 16) {
                    sb.append(PushConstants.PUSH_TYPE_NOTIFY);
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}

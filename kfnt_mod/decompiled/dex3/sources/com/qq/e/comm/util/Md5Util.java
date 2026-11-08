package com.qq.e.comm.util;

import android.util.Base64;
import com.qq.e.comm.compliance.ApkDownloadComplianceInterface;
import com.sigmob.sdk.common.Constants;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.taobao.accs.data.Message;
import com.umeng.commonsdk.proguard.o;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Md5Util {
    private static final String[] a = {Constants.FAIL, "1", "2", "3", DownloadConstants.DOWNLOAD_FINISH_REASON_SHOW_DIALOG_ERROR, "5", DownloadConstants.DOWNLOAD_FINISH_REASON_BEFORE_DOWNLOAD_SUCCESS, "7", "8", "9", o.ap, "b", "c", o.aq, "e", "f"};

    public static String byteArrayToHexString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i : bArr) {
            if (i < 0) {
                i += ApkDownloadComplianceInterface.INSTALL_BITS;
            }
            stringBuffer.append(a[i / 16] + a[i % 16]);
        }
        return stringBuffer.toString();
    }

    public static String encode(File file) throws Throwable {
        if (file == null || !file.exists()) {
            return "";
        }
        FileInputStream fileInputStream = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                byte[] bArr = new byte[Message.FLAG_REQ_BIT1];
                while (true) {
                    int i = fileInputStream2.read(bArr);
                    if (i <= 0) {
                        break;
                    }
                    messageDigest.update(bArr, 0, i);
                }
                String strByteArrayToHexString = byteArrayToHexString(messageDigest.digest());
                try {
                    fileInputStream2.close();
                } catch (Exception unused) {
                }
                return strByteArrayToHexString;
            } catch (Exception unused2) {
                fileInputStream = fileInputStream2;
                if (fileInputStream == null) {
                    return "";
                }
                try {
                    fileInputStream.close();
                    return "";
                } catch (Exception unused3) {
                    return "";
                }
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception unused4) {
                    }
                }
                throw th;
            }
        } catch (Exception unused5) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String encode(String str) {
        try {
            String str2 = new String(str);
            try {
                return byteArrayToHexString(MessageDigest.getInstance("MD5").digest(str2.getBytes("UTF-8")));
            } catch (Exception unused) {
                return str2;
            }
        } catch (Exception unused2) {
            return null;
        }
    }

    public static String encodeBase64String(String str) {
        try {
            return byteArrayToHexString(MessageDigest.getInstance("MD5").digest(Base64.decode(str, 0)));
        } catch (Exception unused) {
            GDTLogger.d("Exception while md5 base64String");
            return null;
        }
    }

    public static byte[] hexStringtoByteArray(String str) {
        if (str.length() % 2 != 0) {
            return null;
        }
        byte[] bArr = new byte[str.length() / 2];
        for (int i = 0; i < str.length() - 1; i += 2) {
            char cCharAt = str.charAt(i);
            char cCharAt2 = str.charAt(i + 1);
            char lowerCase = Character.toLowerCase(cCharAt);
            char lowerCase2 = Character.toLowerCase(cCharAt2);
            int i2 = ((lowerCase <= '9' ? lowerCase - '0' : (lowerCase - 'a') + 10) << 4) + (lowerCase2 <= '9' ? lowerCase2 - '0' : (lowerCase2 - 'a') + 10);
            if (i2 > 127) {
                i2 -= 256;
            }
            bArr[i / 2] = (byte) i2;
        }
        return bArr;
    }
}

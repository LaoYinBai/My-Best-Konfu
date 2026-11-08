package com.yxcorp.kuaishou.addfp.android.b;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.qq.e.comm.compliance.ApkDownloadComplianceInterface;
import com.sigmob.sdk.common.Constants;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.umeng.commonsdk.proguard.o;
import com.umeng.message.MsgConstant;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.List;
import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class b {
    static {
        new String[]{Constants.FAIL, "1", "2", "3", DownloadConstants.DOWNLOAD_FINISH_REASON_SHOW_DIALOG_ERROR, "5", DownloadConstants.DOWNLOAD_FINISH_REASON_BEFORE_DOWNLOAD_SUCCESS, "7", "8", "9", "getNetworkIso", "b", "c", o.aq, "e", "f"};
    }

    public static File a(Context context, boolean z) {
        StorageManager storageManager = (StorageManager) context.getSystemService("storage");
        try {
            Class<?> cls = Class.forName("android.os.storage.StorageVolume");
            Method method = storageManager.getClass().getMethod("getVolumeList", new Class[0]);
            Method method2 = cls.getMethod("getPath", new Class[0]);
            Method method3 = cls.getMethod("isRemovable", new Class[0]);
            Object objInvoke = method.invoke(storageManager, new Object[0]);
            int length = Array.getLength(objInvoke);
            for (int i = 0; i < length; i++) {
                Object obj = Array.get(objInvoke, i);
                String str = (String) method2.invoke(obj, new Object[0]);
                if (!((Boolean) method3.invoke(obj, new Object[0])).booleanValue() && !TextUtils.isEmpty(str)) {
                    return new File(str);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return Environment.getExternalStorageDirectory();
    }

    public static boolean a() {
        return false;
    }

    private static byte[] a(byte[] bArr) {
        byte[] bArr2 = new byte[ApkDownloadComplianceInterface.INSTALL_BITS];
        for (int i = 0; i < 256; i++) {
            bArr2[i] = (byte) i;
        }
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        int length = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < 256; i3++) {
            i2 = ((bArr[length] & UByte.MAX_VALUE) + (bArr2[i3] & UByte.MAX_VALUE) + i2) & 255;
            byte b = bArr2[i3];
            bArr2[i3] = bArr2[i2];
            bArr2[i2] = b;
            length = (length + 1) % bArr.length;
        }
        return bArr2;
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        if (bArr2 != null) {
            try {
                if (bArr2.length > 0 && bArr != null && bArr.length > 0) {
                    byte[] bArrC = (bArr == null || bArr2 == null) ? null : c(bArr, bArr2);
                    if (bArrC != null) {
                        if (bArrC.length > 0) {
                        }
                    }
                    return bArrC;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return null;
    }

    public static String[] a(Context context) {
        List<SubscriptionInfo> activeSubscriptionInfoList;
        try {
            if (!e.a(context, new String[]{MsgConstant.PERMISSION_READ_PHONE_STATE})) {
                return new String[]{"KWE_PN", "KWE_PN"};
            }
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            int phoneCount = Build.VERSION.SDK_INT >= 23 ? ((TelephonyManager) context.getSystemService("phone")).getPhoneCount() : Build.VERSION.SDK_INT >= 22 ? SubscriptionManager.from(context).getActiveSubscriptionInfoCount() : 1;
            if (phoneCount == 0) {
                phoneCount = 1;
            }
            if (phoneCount > 1 && Build.VERSION.SDK_INT >= 22 && (activeSubscriptionInfoList = SubscriptionManager.from(context).getActiveSubscriptionInfoList()) != null) {
                String iccId = "";
                String iccId2 = "";
                int size = activeSubscriptionInfoList.size();
                if (size > 1) {
                    iccId = activeSubscriptionInfoList.get(0).getIccId();
                    iccId2 = activeSubscriptionInfoList.get(1).getIccId();
                } else if (size == 1) {
                    iccId = activeSubscriptionInfoList.get(0).getIccId();
                }
                if (!TextUtils.isEmpty(iccId) || !TextUtils.isEmpty(iccId2)) {
                    return new String[]{iccId, iccId2};
                }
            }
            String simSerialNumber = telephonyManager.getSimSerialNumber();
            if (simSerialNumber == null) {
                simSerialNumber = "KWE_N";
            }
            return new String[]{simSerialNumber, "KWE_N"};
        } catch (Throwable th) {
            th.printStackTrace();
            return new String[]{"KWE_N", "KWE_N"};
        }
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) {
        if (bArr2 != null) {
            try {
                if (bArr2.length > 0 && bArr != null && bArr.length > 0) {
                    byte[] bArrC = (bArr == null || bArr2 == null) ? null : c(bArr, bArr2);
                    if (bArrC != null) {
                        if (bArrC.length > 0) {
                        }
                    }
                    return bArrC;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return null;
    }

    private static byte[] c(byte[] bArr, byte[] bArr2) {
        byte[] bArrA = a(bArr2);
        byte[] bArr3 = new byte[bArr.length];
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            i = (i + 1) & 255;
            i2 = ((bArrA[i] & UByte.MAX_VALUE) + i2) & 255;
            byte b = bArrA[i];
            bArrA[i] = bArrA[i2];
            bArrA[i2] = b;
            int i4 = ((bArrA[i] & UByte.MAX_VALUE) + (bArrA[i2] & UByte.MAX_VALUE)) & 255;
            bArr3[i3] = (byte) (bArrA[i4] ^ bArr[i3]);
            bArr3[i3] = (byte) (bArr3[i3] ^ 42);
        }
        return bArr3;
    }
}

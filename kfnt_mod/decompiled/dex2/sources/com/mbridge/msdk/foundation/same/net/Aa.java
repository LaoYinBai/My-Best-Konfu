package com.mbridge.msdk.foundation.same.net;

import android.os.Handler;
import android.os.Process;
import android.text.TextUtils;
import com.mbridge.msdk.MBridgeConstans;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class Aa {
    private static final String C_END = "_mv_end";
    private static final String C_START = "mv_channel_";
    private static String a = "";

    public static String b() {
        return null;
    }

    private static void b(String str) {
        String strB;
        Integer numC;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            strB = com.mbridge.msdk.foundation.tools.n.b(str);
        } catch (Throwable th) {
            th.printStackTrace();
            strB = null;
        }
        if (!TextUtils.isEmpty(strB) && strB.startsWith(C_START) && strB.endsWith(C_END) && (numC = c(strB)) != null) {
            a = String.valueOf(numC);
        } else {
            if (MBridgeConstans.DEBUG) {
                g();
                throw new RuntimeException("please don't update this value");
            }
            a = "";
        }
    }

    private static Integer c(String str) {
        Integer num = null;
        if (str != null) {
            int iIndexOf = str.indexOf(C_START);
            int iIndexOf2 = str.indexOf(C_END);
            if (iIndexOf != -1 && iIndexOf2 != -1 && iIndexOf2 > iIndexOf) {
                try {
                    Integer numValueOf = Integer.valueOf(str.substring(iIndexOf + C_START.length(), iIndexOf2));
                    try {
                        if (numValueOf.intValue() > 0) {
                            return numValueOf;
                        }
                        return null;
                    } catch (Throwable th) {
                        num = numValueOf;
                        th = th;
                        th.printStackTrace();
                        return num;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }
        return num;
    }

    public static String a() {
        return a;
    }

    private static void g() {
        new Handler().postDelayed(new Runnable() { // from class: com.mbridge.msdk.foundation.same.net.Aa.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    Process.killProcess(Process.myPid());
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }, 500L);
    }
}

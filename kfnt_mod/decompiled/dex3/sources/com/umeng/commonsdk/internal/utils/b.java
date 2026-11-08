package com.umeng.commonsdk.internal.utils;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.qq.e.comm.compliance.ApkDownloadComplianceInterface;
import com.sigmob.sdk.common.Constants;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.statistics.common.ULog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    private static final String b = "BaseStationUtils";
    private static boolean c;
    private static Context d;
    PhoneStateListener a;
    private TelephonyManager e;

    private b(Context context) {
        this.a = new PhoneStateListener() { // from class: com.umeng.commonsdk.internal.utils.b.1
            @Override // android.telephony.PhoneStateListener
            public void onSignalStrengthsChanged(SignalStrength signalStrength) throws Throwable {
                String str;
                super.onSignalStrengthsChanged(signalStrength);
                ULog.e(b.b, "base station onSignalStrengthsChanged");
                try {
                    b.this.e = (TelephonyManager) b.d.getSystemService("phone");
                    String[] strArrSplit = signalStrength.toString().split(" ");
                    String str2 = null;
                    if (b.this.e == null || b.this.e.getNetworkType() != 13) {
                        if (b.this.e != null && (b.this.e.getNetworkType() == 8 || b.this.e.getNetworkType() == 10 || b.this.e.getNetworkType() == 9 || b.this.e.getNetworkType() == 3)) {
                            String strE = b.this.e();
                            if (!TextUtils.isEmpty(strE) && strE.equals("中国移动")) {
                                str2 = Constants.FAIL;
                            } else if (!TextUtils.isEmpty(strE) && strE.equals("中国联通")) {
                                str2 = signalStrength.getCdmaDbm() + "";
                            } else if (!TextUtils.isEmpty(strE) && strE.equals("中国电信")) {
                                str2 = signalStrength.getEvdoDbm() + "";
                            }
                            str = str2;
                        } else {
                            str = ((signalStrength.getGsmSignalStrength() * 2) - 113) + "";
                        }
                    } else {
                        str = "" + Integer.parseInt(strArrSplit[9]);
                    }
                    ULog.e(b.b, "stationStrength is " + str);
                    if (!TextUtils.isEmpty(str)) {
                        try {
                            UMWorkDispatch.sendEvent(b.d, com.umeng.commonsdk.internal.a.h, com.umeng.commonsdk.internal.b.a(b.d).a(), str);
                        } catch (Throwable unused) {
                        }
                    }
                    b.this.c();
                } catch (Exception unused2) {
                }
            }
        };
        if (context != null) {
            try {
                this.e = (TelephonyManager) context.getSystemService("phone");
            } catch (Throwable unused) {
            }
        }
    }

    private static class a {
        private static final b a = new b(b.d);

        private a() {
        }
    }

    public static b a(Context context) {
        if (d == null && context != null) {
            d = context.getApplicationContext();
        }
        return a.a;
    }

    public synchronized boolean a() {
        return c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String e() {
        String str;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) d.getSystemService("phone");
            if (telephonyManager == null) {
                return null;
            }
            String simOperator = telephonyManager.getSimOperator();
            if (TextUtils.isEmpty(simOperator)) {
                return null;
            }
            if (simOperator.equals("46000") || simOperator.equals("46002")) {
                str = "中国移动";
            } else if (simOperator.equals("46001")) {
                str = "中国联通";
            } else {
                if (!simOperator.equals("46003")) {
                    return null;
                }
                str = "中国电信";
            }
            return str;
        } catch (Throwable unused) {
            return null;
        }
    }

    public synchronized void b() {
        ULog.e(b, "base station registerListener");
        try {
            if (this.e != null) {
                this.e.listen(this.a, ApkDownloadComplianceInterface.INSTALL_BITS);
            }
            c = true;
        } catch (Throwable unused) {
        }
    }

    public synchronized void c() {
        ULog.e(b, "base station unRegisterListener");
        try {
            if (this.e != null) {
                this.e.listen(this.a, 0);
            }
            c = false;
        } catch (Throwable unused) {
        }
    }
}

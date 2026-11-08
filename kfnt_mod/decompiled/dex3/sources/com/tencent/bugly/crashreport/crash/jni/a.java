package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.y;
import com.tencent.bugly.proguard.z;
import com.umeng.message.proguard.l;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: BUGLY */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class a implements NativeExceptionHandler {
    private final Context a;
    private final com.tencent.bugly.crashreport.crash.b b;
    private final com.tencent.bugly.crashreport.common.info.a c;
    private final com.tencent.bugly.crashreport.common.strategy.a d;

    public a(Context context, com.tencent.bugly.crashreport.common.info.a aVar, com.tencent.bugly.crashreport.crash.b bVar, com.tencent.bugly.crashreport.common.strategy.a aVar2) {
        this.a = context;
        this.b = bVar;
        this.c = aVar;
        this.d = aVar2;
    }

    @Override // com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler
    public final CrashDetailBean packageCrashDatas(String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, byte[] bArr, Map<String, String> map, boolean z) throws IOException {
        int length;
        String str11;
        int iIndexOf;
        boolean zL = c.a().l();
        if (zL) {
            x.e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.b = 1;
        crashDetailBean.e = this.c.h();
        crashDetailBean.f = this.c.j;
        crashDetailBean.g = this.c.w();
        crashDetailBean.m = this.c.g();
        crashDetailBean.n = str3;
        crashDetailBean.o = zL ? " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]" : "";
        crashDetailBean.p = str4;
        crashDetailBean.q = str5 == null ? "" : str5;
        crashDetailBean.r = j;
        crashDetailBean.u = z.b(crashDetailBean.q.getBytes());
        crashDetailBean.z = str;
        crashDetailBean.A = str2;
        crashDetailBean.H = this.c.y();
        crashDetailBean.h = this.c.v();
        crashDetailBean.i = this.c.I();
        crashDetailBean.v = str8;
        NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
        String dumpFilePath = nativeCrashHandler != null ? nativeCrashHandler.getDumpFilePath() : null;
        String strA = b.a(dumpFilePath, str8);
        if (!z.a(strA)) {
            crashDetailBean.T = strA;
        }
        crashDetailBean.U = b.b(dumpFilePath);
        crashDetailBean.w = b.a(str9, c.e, (String) null);
        crashDetailBean.I = str7;
        crashDetailBean.J = str6;
        crashDetailBean.K = str10;
        crashDetailBean.E = this.c.p();
        crashDetailBean.F = this.c.o();
        crashDetailBean.G = this.c.q();
        if (z) {
            crashDetailBean.B = com.tencent.bugly.crashreport.common.info.b.g();
            crashDetailBean.C = com.tencent.bugly.crashreport.common.info.b.e();
            crashDetailBean.D = com.tencent.bugly.crashreport.common.info.b.i();
            if (crashDetailBean.w == null) {
                crashDetailBean.w = z.a(this.a, c.e, (String) null);
            }
            crashDetailBean.x = y.a();
            crashDetailBean.L = this.c.a;
            crashDetailBean.M = this.c.a();
            crashDetailBean.O = this.c.F();
            crashDetailBean.P = this.c.G();
            crashDetailBean.Q = this.c.z();
            crashDetailBean.R = this.c.E();
            crashDetailBean.y = z.a(c.f, false);
            int iIndexOf2 = crashDetailBean.q.indexOf("java:\n");
            if (iIndexOf2 > 0 && (length = iIndexOf2 + "java:\n".length()) < crashDetailBean.q.length()) {
                String strSubstring = crashDetailBean.q.substring(length, crashDetailBean.q.length() - 1);
                if (strSubstring.length() > 0 && crashDetailBean.y.containsKey(crashDetailBean.A) && (iIndexOf = (str11 = crashDetailBean.y.get(crashDetailBean.A)).indexOf(strSubstring)) > 0) {
                    String strSubstring2 = str11.substring(iIndexOf);
                    crashDetailBean.y.put(crashDetailBean.A, strSubstring2);
                    crashDetailBean.q = crashDetailBean.q.substring(0, length);
                    crashDetailBean.q += strSubstring2;
                }
            }
            if (str == null) {
                crashDetailBean.z = this.c.d;
            }
            this.b.c(crashDetailBean);
        } else {
            crashDetailBean.B = -1L;
            crashDetailBean.C = -1L;
            crashDetailBean.D = -1L;
            if (crashDetailBean.w == null) {
                crashDetailBean.w = "this crash is occurred at last process! Log is miss, when get an terrible ABRT Native Exception etc.";
            }
            crashDetailBean.L = -1L;
            crashDetailBean.O = -1;
            crashDetailBean.P = -1;
            crashDetailBean.Q = map;
            crashDetailBean.R = this.c.E();
            crashDetailBean.y = null;
            if (str == null) {
                crashDetailBean.z = "unknown(record)";
            }
            if (bArr != null) {
                crashDetailBean.x = bArr;
            }
        }
        return crashDetailBean;
    }

    @Override // com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler
    public final void handleNativeException(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7) {
        x.a("Native Crash Happen v1", new Object[0]);
        handleNativeException2(i, i2, j, j2, str, str2, str3, str4, i3, str5, i4, i5, i6, str6, str7, null);
    }

    @Override // com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler
    public final void handleNativeException2(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7, String[] strArr) {
        String str8;
        String str9;
        String str10;
        boolean z;
        x.a("Native Crash Happen v2", new Object[0]);
        try {
            if (!this.d.b()) {
                x.e("waiting for remote sync", new Object[0]);
                int i7 = 0;
                while (!this.d.b()) {
                    z.b(500L);
                    i7 += 500;
                    if (i7 >= 3000) {
                        break;
                    }
                }
            }
            String strA = b.a(str3);
            String strA2 = "UNKNOWN";
            if (i3 > 0) {
                str9 = str + l.s + str5 + l.t;
                str8 = "UNKNOWN";
                str10 = "KERNEL";
            } else {
                if (i4 > 0) {
                    Context context = this.a;
                    strA2 = AppInfo.a(i4);
                }
                str8 = strA2.equals(String.valueOf(i4)) ? strA2 : strA2 + l.s + i4 + l.t;
                str9 = str;
                str10 = str5;
            }
            if (!this.d.b()) {
                x.d("no remote but still store!", new Object[0]);
            }
            if (!this.d.c().g && this.d.b()) {
                x.e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
                com.tencent.bugly.crashreport.crash.b.a("NATIVE_CRASH", z.a(), this.c.d, Thread.currentThread(), str9 + "\n" + str2 + "\n" + strA, null);
                z.b(str4);
                return;
            }
            HashMap map = new HashMap();
            if (strArr != null) {
                for (String str11 : strArr) {
                    String[] strArrSplit = str11.split("=");
                    if (strArrSplit.length == 2) {
                        map.put(strArrSplit[0], strArrSplit[1]);
                    } else {
                        x.d("bad extraMsg %s", str11);
                    }
                }
            } else {
                x.c("not found extraMsg", new Object[0]);
            }
            String str12 = (String) map.get("ExceptionProcessName");
            if (str12 == null || str12.length() == 0) {
                str12 = this.c.d;
            } else {
                x.c("crash process name change to %s", str12);
            }
            String str13 = (String) map.get("ExceptionThreadName");
            if (str13 == null || str13.length() == 0) {
                Thread threadCurrentThread = Thread.currentThread();
                str13 = threadCurrentThread.getName() + l.s + threadCurrentThread.getId() + l.t;
            } else {
                x.c("crash thread name change to %s", str13);
                Iterator<Thread> it = Thread.getAllStackTraces().keySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Thread next = it.next();
                    if (next.getName().equals(str13)) {
                        str13 = str13 + l.s + next.getId() + l.t;
                        break;
                    }
                }
            }
            String str14 = str9;
            try {
                CrashDetailBean crashDetailBeanPackageCrashDatas = packageCrashDatas(str12, str13, (j2 / 1000) + (j * 1000), str9, str2, strA, str10, str8, str4, (String) map.get("SysLogPath"), str7, null, null, true);
                if (crashDetailBeanPackageCrashDatas == null) {
                    x.e("pkg crash datas fail!", new Object[0]);
                    return;
                }
                try {
                    com.tencent.bugly.crashreport.crash.b.a("NATIVE_CRASH", z.a(), this.c.d, Thread.currentThread(), str14 + "\n" + str2 + "\n" + strA, crashDetailBeanPackageCrashDatas);
                    if (this.b.a(crashDetailBeanPackageCrashDatas, i3)) {
                        z = true;
                    } else {
                        z = true;
                        this.b.a(crashDetailBeanPackageCrashDatas, 3000L, true);
                    }
                    this.b.b(crashDetailBeanPackageCrashDatas);
                    NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
                    b.a(z, nativeCrashHandler != null ? nativeCrashHandler.getDumpFilePath() : null);
                } catch (Throwable th) {
                    th = th;
                    if (x.a(th)) {
                        return;
                    }
                    th.printStackTrace();
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }
}

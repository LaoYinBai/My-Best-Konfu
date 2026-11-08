package com.yxcorp.kuaishou.addfp.android.a;

import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.message.MsgConstant;
import com.xiaomi.mipush.sdk.Constants;
import com.yxcorp.kuaishou.addfp.KWEGIDDFP;
import com.yxcorp.kuaishou.addfp.ResponseDfpCallback;
import com.yxcorp.kuaishou.addfp.android.Orange;
import java.net.URLEncoder;
import java.util.concurrent.locks.ReentrantLock;
import java.util.zip.CRC32;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private String a;
    private String b;
    private ReentrantLock c;

    private a() {
        this.b = "";
        this.c = new ReentrantLock();
    }

    /* synthetic */ a(byte b) {
        this();
    }

    public static a a() {
        return b.a;
    }

    private static String a(Context context) {
        try {
            if (!d()) {
                String strC = c();
                if (!TextUtils.isEmpty(strC)) {
                    return strC;
                }
            }
            boolean z = false;
            if (Build.VERSION.SDK_INT >= 29) {
                String str = Build.MODEL;
                if (!TextUtils.isEmpty(str) && str.toLowerCase().contains("redmi") && str.toLowerCase().contains("note 7")) {
                    z = true;
                }
            }
            if (!z) {
                String strGSer = Orange.getInstance().gSer();
                if (!TextUtils.isEmpty(strGSer) && !strGSer.startsWith("KWE")) {
                    return strGSer;
                }
            }
            if (com.yxcorp.kuaishou.addfp.android.b.e.a(context, new String[]{MsgConstant.PERMISSION_READ_PHONE_STATE})) {
                String serial = Build.getSerial();
                if (!TextUtils.isEmpty(serial) && !serial.equals(UtilityImpl.NET_TYPE_UNKNOWN)) {
                    return serial;
                }
            }
            String str2 = Build.SERIAL;
            return !TextUtils.isEmpty(str2) ? !str2.equals(UtilityImpl.NET_TYPE_UNKNOWN) ? str2 : "KWE_N" : "KWE_N";
        } catch (Throwable th) {
            th.printStackTrace();
            return "KWE_N";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x004f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String c() throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L38 java.lang.Throwable -> L3a
            java.lang.String r2 = "/sys/class/android_usb/android0/iSerial"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L38 java.lang.Throwable -> L3a
            boolean r2 = r1.exists()     // Catch: java.lang.Throwable -> L38 java.lang.Throwable -> L3a
            if (r2 == 0) goto L31
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L38 java.lang.Throwable -> L3a
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L38 java.lang.Throwable -> L3a
            r3.<init>(r1)     // Catch: java.lang.Throwable -> L38 java.lang.Throwable -> L3a
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L38 java.lang.Throwable -> L3a
            java.lang.String r1 = r2.readLine()     // Catch: java.lang.Throwable -> L2f java.lang.Throwable -> L4a
            boolean r3 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> L2f java.lang.Throwable -> L4a
            if (r3 != 0) goto L32
            java.lang.String r1 = r1.trim()     // Catch: java.lang.Throwable -> L2f java.lang.Throwable -> L4a
            r2.close()     // Catch: java.lang.Throwable -> L2a
            goto L2e
        L2a:
            r0 = move-exception
            r0.printStackTrace()
        L2e:
            return r1
        L2f:
            r1 = move-exception
            goto L3c
        L31:
            r2 = r0
        L32:
            if (r2 == 0) goto L49
            r2.close()     // Catch: java.lang.Throwable -> L45
            goto L49
        L38:
            r1 = move-exception
            goto L4d
        L3a:
            r1 = move-exception
            r2 = r0
        L3c:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L4a
            if (r2 == 0) goto L49
            r2.close()     // Catch: java.lang.Throwable -> L45
            goto L49
        L45:
            r1 = move-exception
            r1.printStackTrace()
        L49:
            return r0
        L4a:
            r0 = move-exception
            r1 = r0
            r0 = r2
        L4d:
            if (r0 == 0) goto L57
            r0.close()     // Catch: java.lang.Throwable -> L53
            goto L57
        L53:
            r0 = move-exception
            r0.printStackTrace()
        L57:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yxcorp.kuaishou.addfp.android.a.a.c():java.lang.String");
    }

    private static boolean d() {
        try {
            return Build.VERSION.SDK_INT >= 26;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    private static long e() {
        try {
            return System.currentTimeMillis() - SystemClock.elapsedRealtime();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0L;
        }
    }

    public final String a(Context context, ResponseDfpCallback responseDfpCallback, boolean z, boolean z2) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            try {
                this.c.lock();
            } catch (Throwable th) {
                th.printStackTrace();
                this.b = "KWE_PE";
                responseDfpCallback.onFailed(-1, com.yxcorp.kuaishou.addfp.android.b.e.a(th));
            }
            if (!TextUtils.isEmpty(this.b)) {
                return this.b;
            }
            JSONObject jSONObject = new JSONObject();
            String str = Build.MODEL;
            jSONObject.put("k27", com.yxcorp.kuaishou.addfp.android.b.e.b(TextUtils.isEmpty(str) ? "KWE_N" : str.replace("=", "").replace("&", "")));
            jSONObject.put("k64", com.yxcorp.kuaishou.addfp.android.b.e.b(a(context)));
            jSONObject.put("k31", com.yxcorp.kuaishou.addfp.android.b.e.b(z ? d.c() : d.d()));
            jSONObject.put("k66", com.yxcorp.kuaishou.addfp.android.b.e.b(com.yxcorp.kuaishou.addfp.android.b.a.a(context)));
            jSONObject.put("k39", com.yxcorp.kuaishou.addfp.android.b.e.b(Long.toString(e())));
            jSONObject.put("k101", com.yxcorp.kuaishou.addfp.android.b.e.b(com.yxcorp.kuaishou.addfp.android.b.e.a(new String(Base64.decode("Y2F0IC9zeXMvZGV2aWNlcy9zb2MwL3NlcmlhbF9udW1iZXI=", 0)))));
            jSONObject.put("k102", com.yxcorp.kuaishou.addfp.android.b.e.b(com.yxcorp.kuaishou.addfp.android.b.e.a(new String(Base64.decode("Y2F0IC9wcm9jL3N5cy9rZXJuZWwvcmFuZG9tL2Jvb3RfaWQ=", 0)))));
            jSONObject.put("k57", com.yxcorp.kuaishou.addfp.android.b.e.b(d.b(context)));
            jSONObject.put("k68", com.yxcorp.kuaishou.addfp.android.b.e.b(d.a(context)));
            jSONObject.put("k105", com.yxcorp.kuaishou.addfp.android.b.e.b(Orange.getInstance().gRdi()));
            jSONObject.put("k83", com.yxcorp.kuaishou.addfp.android.b.e.b(d.d(context)));
            jSONObject.put("k86", com.yxcorp.kuaishou.addfp.android.b.e.b(d.e(context)));
            jSONObject.put("k3", com.yxcorp.kuaishou.addfp.android.b.e.b(d.c(context)));
            jSONObject.put("k109", com.yxcorp.kuaishou.addfp.android.b.e.b(Orange.getInstance().gProps()));
            jSONObject.put("k36", com.yxcorp.kuaishou.addfp.android.b.e.b("1.3.4.32.7230603b"));
            jSONObject.put("k14", com.yxcorp.kuaishou.addfp.android.b.e.b("AD_AND"));
            jSONObject.put("k5", com.yxcorp.kuaishou.addfp.android.b.e.b(Long.toString(d.a())));
            jSONObject.put("k46", com.yxcorp.kuaishou.addfp.android.b.e.b(Long.toString(d.b())));
            jSONObject.put("k110", com.yxcorp.kuaishou.addfp.android.b.e.b(Orange.getInstance().gKpsd()));
            jSONObject.put("k111", com.yxcorp.kuaishou.addfp.android.b.e.b("KWE_NPN"));
            if (z2) {
                com.yxcorp.kuaishou.addfp.a.a.b.a().c();
            }
            String strC = KWEGIDDFP.mOAID;
            if (TextUtils.isEmpty(strC) || strC.startsWith("KWE")) {
                strC = new e(context).c();
            }
            jSONObject.put("k97", com.yxcorp.kuaishou.addfp.android.b.e.b(strC));
            try {
                CRC32 crc32 = new CRC32();
                for (int i = 1; i <= 111; i++) {
                    String str2 = "k" + i;
                    if (jSONObject.has(str2)) {
                        crc32.update(jSONObject.optString(str2).getBytes());
                    }
                }
                jSONObject.put("k14", com.yxcorp.kuaishou.addfp.android.b.e.b("AD_AND" + Constants.COLON_SEPARATOR + String.valueOf(crc32.getValue())));
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
            StringBuilder sb = new StringBuilder("mimi :");
            sb.append(jSONObject.toString());
            sb.append(" ");
            sb.append(jSONObject.toString().getBytes().length);
            sb.append(" ");
            sb.append(System.currentTimeMillis() - jCurrentTimeMillis);
            byte[] magicWrapper = Orange.getInstance().getMagicWrapper(context, jSONObject.toString().getBytes(), 0);
            new StringBuilder("get mini lenth ").append(magicWrapper.length);
            this.b = URLEncoder.encode(Base64.encodeToString(magicWrapper, 0), "utf-8");
            this.c.unlock();
            if (TextUtils.isEmpty(this.b)) {
                this.b = "KWE_N";
            }
            return this.b;
        } finally {
            this.c.unlock();
        }
    }

    public final void a(String str) {
        this.a = str;
    }

    public final String b() {
        try {
            return !TextUtils.isEmpty(this.a) ? this.a : "KWE_N";
        } catch (Throwable th) {
            th.printStackTrace();
            return "KWE_N";
        }
    }
}

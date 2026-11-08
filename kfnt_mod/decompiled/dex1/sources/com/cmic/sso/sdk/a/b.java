package com.cmic.sso.sdk.a;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import com.cmic.sso.sdk.d.c;
import com.cmic.sso.sdk.d.g;
import com.cmic.sso.sdk.d.n;
import java.lang.reflect.Method;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static b f5348a;

    /* renamed from: b, reason: collision with root package name */
    private static long f5349b;

    /* renamed from: c, reason: collision with root package name */
    private C0161b f5350c = null;

    public static class a extends Exception {
        public a(String str) {
            super(str);
        }
    }

    /* renamed from: com.cmic.sso.sdk.a.b$b, reason: collision with other inner class name */
    public static class C0161b {

        /* renamed from: a, reason: collision with root package name */
        private String f5351a = "";

        /* renamed from: b, reason: collision with root package name */
        private String f5352b = "";

        /* renamed from: c, reason: collision with root package name */
        private String f5353c = "";
        private String d = "";
        private String e = "";
        private String f = "";
        private boolean g = false;
        private boolean h = false;
        private int i = -1;
        private int j = -1;
        private int k = -1;
        private int l = -1;
        private String m = "";
        private String n = "";
        private int o = -1;
        private int p = -1;
        private int q = -1;

        protected void a(String str) {
            if (str != null) {
                this.f5353c = str;
            }
        }

        public String a() {
            return this.d;
        }

        protected void b(String str) {
            if (str != null) {
                this.d = str;
            }
        }

        public String b() {
            return this.e;
        }

        protected void c(String str) {
            if (str != null) {
                this.e = str;
            }
        }

        public String a(int i) {
            if (this.i == i) {
                return this.f5351a;
            }
            return this.j == i ? this.f5352b : "";
        }

        public String c() {
            return this.f;
        }

        protected void d(String str) {
            if (str != null) {
                this.f = str;
            }
        }

        protected void e(String str) {
            if (str != null) {
                this.m = str;
            }
        }

        public String d() {
            return this.n;
        }

        protected void f(String str) {
            this.n = str;
        }

        protected void a(boolean z) {
            this.g = z;
        }

        public boolean e() {
            return this.h;
        }

        protected void b(boolean z) {
            this.h = z;
        }

        public int f() {
            return this.o;
        }

        protected void b(int i) {
            this.o = i;
        }

        public int g() {
            return this.i;
        }

        protected void c(int i) {
            this.i = i;
        }

        public int h() {
            return this.j;
        }

        protected void d(int i) {
            this.j = i;
        }

        protected void e(int i) {
            this.k = i;
        }

        protected void f(int i) {
            this.l = i;
        }

        public int i() {
            if (!TextUtils.isEmpty(this.f) && !TextUtils.isEmpty(this.e)) {
                return 2;
            }
            if (TextUtils.isEmpty(this.m) || TextUtils.isEmpty(this.n)) {
                return (TextUtils.isEmpty(this.e) && TextUtils.isEmpty(this.f) && TextUtils.isEmpty(this.m) && TextUtils.isEmpty(this.n)) ? 0 : 1;
            }
            return 2;
        }

        public String g(int i) {
            if (this.i == i) {
                return this.e;
            }
            return this.j == i ? this.f : "";
        }

        public String h(int i) {
            if (this.i == i) {
                return this.f5353c;
            }
            return this.j == i ? this.d : "";
        }

        public String i(int i) {
            if (this.i == i) {
                return this.m;
            }
            return this.j == i ? this.n : "";
        }
    }

    private b() {
    }

    public static b a() {
        if (f5348a == null) {
            f5348a = new b();
        }
        return f5348a;
    }

    public C0161b b() {
        if (this.f5350c == null) {
            return new C0161b();
        }
        return this.f5350c;
    }

    public void a(Context context, boolean z) throws Throwable {
        if (System.currentTimeMillis() - f5349b < 5000) {
            return;
        }
        this.f5350c = new C0161b();
        if (n.a(context)) {
            b(context, z);
            if (n.e() && n.d()) {
                c.b("UMCTelephonyManagement", "华为手机兼容性处理");
                if (this.f5350c.p == 0 || this.f5350c.p == 1) {
                    this.f5350c.o = this.f5350c.p;
                }
            }
            if (z) {
                try {
                    if (Build.VERSION.SDK_INT >= 22) {
                        c(context);
                    } else {
                        d(context);
                    }
                } catch (Exception unused) {
                    c.a("UMCTelephonyManagement", "read sim info error");
                }
            }
            if (Build.VERSION.SDK_INT >= 21) {
                b(context);
            }
            f5349b = System.currentTimeMillis();
        }
    }

    private void b(Context context, boolean z) throws NoSuchMethodException, SecurityException {
        if (Build.VERSION.SDK_INT < 22) {
            this.f5350c.o = -1;
            return;
        }
        SubscriptionManager subscriptionManagerFrom = SubscriptionManager.from(context.getApplicationContext());
        if (subscriptionManagerFrom != null) {
            if (z) {
                try {
                    SubscriptionInfo subscriptionInfoA = a(subscriptionManagerFrom, "getDefaultDataSubscriptionInfo", (Object[]) null);
                    if (subscriptionInfoA != null) {
                        this.f5350c.o = subscriptionInfoA.getSimSlotIndex();
                        this.f5350c.p = subscriptionInfoA.getSubscriptionId();
                        c.b("UMCTelephonyManagement", "getDefaultDataSubscriptionInfo适配成功: dataSlotId即sim_id = " + this.f5350c.o);
                        c.b("UMCTelephonyManagement", "getDefaultDataSubscriptionInfo适配成功: dataSubId = " + this.f5350c.p);
                        return;
                    }
                } catch (Exception unused) {
                    c.a("UMCTelephonyManagement", "readDefaultDataSubId-->getDefaultDataSubscriptionInfo 反射出错");
                }
            }
            try {
                if (this.f5350c.o == -1 && Build.VERSION.SDK_INT >= 24) {
                    this.f5350c.p = SubscriptionManager.getDefaultDataSubscriptionId();
                    c.b("UMCTelephonyManagement", "android 7.0及以上手机getDefaultDataSubscriptionId适配成功: dataSubId = " + this.f5350c.p);
                    return;
                }
            } catch (Exception unused2) {
                c.a("UMCTelephonyManagement", "android 7.0及以上手机getDefaultDataSubscriptionId适配失败");
            }
            try {
                Method method = subscriptionManagerFrom.getClass().getMethod("getDefaultDataSubId", new Class[0]);
                if (method != null) {
                    this.f5350c.p = ((Integer) method.invoke(subscriptionManagerFrom, new Object[0])).intValue();
                    c.b("UMCTelephonyManagement", "android 7.0以下手机getDefaultDataSubId适配成功: dataSubId = " + this.f5350c.p);
                    return;
                }
            } catch (Exception unused3) {
                c.a("UMCTelephonyManagement", "readDefaultDataSubId-->getDefaultDataSubId 反射出错");
            }
            try {
                Method method2 = subscriptionManagerFrom.getClass().getMethod("getDefaultDataSubscriptionId", new Class[0]);
                if (method2 != null) {
                    this.f5350c.p = ((Integer) method2.invoke(subscriptionManagerFrom, new Object[0])).intValue();
                    c.b("UMCTelephonyManagement", "反射getDefaultDataSubscriptionId适配成功: dataSubId = " + this.f5350c.p);
                }
            } catch (Exception unused4) {
                c.a("UMCTelephonyManagement", "getDefaultDataSubscriptionId-->getDefaultDataSubscriptionId 反射出错");
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v6, types: [java.lang.String] */
    private void b(Context context) throws Throwable {
        Cursor cursorQuery;
        Throwable th;
        c.b("UMCTelephonyManagement", "readSimInfoDbStart");
        Uri uri = Uri.parse("content://telephony/siminfo");
        ContentResolver contentResolver = context.getContentResolver();
        ?? r12 = 0;
        Cursor cursor = null;
        try {
            try {
                cursorQuery = contentResolver.query(uri, new String[]{"_id", "icc_id", "sim_id", "mcc", DispatchConstants.MNC, "carrier_name"}, "sim_id>=?", new String[]{OnekeyLoginConstants.CU_RESULT_SUCCESS}, null);
                if (cursorQuery != null) {
                    while (cursorQuery.moveToNext()) {
                        try {
                            String string = cursorQuery.getString(cursorQuery.getColumnIndex("icc_id"));
                            int i = cursorQuery.getInt(cursorQuery.getColumnIndex("sim_id"));
                            int i2 = cursorQuery.getInt(cursorQuery.getColumnIndex("_id"));
                            String string2 = cursorQuery.getString(cursorQuery.getColumnIndex("mcc"));
                            String string3 = cursorQuery.getString(cursorQuery.getColumnIndex(DispatchConstants.MNC));
                            String string4 = cursorQuery.getString(cursorQuery.getColumnIndex("carrier_name"));
                            if (this.f5350c.o == -1 && this.f5350c.p != -1 && this.f5350c.p == i2) {
                                this.f5350c.o = i;
                                c.b("UMCTelephonyManagement", "通过读取sim db获取数据流量卡的卡槽值：" + i);
                            }
                            if (this.f5350c.o == i && !n.e()) {
                                this.f5350c.p = i2;
                            }
                            if (i == 0) {
                                this.f5350c.f5351a = string;
                                this.f5350c.k = i2;
                                if (this.f5350c.i == -1) {
                                    this.f5350c.i = i;
                                }
                                if (TextUtils.isEmpty(this.f5350c.m) && !TextUtils.isEmpty(string4)) {
                                    String strA = a(string4);
                                    if (!TextUtils.isEmpty(strA)) {
                                        this.f5350c.m = strA;
                                    }
                                }
                                if (TextUtils.isEmpty(this.f5350c.m)) {
                                    String strB = b(string);
                                    if (!TextUtils.isEmpty(strB)) {
                                        this.f5350c.m = strB;
                                    }
                                }
                                if (TextUtils.isEmpty(this.f5350c.m) && !TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string3)) {
                                    if (string3.length() == 1) {
                                        string3 = OnekeyLoginConstants.CU_RESULT_SUCCESS + string3;
                                    }
                                    this.f5350c.m = string2 + string3;
                                }
                            } else if (i == 1) {
                                this.f5350c.f5352b = string;
                                this.f5350c.l = i2;
                                if (this.f5350c.j == -1) {
                                    this.f5350c.j = i;
                                }
                                if (TextUtils.isEmpty(this.f5350c.n) && !TextUtils.isEmpty(string4)) {
                                    String strA2 = a(string4);
                                    if (!TextUtils.isEmpty(strA2)) {
                                        this.f5350c.n = strA2;
                                    }
                                }
                                if (TextUtils.isEmpty(this.f5350c.n)) {
                                    String strB2 = b(string);
                                    if (!TextUtils.isEmpty(strB2)) {
                                        this.f5350c.n = strB2;
                                    }
                                }
                                if (TextUtils.isEmpty(this.f5350c.n) && !TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string3)) {
                                    if (string3.length() == 1) {
                                        string3 = OnekeyLoginConstants.CU_RESULT_SUCCESS + string3;
                                    }
                                    this.f5350c.n = string2 + string3;
                                }
                            }
                            c.b("UMCTelephonyManagement", "icc_id-->" + string);
                            c.b("UMCTelephonyManagement", "sim_id-->" + i);
                            c.b("UMCTelephonyManagement", "subId或者说是_id->" + i2);
                            c.b("UMCTelephonyManagement", "mcc_string--->" + string3);
                            c.b("UMCTelephonyManagement", "mnc_string--->" + string2);
                            c.b("UMCTelephonyManagement", "carrier_name----->" + string4);
                            c.b("UMCTelephonyManagement", "---------------------------------");
                        } catch (Exception unused) {
                            cursor = cursorQuery;
                            c.a("UMCTelephonyManagement", "readSimInfoDb error");
                            if (cursor != null) {
                                cursor.close();
                            }
                            r12 = "UMCTelephonyManagement";
                            c.b("UMCTelephonyManagement", "readSimInfoDbEnd");
                        } catch (Throwable th2) {
                            th = th2;
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                            throw th;
                        }
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Throwable th3) {
                cursorQuery = r12;
                th = th3;
            }
        } catch (Exception unused2) {
        }
        r12 = "UMCTelephonyManagement";
        c.b("UMCTelephonyManagement", "readSimInfoDbEnd");
    }

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        c.b("UMCTelephonyManagement", "operatorChina = " + str);
        return str.contains("中国移动") ? "46000" : str.contains("中国联通") ? "46001" : str.contains("中国电信") ? "46003" : "";
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:42:0x009b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String b(java.lang.String r6) {
        /*
            r5 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            r1 = 6
            if (r0 == 0) goto L10
            int r0 = r6.length()
            if (r0 >= r1) goto L10
            java.lang.String r6 = ""
            return r6
        L10:
            r0 = 0
            java.lang.String r6 = r6.substring(r0, r1)
            java.lang.String r2 = "UMCTelephonyManagement"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "operatorFlag = "
            r3.append(r4)
            r3.append(r6)
            java.lang.String r3 = r3.toString()
            com.cmic.sso.sdk.d.c.b(r2, r3)
            r2 = -1
            int r3 = r6.hashCode()
            r4 = 1657594888(0x62cce408, float:1.8897836E21)
            if (r3 == r4) goto L92
            r1 = 1657594911(0x62cce41f, float:1.8897868E21)
            if (r3 == r1) goto L87
            switch(r3) {
                case 1657594879: goto L7d;
                case 1657594880: goto L73;
                case 1657594881: goto L69;
                case 1657594882: goto L5f;
                case 1657594883: goto L55;
                default: goto L3d;
            }
        L3d:
            switch(r3) {
                case 1657594885: goto L4b;
                case 1657594886: goto L41;
                default: goto L40;
            }
        L40:
            goto L9b
        L41:
            java.lang.String r0 = "898607"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L9b
            r1 = 3
            goto L9c
        L4b:
            java.lang.String r0 = "898606"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L9b
            r1 = 5
            goto L9c
        L55:
            java.lang.String r0 = "898604"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L9b
            r1 = 2
            goto L9c
        L5f:
            java.lang.String r0 = "898603"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L9b
            r1 = 7
            goto L9c
        L69:
            java.lang.String r0 = "898602"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L9b
            r1 = 1
            goto L9c
        L73:
            java.lang.String r0 = "898601"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L9b
            r1 = 4
            goto L9c
        L7d:
            java.lang.String r1 = "898600"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L9b
            r1 = 0
            goto L9c
        L87:
            java.lang.String r0 = "898611"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L9b
            r1 = 8
            goto L9c
        L92:
            java.lang.String r0 = "898609"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L9b
            goto L9c
        L9b:
            r1 = -1
        L9c:
            switch(r1) {
                case 0: goto La8;
                case 1: goto La8;
                case 2: goto La8;
                case 3: goto La8;
                case 4: goto La5;
                case 5: goto La5;
                case 6: goto La5;
                case 7: goto La2;
                case 8: goto La2;
                default: goto L9f;
            }
        L9f:
            java.lang.String r6 = ""
            return r6
        La2:
            java.lang.String r6 = "46003"
            return r6
        La5:
            java.lang.String r6 = "46001"
            return r6
        La8:
            java.lang.String r6 = "46000"
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cmic.sso.sdk.a.b.b(java.lang.String):java.lang.String");
    }

    @SuppressLint({"NewApi"})
    private void c(Context context) {
        List<SubscriptionInfo> listE;
        TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService("phone");
        if (telephonyManager == null || (listE = e(context)) == null || listE.size() <= 0) {
            return;
        }
        a(listE, telephonyManager);
        b(listE, telephonyManager);
    }

    @SuppressLint({"MissingPermission"})
    private void d(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService("phone");
        this.f5350c.c(0);
        this.f5350c.d(1);
        this.f5350c.b(-1);
        try {
            try {
                try {
                    this.f5350c.c(a(telephonyManager, "getSubscriberId", 0));
                    this.f5350c.d(a(telephonyManager, "getSubscriberId", 1));
                } catch (a unused) {
                    this.f5350c.c(telephonyManager.getSubscriberId());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (a unused2) {
            this.f5350c.c(a(telephonyManager, "getSubscriberIdGemini", 0));
            this.f5350c.d(a(telephonyManager, "getSubscriberIdGemini", 1));
        }
        try {
            try {
                this.f5350c.a(b(telephonyManager, "getSimState", 0));
                this.f5350c.b(b(telephonyManager, "getSimState", 1));
            } catch (a unused3) {
                this.f5350c.a(telephonyManager.getSimState() == 5);
            }
        } catch (a unused4) {
            this.f5350c.a(b(telephonyManager, "getSimStateGemini", 0));
            this.f5350c.b(b(telephonyManager, "getSimStateGemini", 1));
        }
        try {
            try {
                this.f5350c.e(a(telephonyManager, "getSimOperator", 0));
                this.f5350c.f(a(telephonyManager, "getSimOperator", 1));
            } catch (a unused5) {
                this.f5350c.e(a(telephonyManager, "getSimOperatorGemini", 0));
                this.f5350c.f(a(telephonyManager, "getSimOperatorGemini", 1));
            }
        } catch (a unused6) {
            this.f5350c.e(telephonyManager.getSimOperator());
        }
        if (TextUtils.isEmpty(this.f5350c.b()) && !TextUtils.isEmpty(this.f5350c.c())) {
            this.f5350c.a(this.f5350c.a());
            this.f5350c.b("");
            this.f5350c.c(this.f5350c.c());
            this.f5350c.d("");
            this.f5350c.c(this.f5350c.h());
            this.f5350c.d(-1);
            this.f5350c.a(this.f5350c.e());
            this.f5350c.b(false);
            this.f5350c.e(this.f5350c.d());
            this.f5350c.f("");
            this.f5350c.b(this.f5350c.g());
            return;
        }
        if (!TextUtils.isEmpty(this.f5350c.b()) && TextUtils.isEmpty(this.f5350c.c())) {
            this.f5350c.b("");
            this.f5350c.b(false);
            this.f5350c.d(-1);
            this.f5350c.b(this.f5350c.g());
            return;
        }
        if (TextUtils.isEmpty(this.f5350c.b()) && TextUtils.isEmpty(this.f5350c.c())) {
            this.f5350c.a("");
            this.f5350c.b("");
            this.f5350c.c(-1);
            this.f5350c.d(-1);
            this.f5350c.a(false);
            this.f5350c.b(false);
            this.f5350c.b(-1);
        }
    }

    @SuppressLint({"NewApi", "MissingPermission"})
    private void a(List<SubscriptionInfo> list, TelephonyManager telephonyManager) {
        SubscriptionInfo subscriptionInfoA;
        int size = list != null ? list.size() : 0;
        if (size == 1) {
            subscriptionInfoA = list.get(0);
        } else if (size <= 1) {
            return;
        } else {
            subscriptionInfoA = a(list, 0);
        }
        this.f5350c.f5351a = subscriptionInfoA.getIccId();
        this.f5350c.c(subscriptionInfoA.getSimSlotIndex());
        this.f5350c.e(subscriptionInfoA.getSubscriptionId());
        this.f5350c.m = b(this.f5350c.f5351a);
        c.b("UMCTelephonyManagement", "readSim1Info1 iccid1 = " + this.f5350c.f5351a);
        if (this.f5350c.o == -1 && this.f5350c.p == subscriptionInfoA.getSubscriptionId()) {
            this.f5350c.o = subscriptionInfoA.getSimSlotIndex();
            c.b("UMCTelephonyManagement", "readSim1Info1 dataSlotId = " + this.f5350c.o);
        }
        if (n.d()) {
            try {
                try {
                    try {
                        this.f5350c.a(a(telephonyManager, "getDeviceId", subscriptionInfoA.getSimSlotIndex()));
                    } catch (Exception unused) {
                        c.a("UMCTelephonyManagement", "readSim1Info imei1 failed");
                    }
                } catch (a unused2) {
                    this.f5350c.a(telephonyManager.getDeviceId());
                }
            } catch (a unused3) {
                this.f5350c.a(a(telephonyManager, "getDeviceIdGemini", subscriptionInfoA.getSimSlotIndex()));
            }
            int simSlotIndex = com.cmic.sso.sdk.a.a.a() == 0 ? subscriptionInfoA.getSimSlotIndex() : subscriptionInfoA.getSubscriptionId();
            try {
                try {
                    try {
                        this.f5350c.c(a(telephonyManager, "getSubscriberId", subscriptionInfoA.getSubscriptionId()));
                    } catch (a unused4) {
                        this.f5350c.c(telephonyManager.getSubscriberId());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (a unused5) {
                this.f5350c.c(a(telephonyManager, "getSubscriberIdGemini", simSlotIndex));
            }
            try {
                try {
                    this.f5350c.e(a(telephonyManager, "getSimOperator", subscriptionInfoA.getSubscriptionId()));
                } catch (a unused6) {
                    this.f5350c.e(a(telephonyManager, "getSimOperatorGemini", simSlotIndex));
                }
            } catch (a unused7) {
                this.f5350c.e(telephonyManager.getSimOperator());
            }
        }
    }

    @SuppressLint({"NewApi"})
    private void b(List<SubscriptionInfo> list, TelephonyManager telephonyManager) {
        if ((list != null ? list.size() : 0) > 1) {
            SubscriptionInfo subscriptionInfoA = a(list, 1);
            this.f5350c.d(subscriptionInfoA.getSimSlotIndex());
            this.f5350c.f(subscriptionInfoA.getSubscriptionId());
            this.f5350c.f5352b = subscriptionInfoA.getIccId();
            this.f5350c.n = b(this.f5350c.f5352b);
            c.b("UMCTelephonyManagement", "readSim1Info2 iccid2 = " + this.f5350c.f5352b);
            if (this.f5350c.o == -1 && this.f5350c.p == subscriptionInfoA.getSubscriptionId()) {
                this.f5350c.o = subscriptionInfoA.getSimSlotIndex();
                c.b("UMCTelephonyManagement", "readSim1Info2 dataSlotId = " + this.f5350c.o);
            }
            if (n.d()) {
                try {
                    try {
                        this.f5350c.b(a(telephonyManager, "getDeviceId", 1));
                    } catch (Exception unused) {
                        c.a("UMCTelephonyManagement", "readSim1Info imei2 failed");
                    }
                } catch (a unused2) {
                    this.f5350c.b(a(telephonyManager, "getDeviceIdGemini", 1));
                }
                int subscriptionId = com.cmic.sso.sdk.a.a.a() != 0 ? subscriptionInfoA.getSubscriptionId() : 1;
                try {
                    try {
                        this.f5350c.d(a(telephonyManager, "getSubscriberId", subscriptionInfoA.getSubscriptionId()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (a unused3) {
                    this.f5350c.d(a(telephonyManager, "getSubscriberIdGemini", subscriptionId));
                }
                try {
                    try {
                        this.f5350c.f(a(telephonyManager, "getSimOperator", subscriptionInfoA.getSubscriptionId()));
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } catch (a unused4) {
                    this.f5350c.f(a(telephonyManager, "getSimOperatorGemini", subscriptionId));
                }
            }
        }
    }

    @SuppressLint({"NewApi"})
    private SubscriptionInfo a(List<SubscriptionInfo> list, int i) {
        SubscriptionInfo subscriptionInfo = list.get(0);
        for (SubscriptionInfo subscriptionInfo2 : list) {
            if (subscriptionInfo2.getSimSlotIndex() == i) {
                subscriptionInfo = subscriptionInfo2;
            }
        }
        return subscriptionInfo;
    }

    @SuppressLint({"NewApi", "MissingPermission"})
    private List<SubscriptionInfo> e(Context context) {
        SubscriptionManager subscriptionManagerFrom = SubscriptionManager.from(context.getApplicationContext());
        if (subscriptionManagerFrom != null) {
            return subscriptionManagerFrom.getActiveSubscriptionInfoList();
        }
        return null;
    }

    private String a(TelephonyManager telephonyManager, String str, int i) throws a, ClassNotFoundException {
        Object objA = a(telephonyManager, str, new Object[]{Integer.valueOf(i)}, new Class[]{Integer.TYPE});
        if (objA != null) {
            return objA.toString();
        }
        return null;
    }

    private boolean b(TelephonyManager telephonyManager, String str, int i) throws a, ClassNotFoundException {
        Object objA = a(telephonyManager, str, new Object[]{Integer.valueOf(i)}, new Class[]{Integer.TYPE});
        return objA != null && Integer.parseInt(objA.toString()) == 5;
    }

    @SuppressLint({"NewApi"})
    private SubscriptionInfo a(Object obj, String str, Object[] objArr) throws a {
        return (SubscriptionInfo) a(obj, str, objArr, null);
    }

    private Object a(Object obj, String str, Object[] objArr, Class[] clsArr) throws a, ClassNotFoundException {
        try {
            Class<?> cls = Class.forName(obj.getClass().getName());
            if (objArr != null && clsArr != null) {
                return cls.getMethod(str, clsArr).invoke(obj, objArr);
            }
            return cls.getMethod(str, new Class[0]).invoke(obj, new Object[0]);
        } catch (Exception unused) {
            c.a("UMCTelephonyManagement", str + " 反射出错");
            throw new a(str);
        }
    }

    @SuppressLint({"MissingPermission"})
    private int f(Context context) throws NoSuchMethodException, SecurityException {
        TelephonyManager telephonyManager;
        if (!g.a(context, "android.permission.READ_PHONE_STATE") || (telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService("phone")) == null) {
            return -1;
        }
        if (n.d()) {
            try {
                Method method = telephonyManager.getClass().getMethod("getDataNetworkType", Integer.TYPE);
                c.b("UMCTelephonyManagement", "data dataNetworkType defaultDataSubId = " + this.f5350c.p);
                int iIntValue = ((Integer) method.invoke(telephonyManager, Integer.valueOf(this.f5350c.p))).intValue();
                c.b("UMCTelephonyManagement", "data dataNetworkType ---------" + iIntValue);
                if (iIntValue != 0 || Build.VERSION.SDK_INT < 24) {
                    return iIntValue;
                }
                c.b("UMCTelephonyManagement", "data dataNetworkType ---->=N " + iIntValue);
                return telephonyManager.getDataNetworkType();
            } catch (Exception e) {
                c.a("UMCTelephonyManagement", "data dataNetworkType ----反射出错-----");
                e.printStackTrace();
                return -1;
            }
        }
        return telephonyManager.getDataNetworkType();
    }

    public String a(Context context) {
        switch (f(context)) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                return "1";
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                return OnekeyLoginConstants.UNICOM_TYPE;
            case 13:
            case 18:
            case 19:
                return OnekeyLoginConstants.TELECOM_TYPE;
            case 20:
                return "4";
            default:
                return OnekeyLoginConstants.CU_RESULT_SUCCESS;
        }
    }
}
